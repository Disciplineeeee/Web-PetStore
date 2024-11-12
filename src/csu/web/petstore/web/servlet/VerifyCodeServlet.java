package csu.web.petstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCheckCode");
        String correctCode = (String) req.getSession().getAttribute("checkcode_session");

        resp.setContentType("application/json");
        if (correctCode != null && correctCode.equalsIgnoreCase(userCode)) {
            resp.getWriter().write("{\"valid\": true}");
        } else {
            resp.getWriter().write("{\"valid\": false}");
        }
    }
}
