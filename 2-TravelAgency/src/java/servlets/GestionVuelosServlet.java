package servlets;

import database.MyDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
        String ciudadOrigen = request.getParameter("ciudad_origen").equals("Todas") ? null : request.getParameter("ciudad_origen");
        String ciudadDestino = request.getParameter("ciudad_destino").equals("Todas") ? null : request.getParameter("ciudad_destino");
        String companyia = request.getParameter("companyia").equals("Todas") ? null : request.getParameter("companyia");
        Statement statement = MyDB.getStatement();

        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM vuelos WHERE");
            if (numVuelo != null) {
                sql.append(" num_vuelo = '" + numVuelo.replace("'", "''") + "' AND");
            }
            if (ciudadOrigen != null) {
                sql.append(" origen = '" + ciudadOrigen.replace("'", "''") + "' AND");
            }
            if (ciudadDestino != null) {
                sql.append(" destino = '" + ciudadDestino.replace("'", "''") + "' AND");
            }
            if (companyia != null) {
                sql.append(" companyia = '" + companyia.replace("'", "''") + "' AND");
            }
            sql.append(" 1=1");
            ResultSet rs = statement.executeQuery(sql.toString());
            List<List<String>> vuelosEncontrados = new ArrayList<>();
            while (rs.next()) {
                List<String> vuelo = new ArrayList();
                vuelo.add(rs.getString("id_vuelo"));
                vuelo.add(rs.getString("num_vuelo"));
                vuelo.add(rs.getString("companyia"));
                vuelo.add(rs.getString("origen"));
                vuelo.add(rs.getString("hora_salida"));
                vuelo.add(rs.getString("destino"));
                vuelo.add(rs.getString("hora_llegada"));
                vuelosEncontrados.add(vuelo);
            }
            request.setAttribute("vuelos", vuelosEncontrados);
            response.setStatus(200);
            request.getRequestDispatcher("vuelos/tablaVuelos.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GestionVuelosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "This should not occur and it is not your fault");
        } finally {
            MyDB.disconnect();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numVuelo = request.getParameter("num_vuelo").equals("") ? null : request.getParameter("num_vuelo");
        String companyia = request.getParameter("companyia").equals("") ? null : request.getParameter("companyia");
        String origen = request.getParameter("origen").equals("") ? null : request.getParameter("origen");
        String hora_salida = request.getParameter("hora_salida").equals("") ? null : request.getParameter("hora_salida");
        String destino = request.getParameter("destino").equals("") ? null : request.getParameter("destino");
        String hora_llegada = request.getParameter("hora_llegada").equals("") ? null : request.getParameter("hora_llegada");
        Statement statement = MyDB.getStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT MAX(id_vuelo) as max_id_vuelo FROM vuelos");
            rs.next();
            Integer id_vuelo = rs.getInt("max_id_vuelo") + 1;
            StringBuilder sql = new StringBuilder("insert into vuelos values(" + id_vuelo);
            sql.append(numVuelo == null ? (", " + numVuelo) : (", '" + numVuelo.replace("'", "''") + "'"));
            sql.append(companyia == null ? (", " + companyia) : (", '" + companyia.replace("'", "''") + "'"));
            sql.append(origen == null ? (", " + origen) : (", '" + origen.replace("'", "''") + "'"));
            sql.append(hora_salida == null ? (", " + hora_salida) : (", '" + hora_salida.replace("'", "''") + "'"));
            sql.append(destino == null ? (", " + destino) : (", '" + destino.replace("'", "''") + "'"));
            sql.append(hora_llegada == null ? (", " + hora_llegada) : (", '" + hora_llegada.replace("'", "''") + "'"));
            sql.append(")");
            statement.executeUpdate(sql.toString());
            request.getSession().setAttribute("success", "Vuelo creado correctamente");
            response.setStatus(201);
            response.sendRedirect("home.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(GestionVuelosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "This should not occur and in any case it is not your fault");
        } finally {
            MyDB.disconnect();
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
