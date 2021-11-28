<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<%@include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/final-servlet" method="post">
    <c:forEach var="index" begin="0" end="${sessionScope.medicineDtos.size()-1}">
        <h3>
            ${sessionScope.medicineDtos.get(index).name}
                (${sessionScope.medicineDtos.get(index).country}) -
                    ${sessionScope.medicineDtos.get(index).price}$:
                        ${sessionScope.quantity[index]} <fmt:message key="medicines.items" />.
                <br>
                <fmt:message key="button.medicine.total.price" /> ${sessionScope.totalPriceEachMedicine.get(index)}$
        </h3>
    </c:forEach>
    <br>
    <h3>
        <fmt:message key="button.medicines.total.price" /> ${sessionScope.totalPrice}$
    </h3>
    <button type="submit" name="submitReject" value="1"><fmt:message key="button.cart.reset" /></button>
    <button type="submit" name="submitReject" value="2"><fmt:message key="button.cart.submit" /></button>
</form>
</body>
</html>
