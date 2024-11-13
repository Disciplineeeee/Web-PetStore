package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.service.CatalogService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import csu.web.petstore.domain.Product;

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCT = "/WEB-INF/jsp/catalog/search.jsp";
    private String name;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        name = req.getParameter("keyword");
        CatalogService catalogService = new CatalogService();
        List<Product> searchProductList =catalogService.searchProductList(name);
        HttpSession session = req.getSession();
        Account account= (Account) session.getAttribute("loginAccount");
        if (account!=null){
            String viewpage="searchProduct/"+name;
            UserLogService userLogService=new UserLogService();
            userLogService.insertUserLog(account,viewpage);
        }
        session.setAttribute("searchProductList",searchProductList);
        req.getRequestDispatcher(SEARCH_PRODUCT).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
