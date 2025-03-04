<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Admin Tournaments</title>
  <<script src="${pageContext.request.contextPath}/js/admin_tournaments.js" defer></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin_tournaments.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tournaments.css">

</head>
<body>
<h1>Admin Tournaments</h1>
<div class="tournament-container">
  <c:if test="${not empty items}">
    <c:forEach var="tournament" items="${items}">
      <div class="tournament-card">
        <h2>${tournament.name}</h2>
        <p><strong>Start Date:</strong> ${tournament.startDate}</p>
        <p><strong>End Date:</strong> ${tournament.endDate}</p>
        <p class="prize-pool"><strong>Prize Pool:</strong> $${tournament.prizePool}</p>
        <p class="organizer"><strong>Organizer:</strong> ${tournament.organizer}</p>
        <button class="button" onclick="openModal('edit', { id: ${tournament.id}, name: '${tournament.name}', startDate: '${tournament.startDate}', endDate: '${tournament.endDate}', prizePool: ${tournament.prizePool}, organizer: '${tournament.organizer}' })">Edit</button>
        <button class="button delete" onclick="deleteTournament(${tournament.id})">Delete</button>
      </div>
    </c:forEach>
  </c:if>
  <c:if test="${empty items}">
    <p>No tournaments available.</p>
  </c:if>
</div>
<button class="button" onclick="openModal('add', null)">Add New Tournament</button>

<!-- Модальное окно -->
<div id="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h2 id="modalTitle">Add/Edit Tournament</h2>
    </div>
    <form id="tournamentForm" method="post" action="tournaments_admin">
      <input type="hidden" name="id" id="tournamentId">
      <label for="tournamentName">Name:</label>
      <input type="text" name="name" id="tournamentName" required>
      <label for="tournamentStartDate">Start Date:</label>
      <input type="date" name="startDate" id="tournamentStartDate" required>
      <label for="tournamentEndDate">End Date:</label>
      <input type="date" name="endDate" id="tournamentEndDate" required>
      <label for="tournamentPrizePool">Prize Pool:</label>
      <input type="number" name="prizePool" id="tournamentPrizePool" required>
      <label for="tournamentOrganizer">Organizer:</label>
      <input type="text" name="organizer" id="tournamentOrganizer" required>
      <div class="modal-footer">
        <button type="submit" value="Create" class="modal-button">Save</button>
        <button type="button" class="modal-button" onclick="closeModal()">Cancel</button>
      </div>
    </form>

  </div>
</div>
</body>
</html>