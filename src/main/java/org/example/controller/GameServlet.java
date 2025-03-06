package org.example.controller;

import org.example.dao.GameDAO;
import org.example.dao.MatchDAO;
import org.example.model.Game;
import org.example.model.Match;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchId = Integer.parseInt(request.getParameter("matchId"));
        List<Game> games = gameDAO.getGamesByMatchId(matchId);

        request.setAttribute("games", games);

        request.getRequestDispatcher("views/games.jsp").forward(request, response);

    }
}