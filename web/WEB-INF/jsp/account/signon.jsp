<%@ include file="../common/top.jsp"%>


<script>
  window.onload = function () {
    // 点击验证码图片时刷新
    document.getElementById('img').onclick = function () {
      this.src = "checkCode?time=" + new Date().getTime();
    }

    // 表单提交事件处理
    const form = document.querySelector('form');
    form.addEventListener('submit', function (e) {
      e.preventDefault(); // 阻止默认提交行为，等待验证码验证

      const userCheckCode = document.querySelector('[name="checkcode"]').value;
      if (!userCheckCode) {
        alert('请输入验证码');
        return; // 验证码为空时不提交表单
      }

      // 验证用户输入的验证码
      fetch('/verifyCode', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ userCheckCode })
      })
              .then(response => response.json())
              .then(data => {
                if (data.valid) {
                  form.submit(); // 验证成功，提交表单
                } else {
                  alert('验证码错误，请重新输入');
                  document.getElementById('img').click(); // 刷新验证码
                }
              })
              .catch(error => {
                console.error('验证请求出错:', error);
              });
    });
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