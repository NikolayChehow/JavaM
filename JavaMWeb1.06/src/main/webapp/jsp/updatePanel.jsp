<%--
  Created by IntelliJ IDEA.
  User: 00sna
  Date: 03.02.2020
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Update User</title>
    <style>
        <%@include file='css/styles.css' %>
    </style>
</head>
<body>
<div class="form-style-2">
    <table style=" width: 100%; border: 4px double black;">
        <table align="form-style-2" border="2px" bgcolor="#ffe4b5" >
        <tr>
            <td style="border: 1px solid black; text-align: center">
                <a href="${pageContext.servletContext.contextPath}/admin/admin">back to AdminPanel</a>
            </td>
            <div class="form-style-2-heading">
            </div>

        </tr>
    </table>
    <div class="form-style-2-heading">
        Change User!
    </div>
    <table>
        <table align="form-style-2" border="2px" bgcolor="#ffe4b5" >
        <tr>
<%--            <th>id</th>--%>
            <th>firstName</th>
            <th>lastName</th>
            <th>email</th>
            <th>role</th>
            <th>state</th>

<%--            <th>password</th>--%>
        </tr>
        <c:forEach items="${usersFromDB}" var="User">
            <tr>
<%--                <th>${User.id}</th>--%>
                <th>${User.firstName}</th>
                <th>${User.lastName}</th>
                <th>${User.email}</th>
                <th>${User.role.name()}</th>
                <th>${User.state.name()}</th>
<%--                <th>${User.password}</th>--%>
            </tr>

    </table>
    </table>
    </table>
    <h4>Input new parametrs</h4>
    <form method="post" action="/admin/updateUsers">

        <label for="firstName">FirstName
            <input id="firstName" name="firstName" value="${User.firstName}">
        </label></br>
        <label for="lastName">Lastname
            <input id="lastName" name="lastName" value="${User.lastName}">
        </label></br>
        <label for="email"> Email
            <input id="email" name="email" value="${User.email}">
        </label></br>
        <label for="password">Password
            <input type="password" id="password" name="password" value="${User.password}">
        </label></br>
        <label for="role"> Role
            <select name="role" id="role" size="1" >
                <option selected value="ADMIN">ADMIN</option>
                <option selected value="USER">USER</option>
            </select>
        </label></br>
        <label for="state"> State
            <select name="state" id="state" size="1">
                <option selected value="BANNED">BANNED</option>
                <option selected value="DELETED">DELETED</option>
                <option selected value="ACTIVE">ACTIVE</option>
            </select>
        </label></br>

        <input type="submit" value="update in DB!">
    </form>
    </c:forEach>
</div>
</body>
</html>
