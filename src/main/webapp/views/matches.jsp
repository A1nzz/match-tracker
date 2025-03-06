<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Matches</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/matches.css">
</head>
<body>
<h1>Matches for Tournament: ${tournamentName}</h1>
<div class="match-container">
    <c:if test="${not empty matches}">
        <c:forEach var="match" items="${matches}">
            <div class="match-card">
                <p class="team-name"><strong>Radiant Team:</strong><a href="${pageContext.request.contextPath}/team?id=${match.teamRadiantId}">${match.teamRadiantName}</a></p>
                <p class="team-name"><strong>Dire Team:</strong><a href="${pageContext.request.contextPath}/team?id=${match.teamDireId}">${match.teamDireName}</a></p>
                <p class="match-type"><strong>Match Type:</strong> ${match.matchTypeName}</p>
                <p class="match-date"><strong>Date:</strong> ${match.matchDate}</p>
                <p><strong>Best of:</strong> ${match.bestOf}</p>
                <a href="${pageContext.request.contextPath}/games?matchId=${match.id}">Check</a>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty matches}">
        <p>No matches available for this tournament.</p>
    </c:if>
</div>
</body>
</html>