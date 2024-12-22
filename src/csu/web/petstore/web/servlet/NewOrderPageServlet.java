package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.Order;
import csu.web.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.alibaba.fastjson.JSON;

public class NewOrderPageServlet extends HttpServlet {
    private static final String SHIPPING = "/WEB-INF/jsp/order/shippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int stage = Integer.parseInt(req.getParameter("stage"));
        System.out.println(stage);
        if(stage == 2){
            Order order = new Order();
            orderService.insertOrder(order);
            Cart cart = (Cart) session.getAttribute("cart");
            cart.removeAll();//此处删除存疑
            session.removeAttribute("cart");
        }
        stage++;
        session.setAttribute("stage", stage);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"stage\":" + stage + "}");
    }
}
