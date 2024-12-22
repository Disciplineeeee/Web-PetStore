<%@ include file="../common/top.jsp"%>

<div id="Catalog" class="div1" >
  <form action="newOrder" method="post">
    <table>
      <tr>
        <th colspan=2>Payment Details</th>
      </tr>
      <tr>
        <td>Card Type:</td>
        <td>
          <select name="cardType">
            <option value="Visa" selected="selected">Visa</option>
            <option value="MasterCard">MasterCard</option>
            <option value="American Express">American Express</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>Card Number:</td>
        <td>
          <input type="text" name="creditCard" value="999999999999">* Use a fake number!
        </td>
      </tr>
      <tr>
        <td>Expiry Date (MM/YYYY):</td>
        <td>
          <input type="text" name="expiryDate" value="12/03"/>
        </td>
      </tr>

      <tr>
        <th colspan=2>Billing Address</th>
      </tr>
      <tr>
        <td>First name:</td>
        <td>
          <input type="text" name="billToFirstName" value="${sessionScope.order.billToFirstName}" />
        </td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td>
          <input type="text" name="billToLastName" value="${sessionScope.order.billToLastName}" />
        </td>
      </tr>
      <tr>
        <td>Address 1:</td>
        <td>
          <input type="text" size="40" name="billAddress1" value="${sessionScope.order.billAddress1}" />
        </td>
      </tr>
      <tr>
        <td>Address 2:</td>
        <td>
          <input type="text" size="40" name="billAddress2" value="${sessionScope.order.billAddress2}" />
        </td>
      </tr>
      <tr>
        <td>City:</td>
        <td>
          <input type="text" name="billCity" value="${sessionScope.order.billCity}" />
        </td>
      </tr>
      <tr>
        <td>State:</td>
        <td>
          <input type="text" size="4" name="billState" value="${sessionScope.order.billState}" />
        </td>
      </tr>
      <tr>
        <td>Zip:</td>
        <td>
          <input type="text" size="10" name="billZip" value="${sessionScope.order.billZip}" />
        </td>
      </tr>
      <tr>
        <td>Country:</td>
        <td>
          <input type="text" size="15" name="billCountry" value="${sessionScope.order.billCountry}" />
        </td>
      </tr>

      <tr>
        <td colspan=2>
          <input type="checkbox" name="shippingAddressRequired"/>
          Ship to different address...
        </td>
      </tr>
    </table>
    <input type="button" name="newOrder" id="confirmButton" value="Continue"/>
  </form>
</div>

<div id="Catalog" style="display: none" class="div2">
  <form action="shippingForm" method="post">
    <table>
      <tr>
        <th colspan=2>Shipping Address</th>
      </tr>

      <tr>
        <td>First name:</td>
        <td><input type="text" name="shipToFirstName" value="${sessionScope.order.shipToFirstName}"/></td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td><input type="text" name="shipToLastName" value="${sessionScope.order.shipToLastName}"/></td>
      </tr>
      <tr>
        <td>Address 1:</td>
        <td><input type="text" size="40" name="shipAddress1" value="${sessionScope.order.shipAddress1}"/></td>
      </tr>
      <tr>
        <td>Address 2:</td>
        <td><input type="text" size="40" name="shipAddress2" value="${sessionScope.order.shipAddress2}"/></td>
      </tr>
      <tr>
        <td>City:</td>
        <td><input type="text" name="shipCity" value="${sessionScope.order.shipCity}"/></td>
      </tr>
      <tr>
        <td>State:</td>
        <td><input type="text" size="4" name="shipState" value="${sessionScope.order.shipState}"/></td>
      </tr>
      <tr>
        <td>Zip:</td>
        <td><input type="text" size="10" name="shipZip" value="${sessionScope.order.shipZip}"/></td>
      </tr>
      <tr>
        <td>Country:</td>
        <td><input type="text" size="15" name="shipCountry" value="${sessionScope.order.shipCountry}"/></td>
      </tr>

    </table>
    <input type="submit" name="newOrder" id="confirmButton" value="Continue"/>
  </form>
