package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.Item;
import csu.web.petstore.service.CartService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveCartItemServlet extends HttpServlet{
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    private String workingItemId;
    private Cart cart;
    private Account account;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");
        HttpSession session = req.getSession();
        cart= (Cart) session.getAttribute("cart");
        if (!cart.containsItemId(workingItemId)){
            req.getRequestDispatcher(ERROR_FORM).forward(req,resp);
        }
        else{
            cart.removeItemById(workingItemId);
            CartService cartService=new CartService();
            account = (Account) session.getAttribute("loginAccount");
            if (account!=null){
                String viewpage="removeCartItem/"+workingItemId;
                UserLogService userLogService=new UserLogService();
                userLogService.insertUserLog(account,viewpage);
            }
            cartService.deleteCart(account,workingItemId);
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(CART_FORM).forward(req,resp);
        }
    }
}
