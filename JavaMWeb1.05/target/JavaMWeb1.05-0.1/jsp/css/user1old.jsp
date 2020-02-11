<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AllUsers</title>
</head>
<body>
<table style=" width: 100%; border: 4px double black;">
    <tr>
        <td style="border: 1px solid black; text-align: center">
            <a href="${pageContext.servletContext.contextPath}/add">Add new user</a>
        </td>
    </tr>
</table>
​
<p align="center">All Users</p>
<table align="center" border="1px">
    <tr>
        <th>User Id</th>
        <th>User Name</th>
        <th>User Last Name</th>

        <th colspan="2">Action</th>
    </tr>
    ​
    <c:forEach var="user" items="${usersFromServer}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>

            <td>
                <form action="${pageContext.servletContext.contextPath}/deleteUsers" method="post">
                    <input type="number" hidden name="id" value="${user.id}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/updateUsers" method="get">
                    <input type="number" hidden name="id" value="${user.id}"/>
                    <input type="submit" value="Update"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>




<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--    <title>AllUsers</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<table style=" width: 100%; border: 4px double black;">--%>
<%--    <tr>--%>
<%--        <td style="border: 1px solid black; text-align: center">--%>
<%--            <a href="${pageContext.servletContext.contextPath}/add">Add new user</a>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--</table>--%>
<%--​--%>
<%--<p align="center">All Users</p>--%>
<%--<table align="center" border="1px">--%>
<%--    <tr>--%>
<%--        <th>User Id</th>--%>
<%--        <th>User Name</th>--%>
<%--        <th>User Age</th>--%>
<%--        <th>User email</th>--%>
<%--        <th>password</th>--%>
<%--        <th colspan="2">Action</th>--%>
<%--    </tr>--%>
<%--    ​--%>
<%--    <c:forEach var="user" items="${usersFromDB}">--%>
<%--        <tr>--%>
<%--            <td>${user.id}</td>--%>
<%--            <td>${user.name}</td>--%>
<%--            <td>${user.age}</td>--%>
<%--            <td>${user.email}</td>--%>
<%--            <td>${user.password}</td>--%>
<%--            <td>--%>
<%--                <form action="${pageContext.servletContext.contextPath}/delete" method="post">--%>
<%--                    <input type="number" hidden name="id" value="${user.id}"/>--%>
<%--                    <input type="submit" value="Delete"/>--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <form action="${pageContext.servletContext.contextPath}/update" method="get">--%>
<%--                    <input type="number" hidden name="id" value="${user.id}"/>--%>
<%--                    <input type="submit" value="Update"/>--%>
<%--                </form>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<%--</body>--%>
<%--</html>--%>







<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    --%>
<%--    <title>Web1.01</title>--%>
<%--    <style>--%>
<%--        <%@include file='css/styles.css' %>--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="form-style-2">--%>
<%--    <div class="form-style-2-heading">--%>
<%--        Already registered!--%>
<%--    </div>--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <th>First name</th>--%>
<%--            <th>Last name</th>--%>
<%--        <tr>--%>
<%--            <c:forEach items="${usersFromServer}" var="user">--%>
<%--        <tr>--%>
<%--            <td>${user.firstName}</td>--%>
<%--            <td>${user.lastName}</td>--%>
<%--        </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</div>--%>

<%--<div class="form-style-2">--%>
<%--    <div class="form-style-2-heading">--%>
<%--        Please register user!--%>
<%--    </div>--%>
<%--    <form method="post" action="${pageContext.request.contextPath}/users">--%>
<%--        <label for="first-name">First name--%>
<%--            <input  class="input-field" type="text" id="first-name" name="first-name">--%>
<%--        </label>--%>
<%--        <label for="last-name">Last name--%>
<%--            <input class="input-field" type="text" id="last-name" name="last-name">--%>
<%--        </label>--%>
<%--        <input type="submit" value="Add user">--%>
<%--    </form>--%>
<%--    <div class="form-style-2-heading">--%>
<%--        Please delete user!--%>
<%--    </div>--%>
<%--    <form method="post" action="${pageContext.request.contextPath}/deleteUsers">--%>
<%--        <label for="first-name">First name--%>
<%--            <input  class="input-field" type="text" id="first-name" name="first-name">--%>
<%--        </label>--%>
<%--        <input type="submit" value="Delete user">--%>
<%--    </form>--%>
<%--    <div class="form-style-2-heading">--%>
<%--        Please change last Name!--%>
<%--    </div>--%>
<%--    <form method="post" action="${pageContext.request.contextPath}/updateUsers">--%>
<%--        <label for="first-name">First name--%>
<%--            <input  class="input-field" type="text" id="first-name" name="first-name">--%>
<%--        </label>--%>
<%--        <label for="last-name">Last name for change--%>
<%--            <input class="input-field" type="text" id="last-name" name="last-name">--%>
<%--        </label>--%>
<%--        <input type="submit" value="Change user">--%>
<%--    </form>--%>
<%--</div>--%>


<%--</body>--%>
<%--</html>--%>
