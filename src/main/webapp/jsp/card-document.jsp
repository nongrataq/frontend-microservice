<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Документ</title>
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
            max-width: 800px;
            width: 100%;
        }
        .container h2 {
            font-size: 28px;
            color: #616161;
            margin-bottom: 20px;
        }
        .document-block {
            background: linear-gradient(90deg,#f5f5f5 60%,#bdbdbd 100%);
            border: 3px solid #bdbdbd;
            border-radius: 14px;
            box-shadow: 0 2px 8px #0001;
            padding: 32px;
            margin-bottom: 24px;
        }
        .doc-header {
            text-align: center;
            border-bottom: 2px solid #bdbdbd;
            padding-bottom: 16px;
            margin-bottom: 24px;
        }
        .doc-header h3 {
            color: #424242;
            font-size: 20px;
            margin-bottom: 8px;
        }
        .doc-header p {
            color: #616161;
            font-size: 14px;
        }
        .doc-content {
            color: #424242;
            font-size: 15px;
            line-height: 1.8;
        }
        .doc-row {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid rgba(158,158,158,0.1);
        }
        .doc-row:last-child {
            border-bottom: none;
        }
        .doc-row strong {
            color: #424242;
            font-weight: bold;
        }
        .doc-row span {
            color: #616161;
        }
        .back-link {
            display: inline-block;
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
        <h2>Документ</h2>
        <div class="document-block">
            <div class="doc-header">
                <h3><c:out value="${document.docType}"/></h3>
                <p>F-BANK — Официальный документ</p>
            </div>

            <div class="doc-content">
                <div class="doc-row">
                    <strong>ID документа:</strong>
                    <span><c:out value="${document.id}"/></span>
                </div>
                <div class="doc-row">
                    <strong>Дата создания:</strong>
                    <span><c:out value="${document.createdDate}"/></span>
                </div>
                <div class="doc-row">
                    <strong>ФИО пользователя:</strong>
                    <span><c:out value="${document.userFio}"/></span>
                </div>
                <div class="doc-row">
                    <strong>Номер карты:</strong>
                    <span><c:out value="${document.cardNumber}"/></span>
                </div>
            </div>
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