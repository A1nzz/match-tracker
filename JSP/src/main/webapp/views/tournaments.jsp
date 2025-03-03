<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Tournament" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tournaments</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #1a1a1a;
            color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        h1 {
            margin-bottom: 20px;
            text-align: center;
        }

        .tournament-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            max-width: 1200px;
        }

        .tournament-card {
            background-color: #333333;
            border-radius: 10px;
            padding: 20px;
            width: 300px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
            transition: transform 0.2s;
        }

        .tournament-card:hover {
            transform: scale(1.05);
        }

        .tournament-card h2 {
            font-size: 1.5em;
            margin: 0 0 10px;
        }

        .tournament-card p {
            margin: 5px 0;
        }

        .prize-pool {
            color: #ffcc00;
            font-weight: bold;
        }

        .organizer {
            color: #ff7f50;
        }
    </style>
</head>
<h1>Tournaments</h1>
<div class="tournament-container">
    <%
        List<Tournament> tournaments = (List<Tournament>) request.getAttribute("tournaments");
        if (tournaments != null && !tournaments.isEmpty()) {
            for (Tournament tournament : tournaments) {
    %>
    <div class="tournament-card">
        <h2><%= tournament.getName() %></h2>
        <p><strong>Start Date:</strong> <%= tournament.getStartDate() %></p>
        <p><strong>End Date:</strong> <%= tournament.getEndDate() %></p>
        <p class="prize-pool"><strong>Prize Pool:</strong> $<%= tournament.getPrizePool() %></p>
        <p class="organizer"><strong>Organizer:</strong> <%= tournament.getOrganizer() %></p>
    </div>
    <%
        }
    } else {
    %>
    <p>No tournaments available.</p>
    <%
        }
    %>
</div>
</body>
</html>