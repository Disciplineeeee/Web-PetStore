<%@ include file="../common/top.jsp"%>


<div id="BackLink">
  <a href="mainForm">Return to MainMenu</a>
</div>

<div id="Catalog">


  <table>
    <tr>
      <th></th>
      <th>Product ID</th>
      <th>name</th>
    </tr>
    <c:forEach var="product" items="${sessionScope.searchProductList}">
    <tr>
      <td><a href="productForm?productId=${product.productId}">${product.description}</a></td>
      <td>${product.productId}</td>
      <td>${product.name}</td>
      </c:forEach>
    </tr>
  </table>
  <div id="Separator"> </div>
</div>

<%@ include file="../common/bottom.jsp"%>