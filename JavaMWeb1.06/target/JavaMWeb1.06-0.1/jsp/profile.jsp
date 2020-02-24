
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        <%@include file='css/styles.css' %>
    </style>

</head>
<body>
<h2>Приветствуем Вас на главной странице!</h2>
<div class="form-style-2-heading">${user.firstName}</div>
<div class="form-style-2-heading">${user.lastName}</div>
<a href="/logout">Выход</a>
</body>
</html>