<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выписка по карте</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            background: #f6f8fa;
            font-family: Verdana, Arial, sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        header {
            background: linear-gradient(90deg, #424242 0%, #616161 50%, #757575 100%);
            box-shadow: 0 6px 20px #00000033;
            border-bottom: 3px solid #bdbdbd;
            padding: 0;
        }
        .header__container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            max-width: 1400px;
            margin: 0 auto;
            padding: 18px 40px;
        }
        .brand a {
            font-size: 28px;
            font-weight: 900;
            letter-spacing: 2px;
            background: linear-gradient(90deg, #bdbdbd, #e0e0e0);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            text-decoration: none;
            transition: letter-spacing 0.3s;
        }
        .brand a:hover {
            letter-spacing: 3px;
        }
        .header__links {
            display: flex;
            gap: 12px;
            align-items: center;
        }
        .grey-btn {
            display: inline-block;
            padding: 10px 24px;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            font-family: Verdana, Arial, sans-serif;
            cursor: pointer;
            font-weight: 600;
            box-shadow: 0 2px 6px #0002;
            text-decoration: none;
            transition: transform 0.22s cubic-bezier(.38,1.64,.41,1.03), box-shadow 0.2s, background 0.18s;
            position: relative;
            color: #222;
            background: rgba(255,255,255,0.9);
        }
        .grey-btn:hover, .grey-btn:focus {
            transform: translateY(-2.5px) scale(1.03);
            box-shadow: 0 6px 14px #0002;
        }
        .grey-btn.secondary {
            background: rgba(255,255,255,0.6);
            color: #616161;
            border: 2px solid #bdbdbd;
        }
        main {
            display: flex;
            flex: 1;
            width: 100%;
            background: linear-gradient(120deg, #f5f5f5 65%, #bdbdbd 100%);
            justify-content: center;
            padding: 40px 20px;
        }
        .container {
            max-width: 1000px;
            width: 100%;
        }
        .container h2 {
            font-size: 28px;
            color: #616161;
            margin-bottom: 20px;
        }
        .table-wrapper {
            background: linear-gradient(90deg,#f5f5f5 60%,#bdbdbd 100%);
            border: 3px solid #bdbdbd;
            border-radius: 14px;
            box-shadow: 0 2px 8px #0001;
            overflow: hidden;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table thead {
            background: rgba(158,158,158,0.15);
        }
        table th {
            padding: 12px;
            text-align: left;
            font-weight: bold;
            color: #424242;
            border-bottom: 2px solid #bdbdbd;
        }
        table td {
            padding: 12px;
            border-bottom: 1px solid rgba(158,158,158,0.1);
            color: #616161;
        }
        table tr:hover {
            background: rgba(158,158,158,0.05);
        }
        .transaction-form {
            display: inline;
        }
        .transaction-form button {
            padding: 6px 16px;
            border: none;
            border-radius: 6px;
            background: rgba(255,255,255,0.9);
            color: #222;
            cursor: pointer;
            font-size: 13px;
            transition: transform 0.22s, box-shadow 0.2s;
            box-shadow: 0 2px 6px #0002;
        }
        .transaction-form button:hover {
            transform: translateY(-2px) scale(1.02);
            box-shadow: 0 4px 10px #0002;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
        }
        footer {
            background: linear-gradient(90deg, #757575 0%, #616161 50%, #424242 100%);
            border-top: 3px solid #bdbdbd;
            box-shadow: 0 -6px 20px #00000033;
            padding: 32px 20px;
            text-align: center;
        }
        footer p {
            color: #e0e0e0;
            margin: 8px 0;
            font-size: 15px;
        }
        footer p strong {
            color: #bdbdbd;
        }
    </style>
</head>
<body>
<header>
    <div class="header__container">
        <div class="brand">
            <a href="${pageContext.request.contextPath}/">F-BANK</a>
        </div>
        <div class="header__links">
            <a class="grey-btn main" href="${pageContext.request.contextPath}/profile">Профиль</a>
            <a class="grey-btn secondary" href="${pageContext.request.contextPath}/logout">Выход</a>
        </div>
    </div>
</header>
<main>
    <div class="container">
        <h2>Выписка по карте</h2>
        <div class="table-wrapper">
            <table>
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Сумма</th>
                    <th>Описание</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.transactions}" var="transaction">
                    <tr>
                        <td><c:out value="${transaction.date}"/></td>
                        <td><c:out value="${transaction.amount}"/></td>
                        <td><c:out value="${transaction.description}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/transaction-document" method="post" class="transaction-form">
                                <input type="hidden" name="transactionId" value="${transaction.id}">
                                <button type="submit">Документ</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <a href="javascript:history.back()" class="grey-btn secondary back-link">← Назад</a>
    </div>
</main>
<footer>
    <div class="container">
        <p><strong>F-BANK</strong> — управление вашими картами</p>
        <p>&copy; 2025 Все права защищены</p>
    </div>
</footer>
</body>
</html>