<%--
  Created by IntelliJ IDEA.
  User: hidyouths
  Date: 2024/11/12
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<h2>My Orders</h2>

<table>
    <tr>
        <th>Order ID</th>
        <th>Date</th>
        <th>Total Price</th>
    </tr>

    <c:forEach var="order" items="${sessionScope.orderList}">
        <tr>
            <td>
                <a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a>
            </td>
            <td>
                <fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
            </td>
            <td>
                <fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../common/bottom.jsp"%>



