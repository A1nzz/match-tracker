package org.example.controller;

import org.example.dao.GameDAO;
import org.example.model.Game;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/games")
public class GameServlet extends HttpServlet {
    private GameDAO gameDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        gameDAO = new GameDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            int matchId = Integer.parseInt(request.getParameter("matchId"));
            List<Game> games = gameDAO.getGamesByMatchId(matchId);
            request.setAttribute("games", games);
            forwardRequest(request, response);
        } catch (NumberFormatException e) {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid match ID.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("views/games.jsp").forward(request, response);
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