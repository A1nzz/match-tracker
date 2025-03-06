<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Match Statistics</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gameStats.css">
</head>
<body>
<h1>Game Statistics</h1>



    <div class="teams-container">
        <div class="team">
            <h2>Radiant Team</h2>
            <c:forEach var="stats" items="${gameStats}">
                <c:if test="${stats.team == 'Radiant'}">
                <div class="player">
                        <div class="player-header">
                            <p><strong>${stats.playerNickname}</strong></p>
                            <p><strong>${stats.heroName}</strong></p>
                        </div>
                        <div class="player-stats">
                            <div class="stat">
                                <p>${stats.kills}</p>
                                <p class="stat-label">Kills</p>
                            </div>
                            <div class="stat">
                                <p>${stats.deaths}</p>
                                <p class="stat-label">Deaths</p>
                            </div>
                            <div class="stat">
                                <p>${stats.assists}</p>
                                <p class="stat-label">Assists</p>
                            </div>
                            <div class="stat">
                                <p>${stats.lastHits}</p>
                                <p class="stat-label">Last Hits</p>
                            </div>
                            <div class="stat">
                                <p>${stats.goldPerMinute}</p>
                                <p class="stat-label">GPM</p>
                            </div>
                            <div class="stat">
                                <p>${stats.xpPerMinute}</p>
                                <p class="stat-label">XPM</p>
                            </div>
                            <div class="stat">
                                <p>${stats.netWorth}</p>
                                <p class="stat-label">Net Worth</p>
                            </div>
                            <div class="stat">
                                <p>${stats.finalLevel}</p>
                                <p class="stat-label">Level</p>
                            </div>
                        </div>
                        <p><strong>Items:</strong>
                        <ul class="items-list">
                            <c:forEach var="item" items="${stats.items}">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                        </p>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <div class="team">
            <h2>Dire Team</h2>
            <c:forEach var="stats" items="${gameStats}">
                <c:if test="${stats.team == 'Dire'}">

                <div class="player">
                        <div class="player-header">
                            <p><strong>${stats.playerNickname}</strong></p>
                            <p><strong>${stats.heroName}</strong></p>
                        </div>
                        <div class="player-stats">
                            <div class="stat">
                                <p>${stats.kills}</p>
                                <p class="stat-label">Kills</p>
                            </div>
                            <div class="stat">
                                <p>${stats.deaths}</p>
                                <p class="stat-label">Deaths</p>
                            </div>
                            <div class="stat">
                                <p>${stats.assists}</p>
                                <p class="stat-label">Assists</p>
                            </div>
                            <div class="stat">
                                <p>${stats.lastHits}</p>
                                <p class="stat-label">Last Hits</p>
                            </div>
                            <div class="stat">
                                <p>${stats.goldPerMinute}</p>
                                <p class="stat-label">GPM</p>
                            </div>
                            <div class="stat">
                                <p>${stats.xpPerMinute}</p>
                                <p class="stat-label">XPM</p>
                            </div>
                            <div class="stat">
                                <p>${stats.netWorth}</p>
                                <p class="stat-label">Net Worth</p>
                            </div>
                            <div class="stat">
                                <p>${stats.finalLevel}</p>
                                <p class="stat-label">Level</p>
                            </div>
                        </div>
                        <p><strong>Items:</strong>
                        <ul class="items-list">
                            <c:forEach var="item" items="${stats.items}">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                        </p>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>

</body>
</html>