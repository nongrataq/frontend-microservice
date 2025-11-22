<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ПРОФИЛЬ</title>
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
        main {
            display: flex;
            flex: 1;
            width: 100%;
            background: linear-gradient(120deg, #f5f5f5 65%, #bdbdbd 100%);
            justify-content: center;
            padding: 40px 20px;
        }
        .profile {
            max-width: 1200px;
            width: 100%;
        }
        .profile-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        .profile h2 {
            font-size: 28px;
            color: #616161;
        }
        .order-button {
            display: inline-block;
        }
        .info {
            padding: 1rem;
            border-radius: 8px;
            margin-bottom: 2rem;
            background: rgba(255,255,255,0.6);
            box-shadow: 0 2px 8px #0001;
        }
        .cards {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 24px;
        }
        .cards__card {
            background: linear-gradient(90deg,#f5f5f5 60%,#bdbdbd 100%);
            border: 3px solid #bdbdbd;
            border-radius: 14px;
            box-shadow: 0 2px 8px #0001;
            padding: 20px;
            transition: border-color 0.5s, box-shadow 0.3s;
            position: relative;
        }
        .cards__card:hover {
            border-color: #616161;
            box-shadow: 0 6px 24px #0002;
        }
        .cards__card::after {
            content: '';
            position: absolute;
            inset: 0;
            pointer-events:none;
            opacity: 0;
            border-radius: inherit;
            transition: opacity .32s;
            z-index: 1;
        }
        .cards__card:hover::after {
            background: rgba(158,158,158,0.08);
            opacity: 1;
        }
        .cards__card__info {
            position: relative;
            z-index: 2;
        }
        .card__image {
            width: 100%;
            height: 160px;
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 12px;
        }
        .card__name {
            font-size: 18px;
            font-weight: bold;
            color: #424242;
            margin-bottom: 8px;
        }
        .card__description {
            font-size: 14px;
            color: #616161;
            margin-bottom: 16px;
            line-height: 1.4;
        }
        .cards__card form {
            position: relative;
            z-index: 2;
        }
        button.grey-btn {
            width: 100%;
        }
        .grey-btn.main {
            background: rgba(255,255,255,0.9);
        }
        footer {
            background: linear-gradient(90deg, #757575 0%, #616161 50%, #424242 100%);
            border-top: 3px solid #bdbdbd;
            box-shadow: 0 -6px 20px #00000033;
            padding: 32px 20px;
            text-align: center;
        }
        footer .container {
            max-width: 1400px;
            margin: 0 auto;
        }
        footer p {
            color: #e0e0e0;
            margin: 8px 0;
            font-size: 15px;
            letter-spacing: 0.5px;
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
            <a class="grey-btn main" href="${pageContext.request.contextPath}/">Главная</a>
            <a class="grey-btn secondary" href="${pageContext.request.contextPath}/logout">Выход</a>
        </div>
    </div>
</header>
<main>
    <div class="profile">
        <div class="profile-header">
            <h2>Ваш профиль</h2>
            <a href="${pageContext.request.contextPath}/cards" class="grey-btn main order-button">+ Заказать новую карту</a>
        </div>
        <div class="info">
            <strong>Email:</strong> <c:out value="${email}"/>
        </div>
        <div class="cards">
            <c:forEach items="${requestScope.cards}" var="card">
                <div class="cards__card">
                    <div class="cards__card__info">
                        <img class="card__image" src="${card.cardImageLink}" alt="Card image"/>
                        <div class="card__name">${card.cardName}</div>
                        <div class="card__description">${card.description}</div>
                    </div>
                    <form action="${pageContext.request.contextPath}/info" method="post">
                        <input type="hidden" name="cardId" value="${card.id}">
                        <button type="submit" class="grey-btn main">Информация по карте</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<footer>
    <div class="container">
        <p><strong>F-BANK</strong> — самый лучший банк страны</p>
        <p>&copy; 2025 Все права защищены</p>
    </div>
</footer>
</body>
</html>
