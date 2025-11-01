<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>F-BANK</title>
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
        .button-group button {
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
        .button-group button:hover,
        .button-group button:focus {
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
            display: flex;
            width: 100%;
            background-color: #616161;
            justify-content: center;
            align-items: center;
            height: 150px;
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
    </style>
</head>
<body>
    <header>
        <h2>F-BANK</h2>
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
                    <label for="firstName">Имя</label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Фамилия</label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="middleName">Отчество</label>
                    <input type="text" id="middleName" name="middleName">
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

    </footer>
</body>
</html>
