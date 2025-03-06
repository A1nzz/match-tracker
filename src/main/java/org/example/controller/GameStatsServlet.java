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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int gameId = Integer.parseInt(request.getParameter("id"));

            GameDAO gameDAO = new GameDAO();
            List<GameStatsDTO> gameStats = gameDAO.getGameStatsForGame(gameId);
            request.setAttribute("gameStats", gameStats);
            request.getRequestDispatcher("/views/gameStats.jsp").forward(request, response);

    }
}
