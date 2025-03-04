package org.example.controller;

import org.example.dao.TournamentDAO;
import org.example.model.Tournament;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tournaments")
public class TournamentServlet extends HttpServlet {
    private TournamentDAO tournamentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        tournamentDAO = new TournamentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tournament> tournaments = tournamentDAO.getAllTournaments();
        req.setAttribute("items", tournaments);
        req.getRequestDispatcher("views/tournaments.jsp").forward(req, resp);
    }



}