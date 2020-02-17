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
<%--            <th>password</th>--%>
        </tr>
        <c:forEach items="${usersFromDB}" var="User">
            <tr>
<%--                <th>${User.id}</th>--%>
                <th>${User.firstName}</th>
                <th>${User.lastName}</th>
                <th>${User.email}</th>
<%--                <th>${User.password}</th>--%>
            </tr>
        </c:forEach>
    </table>
    </table>
    </table>
    <h4>Input new parametrs</h4>
    <form method="post" action="/admin/updateUsers">

        <label for="firstName">FirstName
            <input id="firstName" name="firstName">
        </label></br>
        <label for="lastName">Lastname
            <input id="lastName" name="lastName">
        </label></br>
        <label for="email">Email
            <input id="email" name="email">
        </label></br>
        <label for="password">Password
            <input type="password" id="password" name="password">
        </label></br>
        <label for="nameRole"> Name Role
        <select name="nameRole" id="nameRole" size="1">
            <option selected value="user">user</option>
            <option selected value="admin">admin</option>
        </select>
        </label></br>

        <input type="submit" value="update in DB!">
    </form>
</div>
</body>
</html>
