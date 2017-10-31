package edu.adlabs.servlets;

import edu.adlabs.flights.FlightsWS;
import edu.adlabs.flights.FlightsWS_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightManagementServlet extends HttpServlet {

    private final FlightsWS service;

    public FlightManagementServlet() {
        service = new FlightsWS_Service().getFlightsWSPort();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("flightId"));
        int date = Integer.valueOf(request.getParameter("flightDate"));
        int emptySeats = service.consultaLibres(id, date);
        request.getSession().setAttribute("emptySeats", emptySeats);
        response.sendRedirect("index.jsp");
        response.setStatus(200);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("flightId"));
        int date = Integer.valueOf(request.getParameter("flightDate"));
        int ocuppiedSeats = service.reservaPlaza(id, date);
        request.getSession().setAttribute("occupiedSeats", ocuppiedSeats);
        response.sendRedirect("index.jsp");
        response.setStatus(200);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
