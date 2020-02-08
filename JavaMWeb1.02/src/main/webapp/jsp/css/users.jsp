
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Web1.01</title>
    <style>
        <%@include file='styles.css' %>
    </style>
</head>
<body>

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
        Please register user!
    </div>
    <form method="post" action="${pageContext.request.contextPath}/users">
        <label for="first-name">First name
            <input  class="input-field" type="text" id="first-name" name="first-name">
        </label>
        <label for="last-name">Last name
            <input class="input-field" type="text" id="last-name" name="last-name">
        </label>
        <input type="submit" value="Add user">
    </form>
    <div class="form-style-2-heading">
        Please delete user!
    </div>
    <form method="post" action="${pageContext.request.contextPath}/deleteUsers">
        <label for="first-name">First name
            <input  class="input-field" type="text" id="first-name" name="first-name">
        </label>
        <input type="submit" value="Delete user">
    </form>
    <div class="form-style-2-heading">
        Please change last Name!
    </div>
    <form method="post" action="${pageContext.request.contextPath}/updateUsers">
        <label for="first-name">First name
            <input  class="input-field" type="text" id="first-name" name="first-name">
        </label>
        <label for="last-name">Last name for change
            <input class="input-field" type="text" id="last-name" name="last-name">
        </label>
        <input type="submit" value="Change user">
    </form>
</div>


</body>
</html>
