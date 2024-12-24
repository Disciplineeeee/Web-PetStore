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
  <form action="newAccount" method="post">
    <h3>User Information</h3>
    <c:if test="${requestScope.RegisterMsg !=null}">
    <p><font color="red">${requestScope.RegisterMsg}</font></p>
    </c:if>

    <table>
      <tr>
        <td>User ID:</td>
        <td><input name="userid" id="username"></td>
        <td id="isExistInfo"></td>
      </tr>
      <tr>
        <td>New password:</td>
        <td><input type="password" name="password" id="password"></td>
      </tr>
      <tr>
        <td>Repeat password:</td>
        <td><input type="password" name="repeatedPassword" id="repeatpassword"></td>
      </tr>
    </table>

    <%@ include file="includeAccountFields.jsp"%>

    <br>
    <table>
        <td>Verification code:</td>
        <td>
          <input type="text" name="checkcode" style="width: 60px" value="" >
          <img id="img" style="width: 88px;height: 22px;position: absolute;+margin-top:1px;margin-left: 5px" src="checkCode">
        </td>
      </tr>
    </table>

    <br>

    <input type="submit" value="Save Account Information">
</div>
<script src="js/check-username.js"></script>
<%@ include file="../common/bottom.jsp"%>
