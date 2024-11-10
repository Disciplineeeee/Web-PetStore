<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="registerForm" method="post">
        <h3>User Information</h3>
        <table>
            <tr>
                <td>User ID:</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="password" name="repeatedPassword"></td>
            </tr>
        </table>
        <%@ include file="includeAccountFields.jsp"%>
        <input type="submit" value="Save Account Information">
</div>

<%@ include file="../common/bottom.jsp"%>
