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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
            List<Match> matches = matchDAO.getMatchesByTournament(tournamentId);
            String tournamentName = matchDAO.getTournamentName(tournamentId);
            request.setAttribute("matches", matches);
            request.setAttribute("tournamentName", tournamentName);
            forwardRequest(request, response);
        } catch (NumberFormatException e) {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tournament ID.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("views/matches.jsp").forward(request, response);
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