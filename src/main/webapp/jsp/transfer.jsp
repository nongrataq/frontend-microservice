<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Перевод средств</title>
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
            max-width: 600px;
            width: 100%;
        }
        .container h2 {
            font-size: 28px;
            color: #616161;
            margin-bottom: 20px;
        }
        .form-block {
            background: linear-gradient(90deg,#f5f5f5 60%,#bdbdbd 100%);
            border: 3px solid #bdbdbd;
            border-radius: 14px;
            box-shadow: 0 2px 8px #0001;
            padding: 24px;
            margin-bottom: 24px;
        }
        .form-group {
            margin-bottom: 16px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            color: #424242;
            margin-bottom: 6px;
            font-size: 15px;
        }
        .form-group input {
            width: 100%;
            padding: 10px 12px;
            border: 2px solid #bdbdbd;
            border-radius: 8px;
            font-size: 15px;
            outline: none;
            transition: border-color 0.3s;
            font-family: Verdana, Arial, sans-serif;
        }
        .form-group input:focus {
            border-color: #616161;
        }
        .actions {
            display: flex;
            gap: 12px;
        }
        .actions form, .actions a {
            flex: 1;
        }
        .actions .grey-btn {
            width: 100%;
            text-align: center;
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
        <h2>Перевод средств</h2>
        <div class="form-block">
            <form action="${pageContext.request.contextPath}/transfer-confirm" method="post">
                <input type="hidden" name="cardId" value="${cardId}">

                <div class="form-group">
                    <label for="recipient">ФИО получателя:</label>
                    <input type="text" id="recipient" name="recipientFio" required placeholder="Иван Иванович Иванов">
                </div>

                <div class="form-group">
                    <label for="account">Номер счёта получателя:</label>
                    <input type="text" id="account" name="recipientAccount" required placeholder="12345678901234567890">
                </div>

                <div class="form-group">
                    <label for="amount">Сумма перевода (₽):</label>
                    <input type="number" id="amount" name="amount" required placeholder="1000" step="0.01" min="0">
                </div>

                <div class="form-group">
                    <label for="description">Назначение платежа:</label>
                    <input type="text" id="description" name="description" placeholder="Оплата счёта">
                </div>

                <div class="actions">
                    <button type="submit" class="grey-btn main">Продолжить</button>
                    <a href="javascript:history.back()" class="grey-btn secondary">Отмена</a>
                </div>
            </form>
        </div>
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