<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file='css/styles.css' %>
    </style>
</head>
<body>


<if ${error==true}>
<div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
</if>
<form method="post" action="/login">
    <label name="email" > email
        <input id="email" name="email">
    </label></br>
    <label for password="password"> password
        <input id="password" name="password">
    </label></br>
    <input type="submit" value="login in DB">
</form>

</body>
</html>
