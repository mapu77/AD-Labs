package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "DBServlet")
public class DBServlet extends HttpServlet {
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String cadena = request.getParameter("cadena");
        Integer habs = Integer.valueOf(request.getParameter("habs"));
        String direccion = request.getParameter("direccion");
        Integer calle = Integer.valueOf(request.getParameter("calle"));
        String codigo_postal = request.getParameter("codigo_postal");
        String ciudad = request.getParameter("ciudad");

        Connection connection = null;
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:./example.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet resultSet = statement.executeQuery("SELECT max(id_hotel) as max_id FROM hoteles");
            resultSet.next();
            int lastId = resultSet.getInt("max_id");
            String sqlSentence = "insert into hoteles values(" + (lastId + 1) + ",'" + nombre + "','" + cadena + "'," + habs + ",'" + direccion + "'," + calle + ",'" + codigo_postal + "','" + ciudad + "',NULL, NULL);";
            statement.executeUpdate(sqlSentence);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        response.setStatus(201);
        doGet(request, response);
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Boolean delete = Boolean.valueOf(request.getParameter("delete"));
        Connection connection = null;

        out.write("<html>");
        out.write("<head><title>Listado de hoteles</title></head>");
        out.write("<body>");
        out.write("<h2>Listado de Hoteles</h2>");
        out.write("<table>");
        out.write("<tr>");
        out.write("<th>Nombre</th>");
        out.write("<th>Cadena</th>");
        out.write("<th>Dirección</th>");
        out.write("<th>Núm. Habitaciones</th>");
        if (delete) out.write("<th>Acción</th>");
        out.write("</tr>");
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:./example.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet resultSet = statement.executeQuery("SELECT * FROM hoteles");
            while (resultSet.next()) {
                out.write("<tr>");
                out.write("<td>" + resultSet.getString("nom_hotel") + "</td>");
                out.write("<td>" + resultSet.getString("cadena") + "</td>");
                out.write("<td>" + resultSet.getString("calle") + " " + resultSet.getInt("numero") + ", "
                        + resultSet.getString("codigo_postal") + " " + resultSet.getString("ciudad") + "</td>");
                out.write("<td>" + resultSet.getInt("numb_hab") + "</td>");
                if (delete) out.write("<th><a href='deleteServlet?id_hotel="+resultSet.getInt("id_hotel")+"'>Eliminar</a></th>");
                out.write("</tr>");
            }
            out.write("</table>");
            out.println("<br><a href='index.jsp'>&lt Atrás</a>");
            out.println("</body>");
            out.println("</html");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

}