package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.persistence.RecordDao;
import csu.web.petstore.persistence.impl.RecordDaoImpl;
import csu.web.petstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginServlet extends HttpServlet {

    private static final String LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";
    private String username;
    private String password;
    private String VerificationCode;
    private String Verification;
    private String loginMsg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.VerificationCode = req.getParameter("VerificationCode");
        this.Verification = (String) req.getSession().getAttribute("check_session");
        //У���û��������ȷ��
        if(!validate()){
            req.setAttribute("loginMsg",loginMsg);
            req.getRequestDispatcher(LOGIN_FORM).forward(req,resp);
        }else {
            AccountService accountService = new AccountService();
            Account loginAccount = accountService.getAccount(username,password);
            if(loginAccount == null){
                this.loginMsg = "�û������������";
                req.setAttribute("loginMsg",loginMsg);
                req.getRequestDispatcher(LOGIN_FORM).forward(req,resp);
            }else{
                if(this.VerificationCode.equals(this.Verification)){
                    HttpSession session = req.getSession();
                    session.setAttribute("loginAccount",loginAccount);
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    RecordDao userService = new RecordDaoImpl();
                    userService.InsertToRecord(username,"��¼"+" -----------------------------"+formatter.format(date),0);
                    resp.sendRedirect("mainForm");
                }else{
                    this.loginMsg = "��֤�����";
                    req.setAttribute("loginMsg",loginMsg);
                    req.getRequestDispatcher(LOGIN_FORM).forward(req,resp);
                }

                //��post������ʹ��send��Ҳ����post����

            }

        }

    }
    private boolean validate(){
        if(this.username == null ||this.username.equals("")){
            this.loginMsg = "�û�������Ϊ��";
            return false;
        }
        if(this.password == null ||this.password.equals("")){
            this.loginMsg = "���벻��Ϊ��";
            return false;
        }
        if(this.VerificationCode == null ||this.VerificationCode.equals("")){
            this.loginMsg = "��֤�벻��Ϊ��";
            return false;
        }
        return true;
    }


}


