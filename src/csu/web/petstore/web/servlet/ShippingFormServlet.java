package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Order;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingFormServlet extends HttpServlet {
    private final String CONFIRMORDER="/WEB-INF/jsp/order/confirmOrder.jsp";

    private Order order;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        order = (Order) session.getAttribute("order");
        order.setShipToFirstName(req.getParameter("shipToFirstName"));
        order.setShipToLastName(req.getParameter("shipToLastName"));
        order.setShipAddress1(req.getParameter("shipAddress1"));
        order.setShipAddress2(req.getParameter("shipAddress2"));
        order.setShipCity(req.getParameter("shipCity"));
        order.setShipState(req.getParameter("shipState"));
        order.setShipZip(req.getParameter("shipZip"));
        order.setShipCountry(req.getParameter( "shipCountry"));
        session.setAttribute("order",order);
        Account account= (Account) session.getAttribute("loginAccount");
        if (account!=null){
            String viewpage="changeSippingForm";
            UserLogService userlogService=new UserLogService();
            userlogService.insertUserLog(account,viewpage);
        }
        req.getRequestDispatcher(CONFIRMORDER).forward(req,resp);
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
