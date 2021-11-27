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
                    ${sessionScope.medicineDtos.get(index).price} $:
                        ${sessionScope.quantity[index]} items.
                <br>
                Total price of this medicine ${sessionScope.totalPriceEachMedicine.get(index)} $
        </h3>
    </c:forEach>
    <br>
    <h3>
        Total price ${sessionScope.totalPrice}
    </h3>
    <button type="submit" name="submitReject" value="1">Отменить покупку</button>
    <button type="submit" name="submitReject" value="2">Подтвердить покупку</button>
</form>
</body>
</html>
