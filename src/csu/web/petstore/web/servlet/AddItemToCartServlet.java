package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.CartItem;
import csu.web.petstore.domain.Item;
import csu.web.petstore.service.CartService;
import csu.web.petstore.service.CatalogService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    public static final String SIGNON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private String workingItemId;
    private Cart cart;
    private CartItem cartItem;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =req.getSession();
        Account account= (Account) session.getAttribute("loginAccount");
        if (account==null){
            req.getRequestDispatcher(SIGNON_FORM).forward(req,resp);
        }
        else{
            workingItemId = req.getParameter("workingItemId");
            cart= (Cart) session.getAttribute("cart");
            CartService cartService=new CartService();
            if (cart==null){
                cart = new Cart();
            }
            if (cart.containsItemId(workingItemId)){
                cartItem=new CartItem();
                cartItem=cart.incrementQuantityByItemId(workingItemId);
                cartService.updateCart(account,cartItem);
                if (account!=null){
                    String viewpage="increaseCartItem/"+workingItemId;
                    UserLogService userLogService=new UserLogService();
                    userLogService.insertUserLog(account,viewpage);
                }
            }

            else{
                CatalogService catalogService = new CatalogService();
                boolean isInstock = catalogService.isItemInStock(workingItemId);
                Item item = catalogService.getItem(workingItemId);
                cartItem =new CartItem();
                cartItem.setItem(item);
                cartItem.setQuantity(1);
                if (account!=null){
                    String viewpage="addCartItem/"+workingItemId;
                    UserLogService userLogService=new UserLogService();
                    userLogService.insertUserLog(account,viewpage);
                }
                cartService.insertCart(account,cartItem);
                cart.addItem(item,isInstock);
            }
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(CART_FORM).forward(req,resp);
        }

    }
}





//public class AddItemToCartServlet extends HttpServlet {
//    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String workingItemId = req.getParameter("workingItemId");
//
//        HttpSession session = req.getSession();
//        Cart cart = (Cart) session.getAttribute("cart");
//
//        if(cart == null){
//            cart = new Cart();
//        }
//
//        if (cart.containsItemId(workingItemId)) {
//            cart.incrementQuantityByItemId(workingItemId);
//        } else {
//            CatalogService catalogService = new CatalogService();
//            boolean isInStock = catalogService.isItemInStock(workingItemId);
//            Item item = catalogService.getItem(workingItemId);
//            cart.addItem(item, isInStock);
//        }
//
//        session.setAttribute("cart", cart);
//        req.getRequestDispatcher(CART_FORM).forward(req, resp);
//    }
//}
