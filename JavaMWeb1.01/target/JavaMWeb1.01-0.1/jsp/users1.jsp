
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
        <th>First name</th>
        <th>Last name</th>
        <th colspan="2">Action</th>
    </tr>
    ​
    <c:forEach var="user" items="${usersFromServer}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/deleteUsers">
                    <label for="first-name">First name
                        <input  class="input-field" type="text" id="first-name" name="first-name">
                    </label>
                    <input type="submit" value="Delete user">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                    <input type="input-field" hidden name="first-name" value="${user.firstName}"/>
                    <input type="submit" value="Update"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

