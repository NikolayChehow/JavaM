<%--
  Created by IntelliJ IDEA.
  User: 00sna
  Date: 03.02.2020
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<table>
    <tr>
        <th>id   </th>
        <th>name   </th>
        <th>lastName</th>
    </tr>
    <c:forEach items="${usersFromServer}" var="User">
        <tr>
            <th>${User.id}</th>
            <th>${User.firstName}</th>
            <th>${User.lastName}</th>
        </tr>
    </c:forEach>
</table>
</br>
</br>
<h5>Input new parametrs</h5>
<h4>update User in dataBase</h4>
<form method="post" action="/updateUser">
    <label for="first-name">Firstname
        <input id="first-name" name="first-name">
    </label></br>
    <label for="last-name">Lastname
        <input id="last-name" name="last-name">
    </label></br>
    <input type="submit" value="update in JDBC">
</form>

</body>
</html>
