<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User medicines list</title>
</head>
<body>
<%@ include file="header.jsp"%>
<c:if test="${not empty requestScope.request && requestScope.request.equals('Покупка была отменена!')}">
    <div style ="color: red">
        <p>${requestScope.request}</p>
    </div>
</c:if>
<c:if test="${not empty requestScope.request && requestScope.request.equals('Покупка была совершена!')}">
    <div style ="color: blue">
        <p>${requestScope.request}</p>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/user-medicines" method="post">
    <c:forEach var="medicine" items="${sessionScope.medicines}">
        <h3>
            <label>
                <input type="checkbox" name="medicineId" value="${medicine.id}"/>
                    ${medicine.name} (${medicine.country}) - ${medicine.price} $ <br>
                Количество лекарства к покупке:<input type="number" name="quantity" value="0"/>
            </label>
        </h3>
    </c:forEach>
    <button type="submit">Добавить лекарства в корзину</button>
</form>
</body>
</html>
