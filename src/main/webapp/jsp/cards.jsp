<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.11.2025
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${cardProducts}" var="cardProduct">
    <div class="card-product">
        <img src="${cardProduct.cardImageLink}">
        <h3>${cardProduct.cardName}</h3>
        <p>${cardProduct.description}</p>

        <form action="${pageContext.request.contextPath}/order-card" method="post">
            <input type="hidden" name="cardProductId" value="${cardProduct.id}">
            <input type="hidden" name="cardName" value="${cardProduct.cardName}">
            <button type="submit">Заказать карту</button>
        </form>
    </div>
</c:forEach>

</body>

</html>
