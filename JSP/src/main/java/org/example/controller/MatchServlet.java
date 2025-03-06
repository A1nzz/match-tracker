package org.example.controller;

import org.example.dao.MatchDAO;
import org.example.model.Match;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/matches")
public class MatchServlet extends HttpServlet {

    private MatchDAO matchDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        matchDAO = new MatchDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
        List<Match> matches = matchDAO.getMatchesByTournament(tournamentId);
        String tournamentName = matchDAO.getTournamentName(tournamentId); // Получите название турнира

        request.setAttribute("matches", matches);
        request.setAttribute("tournamentName", tournamentName);

        request.getRequestDispatcher("views/matches.jsp").forward(request, response);

    }
}