<%@ include file="../common/top.jsp"%>


<script>
  window.onload=function (){
    document.getElementById("img").onclick=function (){
      this.src="checkCode?time"+new Date().getTime();
    }
  }
</script>


<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
  <form action="signOn" method="post">
    &nbsp&nbsp&nbsp
    <p>Please enter your username and password.</p>
    <c:if test="${requestScope.signOnMsg!= null}">
      <p> <font color="red">${requestScope.signOnMsg} </font> </p>
    </c:if>

<%--    <p>Username:<input name="username" type="text" value="" /> </p>--%>
<%--    <p>Password:<input name="password" type="password" value="" /> </p>--%>
<%--    <p>Checkcode:<input name="checkcode" type="text" value="" /></p>--%>
<%--    <p><img id="img" src="checkCode" alt=""></p>--%>

    <table>
      <tr>
        <td>Username:</td>
        <td><input name="username" type="text" value="" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input name="password" type="password" value="" /></td>
      </tr>
      <tr>
        <td>Verification code:</td>
        <td>
          <input type="text" name="checkcode" style="width: 60px" value="" >
          <img id="img" style="width: 88px;height: 22px;position: absolute;+margin-top:1px;margin-left: 5px" src="checkCode">
        </td>
      </tr>
    </table>


    <input type="submit" value="Login">
  </form>
  Need a user name and password?
  <a href="newAccountForm">Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>