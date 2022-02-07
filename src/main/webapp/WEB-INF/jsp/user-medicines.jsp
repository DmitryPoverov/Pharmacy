<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User medicines list</title>
</head>
<body>

<%@ include file="header.jsp"%>

<c:if test="${not empty sessionScope.request && sessionScope.request.equals('Покупка была отменена!')}">
    <div style ="color: red">
        <p><fmt:message key="error.purchase"/> </p>
            ${sessionScope.remove('request')}
    </div>
</c:if>

<c:if test="${not empty sessionScope.request && sessionScope.request.equals('Покупка была совершена!')}">
    <div style ="color: blue">
        <p><fmt:message key="message.purchase"/></p>
            ${sessionScope.remove('request')}
    </div>
</c:if>

<c:if test="${not empty sessionScope.quantityError && sessionScope.quantityError.equals('1')}">
    <div style ="color: red">
        <p><fmt:message key="error.quantity" /></p>
            ${sessionScope.remove('quantityError')}
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/user-cart" method="post">
    <c:forEach var="medicine" items="${sessionScope.medicines}">
        <h3>
            <label>
                <input type="checkbox" name="medicineId" value="${medicine.id}"/>
                    ${medicine.name} (${medicine.country}) - ${medicine.price}$ <br>
                <fmt:message key="medicine.quantity" /><input type="number" name="quantity" value="0"/>
            </label>
        </h3>
    </c:forEach>
    <button type="submit"><fmt:message key="button.medicines.add.to.cart" /></button>
</form>

</body>
</html>
