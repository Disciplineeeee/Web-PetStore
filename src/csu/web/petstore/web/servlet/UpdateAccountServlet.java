package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.service.AccountService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateAccountServlet extends HttpServlet {
    public static final String UPDATEACCOUNT = "/WEB-INF/jsp/account/editAccount.jsp";
    private Account account ;
    private String password;
    private String repeatPassword;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        password=req.getParameter("password");
        repeatPassword=req.getParameter("repeatedPassword");
        System.out.println(password+repeatPassword);
       account = new Account();
        if (!password.equals(repeatPassword)){
            System.out.println("updateps");
            req.getRequestDispatcher(UPDATEACCOUNT).forward(req,resp);
        }
        else{
            HttpSession session = req.getSession();
            account= (Account) session.getAttribute("loginAccount");
            account.setPassword(req.getParameter("password"));
            account.setFirstName(req.getParameter("account.firstName"));
            account.setLastName(req.getParameter("account.lastName"));
            account.setEmail(req.getParameter("account.email"));
            account.setPhone(req.getParameter("account.phone"));
            account.setAddress1(req.getParameter("account.address1"));
            account.setAddress2(req.getParameter("account.address2"));
            account.setCity(req.getParameter("account.city"));
            account.setState(req.getParameter("account.state"));
            account.setZip(req.getParameter("account.zip"));
            account.setCountry(req.getParameter("account.country"));
            account.setLanguagePreference(req.getParameter("account.languagePreference"));
            account.setFavouriteCategoryId(req.getParameter("account.favouriteCategoryId"));
            account.setListOption(Boolean.parseBoolean(req.getParameter("account.listOption")));
            account.setBannerOption(Boolean.parseBoolean(req.getParameter("account.bannerOption")));
            AccountService accountService = new AccountService();
            accountService.updateAccount(account);
            session.setAttribute("account",account);
            if (account!=null){
                String viewpage="changeAccountForm";
                UserLogService userLogService=new UserLogService();
                userLogService.insertUserLog(account,viewpage);
            }
            req.getRequestDispatcher(UPDATEACCOUNT).forward(req,resp);
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
