package servlets;

import database.MyDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestionVuelosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numVuelo = request.getParameter("num_vuelo");
        String companyia = request.getParameter("companyia");
        String origen = request.getParameter("origen");
        String hora_salida = request.getParameter("hora_salida");
        String destino = request.getParameter("destino");
        String hora_llegada = request.getParameter("hora_llegada");
        Statement statement = MyDB.getStatment();
        try {
            ResultSet rs = statement.executeQuery("SELECT MAX(id_vuelo) as max_id_vuelo FROM vuelos");
            rs.next();
            Integer id_vuelo = rs.getInt("max_id_vuelo") + 1;
            statement.executeUpdate("insert into vuelos values(" + id_vuelo + ", '" + numVuelo + "', '" + companyia + "','" + origen + "','" + hora_salida + "','" + destino + "','" + hora_llegada + "')");
            response.setStatus(201);
            response.sendRedirect("menu.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(GestionVuelosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
        }
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
