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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            int teamId = Integer.parseInt(request.getParameter("id"));
            TeamDAO teamDAO = new TeamDAO();
            Team team = teamDAO.getTeamById(teamId);
            List<Player> players = teamDAO.getPlayersByTeamId(teamId);
            request.setAttribute("team", team);
            request.setAttribute("players", players);
            forwardRequest(request, response);
        } catch (NumberFormatException e) {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid team ID.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/views/team.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while forwarding the request."); // Ошибка при forward
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}