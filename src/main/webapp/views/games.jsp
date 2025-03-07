<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>Games</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/games.css">
</head>
<body>
<div class="game-container">
    <c:if test="${not empty games}">
        <c:forEach var="game" items="${games}">
            <div class="game-card">
                <p class="game-details"><strong>Duration:</strong> ${game.duration} minutes</p>
                <p class="game-details"><strong>Winner:</strong> ${game.winner}</p>
                <p class="game-details"><strong>Start Time:</strong> ${game.startTime}</p>
                <p class="game-details"><strong>Match ID:</strong> ${game.matchId}</p>
                <a class="stats-button" href="${pageContext.request.contextPath}/gameStats?id=${game.id}">View Detailed Stats</a>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty games}">
        <p>No games available for this tournament.</p>
    </c:if>
</div>
</body>
</html>