<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<form action="/admin-users" method="post">
    <c:forEach var="user" items="${requestScope.users}">
        <fieldset>
            <h3>${user.id} ${user.name} ${user.email} ${user.role}</h3>
            <input type="hidden" name="id" value="${user.id}"/>
            <input type="hidden" name="name" value="${user.name}"/>
            <input type="hidden" name="email" value="${user.email}"/>
            <input type="hidden" name="role" value="${user.role}"/>
        </fieldset>
    </c:forEach>
</form>

</body>
</html>
