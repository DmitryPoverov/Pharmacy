<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h3>Список пользователей из базы данных:</h3>
<form action="${pageContext.request.contextPath}/admin-users" method="post">
    <c:forEach var="user" items="${requestScope.users}">
        <h3>
            Id:${user.id}, ${user.name} - [${user.email}] - ${user.role}
            <input type="hidden" name="id" value="${user.id}"/>
            <input type="hidden" name="name" value="${user.name}"/>
            <input type="hidden" name="email" value="${user.email}"/>
            <input type="hidden" name="role" value="${user.role}"/>
        </h3>
    </c:forEach>
</form>
</body>
</html>
