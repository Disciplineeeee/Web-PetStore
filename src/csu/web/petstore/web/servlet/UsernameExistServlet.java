package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Account account = new Account();
        account.setUsername(username);
        AccountService accountService = new AccountService();
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        if(accountService.getAccountByUsername(account.getUsername()) != null) {
            out.print("E");
        }
        else{
            out.print("N");
        }
        out.flush();
        out.close();
    }
}
