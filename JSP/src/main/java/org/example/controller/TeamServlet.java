package org.example.controller;

import org.example.dao.TeamDAO;
import org.example.model.Player;
import org.example.model.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/team")
public class TeamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = Integer.parseInt(request.getParameter("id"));

            TeamDAO teamDAO = new TeamDAO();
            Team team = teamDAO.getTeamById(teamId);
            List<Player> players = teamDAO.getPlayersByTeamId(teamId);
            request.setAttribute("team", team);
            request.setAttribute("players", players);
            request.getRequestDispatcher("/views/team.jsp").forward(request, response);
    }
}