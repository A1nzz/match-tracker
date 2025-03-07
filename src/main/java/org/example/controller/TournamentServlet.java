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
        forwardRequest(req, resp);
    }


    private void forwardRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("views/tournaments.jsp").forward(request, response);
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