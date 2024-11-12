package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountFormServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/editAccount.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Account account= (Account) session.getAttribute("account");
        if (account!=null){
            String viewpage="viewEditAccountForm";
            UserLogService userLogService=new UserLogService();
            userLogService.insertUserLog(account,viewpage);
        }
        req.getRequestDispatcher(EDIT_ACCOUNT).forward(req,resp);
    }
}
