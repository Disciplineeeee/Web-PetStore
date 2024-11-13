package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.Order;
import csu.web.petstore.service.CartService;
import csu.web.petstore.service.OrderService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConfirmOrderServlet extends HttpServlet {
    public static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private Order order;
    private Cart cart;
    private Account account;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = new OrderService();
        CartService cartService =new CartService();
        HttpSession session =req.getSession();
        order= (Order) session.getAttribute("order");
        account= (Account) session.getAttribute("loginAccount");
        orderService.insertOrder(order);
        cartService.deleteCartAll(account);
        Account account= (Account) session.getAttribute("loginAccount");
        if (account!=null){
            String viewpage="confirmorder/"+order.getOrderId();
            UserLogService userLogService=new UserLogService();
            userLogService.insertUserLog(account,viewpage);
        }
        cart= (Cart) session.getAttribute("cart");
        cart.removeAll();
        session.setAttribute("cart",cart);
        req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
