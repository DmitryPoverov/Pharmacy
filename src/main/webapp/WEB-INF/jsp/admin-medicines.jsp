<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin medicines</title>
</head>

<body>
<%@ include file="header.jsp" %>

<c:if test="${not empty requestScope.result && (requestScope.result.equals('Successfully deleted') || requestScope.result.equals('Successfully saving'))}">
    <div style ="color: blue">
        <p>${requestScope.result}</p>
    </div>
</c:if>
<c:if test="${not empty requestScope.result && (requestScope.result.equals('Was not deleted') || requestScope.result.equals('Was not saved'))}">
    <div style ="color: red">
        <p>${requestScope.result}</p>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/admin-medicines" method="post">
    <h3>
        <label id="add">Добавить лекарство в базу данных:<br>
            <input type="text" placeholder="name" required name="name">
            <input type="text" placeholder="county" required name="country"/>
            <input type="number" placeholder="price" required name="price"/>
            <input type="hidden" name="medicineAddition" value="medicineAddition"/>
            <button type="submit">Добавить</button>
        </label>
    </h3>
</form>

<h3>Список лекарств из базы данных:</h3>
<ol>
    <c:forEach var="medicine" items="${sessionScope.medicines}">
        <li>
            <form action="${pageContext.request.contextPath}/admin-medicines" method="post">
                <input type="hidden" name="id" value="${medicine.id}"/>
                <input type="hidden" name="name" value="${medicine.name}"/>
                <input type="hidden" name="country" value="${medicine.country}"/>
                <input type="hidden" name="price" value="${medicine.price}"/>
                <h3>${medicine.name} (${medicine.country}) - ${medicine.price}$
                    <button type="submit">Удалить</button>
                </h3>
            </form>
        </li>
    </c:forEach>
</ol>
<br>
</body>
</html>
