package csu.web.petstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewAccountFormServlet extends HttpServlet {
    private static final String NEW_ACCOUNT_FORM="/WEB-INF/jsp/account/newAccount.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
    }
}
