package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.CartItem;
import csu.web.petstore.service.CartService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateCartServlet extends HttpServlet {
    private static final String UPDATE_CART = "/WEB-INF/jsp/cart/cart.jsp";

    private int quantity;
    private List<String> removeItems = new ArrayList<>();
    private Cart cart;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Account account= (Account) session.getAttribute("loginAccount");
        CartService cartService=new CartService();
        cart= (Cart) session.getAttribute("cart");
        for (CartItem items:cart.getCartItemList()) {
            quantity = Integer.parseInt(req.getParameter(items.getItem().getItemId()));
            if (quantity<=0){
                removeItems.add(items.getItem().getItemId());
            }
            else {
                int before_quantity=items.getQuantity();
                cart.setQuantityByItemId(items.getItem().getItemId(),quantity);
                if (account!=null){
                    String viewpage="changeCartItem/"+items.getItem().getItemId()+"/"+before_quantity+"to"+quantity;
                    UserLogService userLogService=new UserLogService();
                    userLogService.insertUserLog(account,viewpage);
                }
                cartService.updateCart(account,items);
            }
        }
        for (String removeItem:removeItems) {
            cart.removeItemById(removeItem);
            if (account!=null){
                String viewpage="removeCartItem/"+removeItem;
                UserLogService userLogService=new UserLogService();
                userLogService.insertUserLog(account,viewpage);
            }
            cartService.deleteCart(account,removeItem);
        }
        removeItems.clear();
        session.setAttribute("cart",cart);
        req.getRequestDispatcher(UPDATE_CART).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}