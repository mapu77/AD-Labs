package edu.adlabs.servlets;

import edu.adlabs.hotels.HotelsWS;
import edu.adlabs.hotels.HotelsWS_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HotelManagementServlet extends HttpServlet {
    
    private final HotelsWS service;
    
    public HotelManagementServlet() {
        service = new HotelsWS_Service().getHotelsWSPort();
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
        int id = Integer.valueOf(request.getParameter("hotelId"));
        int date = Integer.valueOf(request.getParameter("hotelDate"));
        int emptyRooms = service.consultaLibres(id, date);
        request.getSession().setAttribute("emptyRooms", emptyRooms);
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
        int id = Integer.valueOf(request.getParameter("hotelId"));
        int date = Integer.valueOf(request.getParameter("hotelDate"));
        int ocuppiedRooms = service.reservaHabitacion(id, date);
        request.getSession().setAttribute("occupiedRooms", ocuppiedRooms);
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
