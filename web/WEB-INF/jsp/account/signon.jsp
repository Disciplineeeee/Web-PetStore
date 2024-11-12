<%@ include file="../common/top.jsp"%>


<script>
  window.onload=function (){
    document.getElementById("img").onclick=function (){
      this.src="checkCode?time"+new Date().getTime();
    }
  }

</script>


<div id="Catalog">
  <form action="signOn" method="post">
    <p>Please enter your username and password.</p>
    <c:if test="${requestScope.signOnMsg!= null}">
      <p> <font color="red">${requestScope.signOnMsg} </font> </p>
    </c:if>

    <p>Username:<input name="username" type="text" value="" /> </p>
    <p>Password:<input name="password" type="password" value="" /> </p>
    <p>Checkcode:<input name="checkcode" type="text" value="" /></p>
    <p><img id="img" src="checkCode" alt=""></p>

    <input type="submit" value="Login">
  </form>
  Need a user name and password?
  <a href="newAccountForm">Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>