package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Order;
import csu.web.petstore.service.OrderService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {
    public static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private Order order;
    private int orderId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = new OrderService();
        HttpSession session =req.getSession();
        orderId= Integer.parseInt(req.getParameter("orderId"));
        order= orderService.getOrder(orderId);
        //Account account = (Account) session.getAttribute("account");
        Account account = (Account) session.getAttribute("loginAccount");
        if (account!=null){
            String viewpage="viewOrder/"+orderId;
            UserLogService userLogService=new UserLogService();
            userLogService.insertUserLog(account,viewpage);
        }
        session.setAttribute("order",order);
        req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}