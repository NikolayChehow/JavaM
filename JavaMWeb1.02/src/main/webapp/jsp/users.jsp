<%--
  Created by IntelliJ IDEA.
  User: 00sna
  Date: 02.02.2020
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>crud</title>
    <style>
        <%@include file='css/styles.css' %>
    </style>

</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Already registered!
    </div>
    <form method="post" action="/deleteUsers">
        <table>
            <table align="form-style-2" border="2px" bgcolor="#ffe4b5" >
            <tr>
                <th>id</th>
                <th>firstName</th>
                <th>lastName</th>
                <th>email</th>
<%--                <th>password</th>--%>
            </tr>
            <c:forEach items="${usersFromDB}" var="User">
                <tr>
                    <th>${User.id}</th>
                    <th>${User.firstName}</th>
                    <th>${User.lastName}</th>
                    <th>${User.email}</th>
<%--                    <th>${User.password}</th>--%>
                    <td>
                        <a href="/updateUsers?id=<c:out value='${User.id}' />">Edit</a>
                    </td>
                    <!---------Check box Delete----------->
                    <td id="deleteUsers">
                        <input type="checkbox" name="Delete" value=${User.id}>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!------------ button Clear Cart--------------->
        <div class="button-update">
            <input type="submit" name="Delete" value="Delete selected users">
        </div>
    </form>

    </br>
    </br>
    <div class="form-style-2-heading">
        <h4>add User in dataBase</h4>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/users">

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

        <input type="submit" value="Add user">
    </form>
    </br>
    </br>

</div>
</body>
</html>
</body>
</html>
