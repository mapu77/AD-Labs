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
        String numVuelo = request.getParameter("numero_vuelo").equals("") ? null : request.getParameter("numero_vuelo");
        String ciudadOrigen = request.getParameter("ciudad_origen").equals("") ? null : request.getParameter("ciudad_origen");
        String ciudadDestino = request.getParameter("ciudad_destino").equals("") ? null : request.getParameter("ciudad_destino");
        String companyia = request.getParameter("companyia").equals("") ? null : request.getParameter("companyia");
        Statement statement = MyDB.getStatment();

        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM vuelos WHERE");
            if (numVuelo != null) {
                sql.append(" num_vuelo = '" + numVuelo + "' AND");
            }
            if (ciudadOrigen != null) {
                sql.append(" origen = '" + ciudadOrigen + "' AND");
            }
            if (ciudadDestino != null) {
                sql.append(" destino = '" + ciudadDestino + "' AND");
            }
            if (companyia != null) {
                sql.append(" companyia = '" + companyia + "' AND");
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
            writer.println("<th>Id Vuelo</th>");
            writer.println("<th>Nº Vuelo</th>");
            writer.println("<th>Compañia</th>");
            writer.println("<th>Origen</th>");
            writer.println("<th>Hora Salida</th>");
            writer.println("<th>Destino</th>");
            writer.println("<th>Hora Llegada</th>");
            writer.println("</tr>");
            while (rs.next()) {
                writer.println("<tr>");
                writer.println("<td>"+rs.getString("id_vuelo")+"</td>");
                writer.println("<td>"+rs.getString("num_vuelo")+"</td>");
                writer.println("<td>"+rs.getString("companyia")+"</td>");
                writer.println("<td>"+rs.getString("origen")+"</td>");
                writer.println("<td>"+rs.getString("hora_salida")+"</td>");
                writer.println("<td>"+rs.getString("destino")+"</td>");
                writer.println("<td>"+rs.getString("hora_llegada")+"</td>");
                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
            response.setStatus(200);
        } catch (SQLException ex) {
            Logger.getLogger(GestionVuelosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "Error SQL, try to restart the database");
        }
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
