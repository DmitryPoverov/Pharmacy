<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<%@ include file="header.jsp"%>
<br>
<a href="${pageContext.request.contextPath}/admin-users"><button type="button"><fmt:message key="user.list" /></button></a>
<a href="${pageContext.request.contextPath}/admin-medicines"><button type="button"><fmt:message key="medicine.list" /></button></a>
</body>
</html>
