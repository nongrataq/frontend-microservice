<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ВХОД</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
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
        .main {
            background:rgba(255,255,255,0.9);
        }
        .secondary {
            background:rgba(255,255,255,0.6);
        }
        .extra {
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
            display: flex;
            width: 100%;
            background-color: #616161;
            justify-content: center;
            align-items: center;
            height: 150px;
        }
        main {
            display: flex;
            height: 80%;
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
        .grey-btn {
            display: inline-block;
            padding: 9px 22px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-family: Verdana, Arial, sans-serif;
            cursor: pointer;
            font-weight: 500;
            box-shadow: 0 1.5px 4px #0002;
            text-decoration: none;
            margin-right: 8px;
            transition:
                    transform 0.22s cubic-bezier(.38,1.64,.41,1.03),
                    box-shadow 0.2s,
                    background 0.18s,
                    color 0.19s,
                    border 0.18s;
            position: relative;
            color: #222;
        }
        .grey-btn:hover, .grey-btn:focus {
            transform: translateY(-2.5px) scale(1.03);
            box-shadow: 0 6px 14px #0002;
            z-index: 2;
        }
        .brand a {
            font-size: 1.5rem;
            font-weight: bold;
            color: #bdbdbd;
            text-decoration: none;
        }
        .brand a:hover {
            color: white;
        }
        .header__container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            margin: 0 auto;
            padding: 0 20px;
        }
        .header__menu {
            list-style: none;
            display: flex;
            gap: 1rem;
            align-items: center;
            justify-content: center;
        }
        .header__menu li a {
            padding: 0.5rem 1rem;
            transition: all 0.3s ease;
            text-decoration: none;
            white-space: nowrap;
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
        <ul class="header__menu">
            <c:if test="${not empty sessionScope.email}">
                <li><a href="${pageContext.request.contextPath}/profile" class="grey-btn main">Профиль</a></li>
                <li><a href="${pageContext.request.contextPath}/logout" class="grey-btn secondary">Выход</a></li>
            </c:if>
            <c:if test="${empty sessionScope.email}">
                <li><a href="${pageContext.request.contextPath}/sign-in" class="grey-btn main">Войти</a></li>
                <li><a href="${pageContext.request.contextPath}/sign-up" class="grey-btn secondary">Регистрация</a></li>
            </c:if>
        </ul>
    </div>
</header>
<main>
    <div class="grey-container">
        <h1>Добро пожаловать в F-Bank</h1>
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