package com.example.servlet;

import com.example.dao.TournamentDAO;
import com.example.model.Tournament;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/tournaments")
public class TournamentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Создаем экземпляр TournamentDAO
        TournamentDAO tournamentDAO = new TournamentDAO();

        // Получаем список турниров из DAO
        List<Tournament> tournaments = tournamentDAO.getAllTournaments();

        // Передаем список турниров в JSP
        request.setAttribute("tournaments", tournaments);
        request.getRequestDispatcher("views/tournaments.jsp").forward(request, response);

    }
}