package org.example.controller;

import org.example.dao.TournamentDAO;
import org.example.model.Tournament;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/tournaments_admin/*")
public class AdminTournamentServlet extends HttpServlet {
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
        req.getRequestDispatcher("views/admin_tournaments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        if ("create".equals(action)) {
            Tournament tournament = new Tournament();
            tournament.setName(req.getParameter("name"));
            tournament.setStartDate(Date.valueOf(req.getParameter("startDate")));
            tournament.setEndDate(Date.valueOf(req.getParameter("endDate")));
            tournament.setPrizePool(Double.parseDouble(req.getParameter("prizePool")));
            tournament.setOrganizer(req.getParameter("organizer"));
            tournamentDAO.addTournament(tournament);
        } else if ("edit".equals(action)) {
            System.out.println("Received post request");

            Tournament tournament = new Tournament();
            tournament.setId(Integer.parseInt(req.getParameter("id")));
            tournament.setName(req.getParameter("name"));
            tournament.setStartDate(Date.valueOf(req.getParameter("startDate")));
            tournament.setEndDate(Date.valueOf(req.getParameter("endDate")));
            tournament.setPrizePool(Double.parseDouble(req.getParameter("prizePool")));
            tournament.setOrganizer(req.getParameter("organizer"));
            tournamentDAO.updateTournament(tournament);
        }
        resp.sendRedirect(req.getContextPath() + "/tournaments_admin");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String tournamentIdStr = pathInfo.substring(1); // Убираем начальный "/"
            try {
                int tournamentId = Integer.parseInt(tournamentIdStr); // Преобразуем в int

                tournamentDAO.deleteTournament(tournamentId); // Метод удаления из DAO
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tournament ID"); // Неверный ID
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tournament ID is required"); // Отсутствует ID
        }
    }



}