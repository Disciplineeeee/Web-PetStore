package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Item;
import csu.web.petstore.persistence.OrderDao;
import csu.web.petstore.persistence.RecordDao;
import csu.web.petstore.persistence.impl.OrderDaoImpl;
import csu.web.petstore.persistence.impl.RecordDaoImpl;
import csu.web.petstore.service.CartService;
import csu.web.petstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewOrderServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";
    OrderDao orderDao = new OrderDaoImpl();
    CartService cartService = new CartService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        String username = loginAccount.getUsername();
        CatalogService catalogService = new CatalogService();
        String productname = req.getParameter("productName");
        String itemId = req.getParameter("itemId");
        Item item = catalogService.getItem(itemId);
        String productId = item.getProduct().getProductId();
        String descn = item.getProduct().getDescription();
        String adress = req.getParameter("address");
        String numString = req.getParameter("num");
        int num = Integer.parseInt(numString);
        String cost = req.getParameter("totalprice");
        BigDecimal listprice = new BigDecimal(cost);


        orderDao.addOrder(username,itemId,num,listprice,adress,productname,descn,productId);

        Account account = (Account) session.getAttribute("loginAccount");
        if(account != null){
            RecordDao userService = new RecordDaoImpl();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            userService.InsertToRecord(account.getUsername(),"�µ�"+itemId+" -----------------------------"+formatter.format(date),0);
        }
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print("success");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

