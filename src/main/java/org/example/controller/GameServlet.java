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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int matchId = Integer.parseInt(request.getParameter("matchId"));
            List<Game> games = gameDAO.getGamesByMatchId(matchId);
            request.setAttribute("games", games);
            try {
                request.getRequestDispatcher("views/games.jsp").forward(request, response); // Обработка исключения
            } catch (ServletException | IOException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while forwarding the request."); // Ошибка при forward
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tournament ID or prize pool value.");
        }
    }
}