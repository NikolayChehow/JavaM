
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Web1.01</title>
    <style>
        <%@include file='css/styles.css' %>
    </style>
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
<table style=" width: 100%; border: 2px double black;">
    <tr>
        <td style="border: 1px solid deepskyblue; text-align: center">
            <a href="/users">toRegisteredUsers</a>
        </td>
        <td style="border: 1px solid deepskyblue; text-align: center">
            <a href="/updateUsers">toUpdateUsers</a>
        </td>
    </tr>
</table>
    </div>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Already registered!
    </div>
    <table>
        <tr>
            <th>First name</th>
            <th>Last name</th>
        <tr>
            <c:forEach items="${usersFromServer}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        </tr>
        </c:forEach>
    </table>
</div>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Delete user!
    </div>
    <form method="post" action="${pageContext.request.contextPath}/deleteUsers">
        <label for="first-name">First name
            <input  class="input-field" type="text" id="first-name" name="first-name">
        </label>
        <input type="submit" value="Delete user">
    </form>
</div>



</body>
</html>
