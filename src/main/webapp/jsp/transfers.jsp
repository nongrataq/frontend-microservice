<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.11.2025
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form method="post" action="/transfer">
        <label for="amount">Сколько бабок перевести</label>
        <input id="amount" type="number" name="amount">
        <label for="who">Кому</label>
        //тут выбор из всех пользователей
        <c:forEach items="users" var="user">
            //<input name="sourseContractId" >

        </c:forEach>
    </form>

</body>
</html>
