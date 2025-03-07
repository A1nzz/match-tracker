<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>Tournaments</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tournaments.css">
</head>
<body>
<h1>Tournaments</h1>
<div class="tournament-container">
    <c:if test="${not empty items}">
        <c:forEach var="tournament" items="${items}">
            <div class="tournament-card">
                <h2>${tournament.name}</h2>
                <p><strong>Start Date:</strong> ${tournament.startDate}</p>
                <p><strong>End Date:</strong> ${tournament.endDate}</p>
                <p class="prize-pool"><strong>Prize Pool:</strong> $${tournament.prizePool}</p>
                <p class="organizer"><strong>Organizer:</strong> ${tournament.organizer}</p>
                <a class="tournament-link" href="${pageContext.request.contextPath}/matches?tournamentId=${tournament.id}">View Matches</a>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty items}">
        <p>No tournaments available.</p>
    </c:if>
</div>
</body>
