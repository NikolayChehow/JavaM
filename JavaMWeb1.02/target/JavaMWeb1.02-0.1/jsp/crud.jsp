<%--
  Created by IntelliJ IDEA.
  User: 00sna
  Date: 02.02.2020
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>crud</title>
    <style>
        <%@include file='css/styles.css' %>
    </style>

</head>
<body>
<div class="form-style-2">
<form method="post" action="/deleteUser">
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
                <td>
                    <a href="/updateUsers?id=<c:out value='${User.id}' />">Edit</a>
                </td>
                <!---------Check box Delete----------->
                <td id="deleteUser">
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
<h4>add User in dataBase</h4>
<form method="post" action="${pageContext.request.contextPath}/users">
    <label for="first-name">Firstname
        <input id="first-name" name="first-name">
    </label></br>
    <label for="last-name">Lastname
        <input id="last-name" name="last-name">
    </label></br>

    <input type="submit" value="inside in JDBC">
</form>
</br>
</br>

    </div>
</body>
</html>
</body>
</html>
