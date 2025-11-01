<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>РЕГИСТРАЦИЯ</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
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
            transition:
                    transform 0.22s cubic-bezier(.38,1.64,.41,1.03),
                    box-shadow 0.2s,
                    background 0.18s;
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
        .grey-container {
            background: linear-gradient(90deg,#f5f5f5 60%,#bdbdbd 100%);
            border: 3px solid #bdbdbd;
            border-radius: 14px;
            box-shadow: 0 2px 8px #0001;
            padding: 24px 32px;
            margin-bottom: 32px;
            transition: border-color 0.5s, box-shadow 0.3s;
            position: relative;
            width: max-content;
        }
        .grey-container:hover {
            border-color: #616161;
            box-shadow: 0 6px 24px #0002;
        }
        .grey-container::after {
            content: '';
            position: absolute;
            inset: 0;
            pointer-events:none;
            opacity: 0;
            border-radius: inherit;
            transition: opacity .32s;
            z-index: 1;
        }
        .grey-container:hover::after {
            background: rgba(158,158,158,0.08);
            opacity: 1;
        }
        .button-group button:hover, .button-group button:focus {
            transform: translateY(-2.5px) scale(1.03);
            box-shadow: 0 6px 14px #0002;
        }
        body {
            background: #f6f8fa;
            font-family: Verdana, Arial, sans-serif;
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-bottom: 30px;
            padding: 16px 24px;
            border-radius: 14px;
            align-items: center;
            box-shadow: 0 2px 8px #0001;
            transition: border-color 0.5s, box-shadow 0.3s;
            border: 3px solid transparent;
            width: max-content;
            position: relative;
        }
        .button-group:hover {
            box-shadow: 0 6px 24px #0002;
            z-index: 2;
        }
        button {
            padding: 9px 22px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            font-weight: 500;
            box-shadow: 0 1.5px 4px #0002;
            transition:
                    transform 0.22s cubic-bezier(.38,1.64,.41,1.03),
                    box-shadow 0.2s,
                    background 0.18s,
                    color 0.19s,
                    border 0.18s;
            position: relative;
        }
        button:hover,
        button:focus {
            transform: translateY(-2.5px) scale(1.03);
            box-shadow: 0 6px 14px #0002;
            z-index: 3;
        }
        .group-grey {
            border-color: #bdbdbd;
            background: linear-gradient(90deg,#f5f5f5 60%,#bdbdbd 100%);
        }
        .group-grey:hover {
            border-color:#616161
        }
        .button-group .main {
            background:rgba(255,255,255,0.9);
        }
        .button-group .secondary {
            background:rgba(255,255,255,0.6);
        }
        .button-group .extra {
            background:rgba(255,255,255,0.35);
        }
        .button-group::after {
            content: '';
            position: absolute;
            inset: 0;
            pointer-events:none;
            opacity: 0;
            border-radius: inherit;
            transition: opacity .32s;
            z-index:1;
        }
        .group-grey:hover::after {
            background:rgba(158,158,158,0.08);
            opacity:1;
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
            text-shadow: 0 2px 8px #00000011;
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
        main {
            display: flex;
            width: 100%;
            background-color: #bdbdbd;
            justify-content: center;
            align-items: center;
        }
        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 1rem;
        }
        .form input,
        .form button {
            margin: 8px 0;
            padding: 7px 16px;
            border-radius: 7px;
            border: 1px solid #bdbdbd;
            font-size: 15px;
            outline: none;
        }
        .form button {
            background: #616161;
            color: #fff;
            border: none;
            font-weight: bold;
            cursor: pointer;
            width: 100%;
        }
        .form button:hover {
            background: #424242;
        }
        .errors {
            background: #ff4444;
            color: white;
            padding: 1rem;
            border-radius: 5px;
            margin-bottom: 1rem;
        }
        .error-item {
            margin: 0.3rem 0;
        }
        .link {
            text-align: center;
            margin-top: 1rem;
        }
        .link a {
            color: #616161;
            text-decoration: none;
        }
        .link a:hover {
            text-decoration: underline;
        }
        footer {
            background-color: #616161;
            border-top: 2px solid #404040;
            padding: 2rem 0;
            text-align: center;
            margin-top: auto;
        }
        footer p {
            color: whitesmoke;
            margin: 0.5rem 0;
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
            <a class="grey-btn main" href="${pageContext.request.contextPath}/">Главная</a>
            <a class="grey-btn secondary" href="${pageContext.request.contextPath}/logout">Выход</a>
        </div>
    </div>
</header>
<main>
    <div class="grey-container">
        <h1>Регистрация</h1>
        <c:if test="${not empty requestScope.errors}">
            <div class="errors">
                <c:forEach items="${requestScope.errors}" var="error">
                    <div class="error-item">
                        <strong>${error.field}:</strong> ${error.message}
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <form class="form" action="${pageContext.request.contextPath}/sign-up" method="post">
            <div class="form-group">
                <label for="fio">ФИО</label>
                <input type="text" id="fio" name="fio" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Зарегистрироваться</button>
        </form>
        <div class="link">
            Уже есть аккаунт? <a href="${pageContext.request.contextPath}/sign-in">Войти</a>
        </div>
    </div>
</main>
<footer>
    <div class="container">
        <p><strong>F-BANK</strong> — самый лучший банк страны.</p>
        <p>&copy; 2025 Все права защищены.</p>
    </div>
</footer>
</body>
</html>