<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>


<div id="Catalog">
    <form action="updateAccount" method="post">

        <h3>User Information</h3>

        <table>
            <tr>
                <td>User ID:</td>
                <td>${sessionScope.loginAccount.username}</td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repeatedPassword" /></td>
            </tr>
        </table>






        <h3>Account Information</h3>

        <table>
            <tr>
                <td>First name:</td>
                <td><input type="text" name="account.firstName" value="${sessionScope.loginAccount.firstName}"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="account.lastName" value="${sessionScope.loginAccount.lastName}"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" size="40" name="account.email" value="${sessionScope.loginAccount.email}"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="account.phone" value="${sessionScope.loginAccount.phone}"></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" size="40" name="account.address1" value="${sessionScope.loginAccount.address1}"></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" size="40" name="account.address2" value="${sessionScope.loginAccount.address2}"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="account.city" value="${sessionScope.loginAccount.city}"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" size="4" name="account.state" value="${sessionScope.loginAccount.state}"></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" size="10" name="account.zip" value="${sessionScope.loginAccount.zip}"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" size="15" name="account.country" value="${sessionScope.loginAccount.country}"></td>
            </tr>
        </table>

        <h3>Profile Information</h3>

        <table>
            <tr>
                <td>Language Preference:</td>
                <td><select name="account.languagePreference" id="account.languagePreference">
                    <option value="english">english</option>
                    <option value="chinese">chinese</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>Favourite Category:</td>
                <td><select name="account.favouriteCategoryId" id="account.favouriteCategoryId">
                    <option value="BIRDS">BIRDS</option>
                    <option value="CATS">CATS</option>
                    <option value="DOGS">DOGS</option>
                    <option value="FISH">FISH</option>
                    <option value="REPTILES">REPTILES</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>Enable MyList</td>
                <td><input type="checkbox" name="account.listOption"></td>
            </tr>
            <tr>
                <td>Enable MyBanner</td>
                <td><input type="checkbox" name="account.bannerOption"></td>
            </tr>

        </table>

        <br>
        <input type="submit" name="editAccount" value="Save Account Information" />

    </form>
    <br>
    <a href="listOrder">My Orders</a></div>

<%@ include file="../common/bottom.jsp"%>
