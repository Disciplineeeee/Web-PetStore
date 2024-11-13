package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CartFormServlet extends HttpServlet{
    private Cart cart;
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    public static final String SIGNON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if (account == null) {
            req.getRequestDispatcher(SIGNON_FORM).forward(req, resp);
        } else {
            String viewpage="viewCart";
            UserLogService userlogService=new UserLogService();
            userlogService.insertUserLog(account,viewpage);
            cart= (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
            session.setAttribute("cart", cart);
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}




//public class CartFormServlet extends HttpServlet {
//    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher(CART_FORM).forward(req,resp);
//    }
//}