</div>
<div class="div3" style="display: none" id="Catalog">
  <table>
    <tr>
      <th align="center" colspan="2">
        Order #${sessionScope.order.orderId}
        <fmt:formatDate value="${sessionScope.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
      </th>
    </tr>
    <tr>
      <th colspan="2">Payment Details</th>
    </tr>
    <tr>
      <td>Card Type:</td>
      <td><c:out value="${sessionScope.order.cardType}" /></td>
    </tr>
    <tr>
      <td>Card Number:</td>
      <td><c:out value="${sessionScope.order.creditCard}" /> * Fake number!</td>
    </tr>
    <tr>
      <td>Expiry Date (MM/YYYY):</td>
      <td><c:out value="${sessionScope.order.expiryDate}" /></td>
    </tr>
    <tr>
      <th colspan="2">Billing Address</th>
    </tr>
    <tr>
      <td>First name:</td>
      <td><c:out value="${sessionScope.order.billToFirstName}" /></td>
    </tr>
    <tr>
      <td>Last name:</td>
      <td><c:out value="${sessionScope.order.billToLastName}" /></td>
    </tr>
    <tr>
      <td>Address 1:</td>
      <td><c:out value="${sessionScope.order.billAddress1}" /></td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td><c:out value="${sessionScope.order.billAddress2}" /></td>
    </tr>
    <tr>
      <td>City:</td>
      <td><c:out value="${sessionScope.order.billCity}" /></td>
    </tr>
    <tr>
      <td>State:</td>
      <td><c:out value="${sessionScope.order.billState}" /></td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td><c:out value="${sessionScope.order.billZip}" /></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><c:out value="${sessionScope.order.billCountry}" /></td>
    </tr>
    <tr>
      <th colspan="2">Shipping Address</th>
    </tr>
    <tr>
      <td>First name:</td>
      <td><c:out value="${sessionScope.order.shipToFirstName}" /></td>
    </tr>
    <tr>
      <td>Last name:</td>
      <td><c:out value="${sessionScope.order.shipToLastName}" /></td>
    </tr>
    <tr>
      <td>Address 1:</td>
      <td><c:out value="${sessionScope.order.shipAddress1}" /></td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td><c:out value="${sessionScope.order.shipAddress2}" /></td>
    </tr>
    <tr>
      <td>City:</td>
      <td><c:out value="${sessionScope.order.shipCity}" /></td>
    </tr>
    <tr>
      <td>State:</td>
      <td><c:out value="${sessionScope.order.shipState}" /></td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td><c:out value="${sessionScope.order.shipZip}" /></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><c:out value="${sessionScope.order.shipCountry}" /></td>
    </tr>
    <tr>
      <td>Courier:</td>
      <td><c:out value="${sessionScope.order.courier}" /></td>
    </tr>
    <tr>
      <td colspan="2">Status: <c:out value="${sessionScope.order.status}" /></td>
    </tr>
    <tr>
      <td colspan="2">
        <table>
          <tr>
            <th>Item ID</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total Cost</th>
          </tr>
          <c:forEach var="lineItem" items="${sessionScope.order.lineItems}">
            <tr>
              <td>
                <a href="itemForm?itemId=${lineItem.item.itemId}">${lineItem.item.itemId}</a>
              </td>
              <td><c:if test="${lineItem.item != null}">
                ${lineItem.item.attribute1}
                ${lineItem.item.attribute2}
                ${lineItem.item.attribute3}
                ${lineItem.item.attribute4}
                ${lineItem.item.attribute5}
                ${lineItem.item.product.name}
              </c:if>
                <c:if test="${lineItem.item == null}">
                  <i>{description unavailable}</i>
                </c:if></td>

              <td>${lineItem.quantity}</td>
              <td><fmt:formatNumber value="${lineItem.unitPrice}" pattern="$#,##0.00" /></td>
              <td><fmt:formatNumber value="${lineItem.total}" pattern="$#,##0.00" /></td>
            </tr>
          </c:forEach>
          <tr>
            <th colspan="5">
              Total: <fmt:formatNumber value="${sessionScope.order.totalPrice}" pattern="$#,##0.00" />
            </th>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>
<%@ include file="../common/bottom.jsp"%>