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
import java.util.List;

public class ListOrderServlet extends HttpServlet {
    private static final String ORDERS_LIST = "/WEB-INF/jsp/order/listOrder.jsp";
    private String name;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        name = ((Account)session.getAttribute("loginAccount")).getUsername();
        OrderService orderService=new OrderService();
        List<Order> orderList =orderService.getOrdersByUsername(name);
        Account account = (Account) session.getAttribute("loginAccount");
        if (account!=null){
            String viewpage="viewOrderList";
            UserLogService userLogService=new UserLogService();
            userLogService.insertUserLog(account,viewpage);
        }
        session.setAttribute("orderList",orderList);
        req.getRequestDispatcher(ORDERS_LIST).forward(req,resp);
    }
}
