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
        String cadena = request.getParameter("cadena").equals("") ? null : request.getParameter("cadena");
        String ciudad = request.getParameter("ciudad").equals("") ? null : request.getParameter("ciudad");
        Statement statement = MyDB.getStatment();

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
            PrintWriter writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<style>");
            writer.println("table, th, td {border: 1px solid black;    border-collapse: collapse; padding: 10px}");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>Id Hotel</th>");
            writer.println("<th>Nombre hotel</th>");
            writer.println("<th>Cadena</th>");
            writer.println("<th>Nº habitaciones</th>");
            writer.println("<th>Calle</th>");
            writer.println("<th>Número</th>");
            writer.println("<th>Código postal</th>");
            writer.println("<th>Ciudad</th>");
            writer.println("<th>Provincia</th>");
            writer.println("<th>Pais</th>");
            writer.println("</tr>");
            while (rs.next()) {
                writer.println("<tr>");
                writer.println("<td>"+rs.getString("id_hotel")+"</td>");
                writer.println("<td>"+rs.getString("nom_hotel")+"</td>");
                writer.println("<td>"+rs.getString("cadena")+"</td>");
                writer.println("<td>"+rs.getString("numb_hab")+"</td>");
                writer.println("<td>"+rs.getString("calle")+"</td>");
                writer.println("<td>"+rs.getString("numero")+"</td>");
                writer.println("<td>"+rs.getString("codigo_postal")+"</td>");
                writer.println("<td>"+rs.getString("ciudad")+"</td>");
                writer.println("<td>"+rs.getString("provincia")+"</td>");
                writer.println("<td>"+rs.getString("pais")+"</td>");
                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
            response.setStatus(200);
        } catch (SQLException ex) {
            Logger.getLogger(GestionVuelosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
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
        Integer num = Integer.valueOf(request.getParameter("num"));
        String cp = request.getParameter("cp");
        String ciudad = request.getParameter("ciudad");
        String provincia = request.getParameter("provincia");
        String pais = request.getParameter("pais");
        Integer num_hab = Integer.valueOf(request.getParameter("num_hab"));
        Statement statement = MyDB.getStatment();
        try {
            ResultSet rs = statement.executeQuery("SELECT MAX(id_hotel) as max_id_hotel FROM hoteles");
            rs.next();
            Integer id_hotel = rs.getInt("max_id_hotel") + 1;
            statement.executeUpdate("insert into hoteles values(" + id_hotel + ", '" + nombre + "', '" + cadena + "'," + num_hab + ",'" + calle + "'," + num + ",'" + cp + "','"+ciudad+"','"+provincia+"','"+pais+"')");
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
