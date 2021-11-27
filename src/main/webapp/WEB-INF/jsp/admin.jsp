<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<%@ include file="header.jsp"%>
<br>
<a href="${pageContext.request.contextPath}/admin-users"><button type="button">Список пользователей</button></a>
<a href="${pageContext.request.contextPath}/admin-medicines"><button type="button">Список лекарств</button></a>
</body>
</html>
