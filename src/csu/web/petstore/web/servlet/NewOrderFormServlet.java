package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.Order;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {

    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =req.getSession();
        Account account= (Account) session.getAttribute("loginAccount");

        if (account!=null){
            String viewpage="prepareOrder";
            UserLogService userLogService=new UserLogService();
            userLogService.insertUserLog(account,viewpage);
        }
        Cart cart = (Cart) session.getAttribute("cart");
        Order order =new Order();
        order.initOrder(account,cart);
        session.setAttribute("order",order);
        System.out.println(order.getCreditCardTypes());
        session.setAttribute("CARD_TYPE_LIST",order.getCreditCardTypes());
        req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
    }
}





//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Account loginAccount = (Account) session.getAttribute("loginAccount");
//        if(loginAccount == null){
//            resp.sendRedirect("signonForm");
//        }else{
//            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
//        }
//    }

