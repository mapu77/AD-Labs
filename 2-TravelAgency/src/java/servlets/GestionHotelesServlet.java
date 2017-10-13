package servlets;

import database.MyDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionHotelesServlet extends HttpServlet {

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
        String nombre = request.getParameter("nombre").equals("") ? null : request.getParameter("nombre");
        String cadena = request.getParameter("cadena").equals("Todas") ? null : request.getParameter("cadena");
        String ciudad = request.getParameter("ciudad").equals("Todas") ? null : request.getParameter("ciudad");
        Statement statement = MyDB.getStatement();

        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM hoteles WHERE");
            if (nombre != null) {
                sql.append(" nom_hotel = '" + nombre + "' AND");
            }
            if (cadena != null) {
                sql.append(" cadena = '" + cadena + "' AND");
            }
            if (ciudad != null) {
                sql.append(" ciudad = '" + ciudad + "' AND");
            }
            sql.append(" 1=1");
            ResultSet rs = statement.executeQuery(sql.toString());
            List<List<String>> hotelesEncontrados = new ArrayList<>();
            while (rs.next()) {
                List<String> hotel = new ArrayList();
                hotel.add(rs.getString("id_hotel"));
                hotel.add(rs.getString("nom_hotel"));
                hotel.add(rs.getString("cadena"));
                hotel.add(rs.getString("numb_hab"));
                hotel.add(rs.getString("calle"));
                hotel.add(rs.getString("numero"));
                hotel.add(rs.getString("codigo_postal"));
                hotel.add(rs.getString("ciudad"));
                hotel.add(rs.getString("provincia"));
                hotel.add(rs.getString("pais"));
                hotelesEncontrados.add(hotel);
            }
            request.setAttribute("hoteles", hotelesEncontrados);
            response.setStatus(200);
            request.getRequestDispatcher("tablaHoteles.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GestionVuelosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "Error SQL, try to restart the database");

        }
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
        String nombre = request.getParameter("nombre");
        String cadena = request.getParameter("cadena");
        String calle = request.getParameter("calle");
        Integer num = request.getParameter("num").equals("") ? null : Integer.valueOf(request.getParameter("num"));
        String cp = request.getParameter("cp");
        String ciudad = request.getParameter("ciudad");
        String provincia = request.getParameter("provincia");
        String pais = request.getParameter("pais");
        Integer num_hab = request.getParameter("num_hab").equals("") ? null : Integer.valueOf(request.getParameter("num_hab"));
        Statement statement = MyDB.getStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT MAX(id_hotel) as max_id_hotel FROM hoteles");
            rs.next();
            Integer id_hotel = rs.getInt("max_id_hotel") + 1;
            statement.executeUpdate("insert into hoteles values(" + id_hotel + ", '" + nombre + "', '" + cadena + "'," + num_hab + ",'" + calle + "'," + num + ",'" + cp + "','" + ciudad + "','" + provincia + "','" + pais + "')");
            request.getSession().setAttribute("success", "Hotel creado correctamente");
            response.setStatus(201);
            response.sendRedirect("home.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(GestionVuelosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "Error SQL, try to restart the database");
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
