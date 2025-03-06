<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Team Information</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/team.css">
</head>
<body>
<h1>Team: ${team.name}</h1>
<div class="team-info">
    <img src="${team.logoUrl}" alt="${team.name} Logo" />
    <p><strong>Region:</strong> ${team.region}</p>
    <p><strong>Rating:</strong> ${team.rating}</p>
</div>
<h2>Players</h2>
<div class="player-container">
    <c:if test="${not empty players}">
        <c:forEach var="player" items="${players}">
            <div class="player-card">
                <p class="nickname">Nickname: ${player.nickname}</p>
                <p>Real Name: ${player.realName}</p>
                <p class="role">Role: ${player.role}</p>
                <p>Country: ${player.country}</p>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty players}">
        <p>No players available for this team.</p>
    </c:if>
</div>
</body>
</html>