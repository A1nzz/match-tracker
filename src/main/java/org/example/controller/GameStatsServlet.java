package org.example.controller;

import org.example.dao.GameDAO;
import org.example.model.GameStatsDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/gameStats")
public class GameStatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            int gameId = Integer.parseInt(request.getParameter("id"));
            GameDAO gameDAO = new GameDAO();
            List<GameStatsDTO> gameStats = gameDAO.getGameStatsForGame(gameId);
            request.setAttribute("gameStats", gameStats);
            forwardRequest(request, response);
        } catch (NumberFormatException e) {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid game ID.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/views/gameStats.jsp").forward(request, response);
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
