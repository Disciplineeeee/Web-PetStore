<%--
  Created by IntelliJ IDEA.
  User: hidyouths
  Date: 2024/11/12
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="csu.web.petstore.web.servlet.LoginServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/login2.css"/>
</head>
<body >
<div class="box" >
    <h2>Login</h2>
    <form action="Login" method="post">

        <c:if test="${requestScope.loginMsg != null}">
            <p><font color=white>${requestScope.loginMsg}</font></p>
        </c:if>
        <div class="input-box">
            <label>账号</label>
            <input type="text" name="username"/>
        </div>
        <div class="input-box">
            <label>密码</label>
            <input type="password" name="password"/>
        </div>
        <div class="input-box">
            <label>验证码</label>
            <input type="text"  name="VerificationCode"/>
            <div class="verification">
                <img src="Verification">
            </div>

        </div>
        <div>
            <div class="btn-box">
                <a href="RegisterForm">去注册>></a>
                <div>
                    <button type="submit">登录</button>
                </div>
            </div>

        </div>
    </form>


</div>
</body>
</html>
