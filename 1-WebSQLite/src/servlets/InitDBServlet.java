package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "InitDBServlet")
public class InitDBServlet extends HttpServlet {
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:./example.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists usuarios");
            statement.executeUpdate("drop table if exists vuelos");
            statement.executeUpdate("drop table if exists hoteles");

            statement.executeUpdate("create table usuarios (id_usuario string primary key, password string)");
            statement.executeUpdate("insert into usuarios values('Silvia','12345')");
            statement.executeUpdate("insert into usuarios values('Pepito','23456')");

            statement.executeUpdate("create table vuelos (id_vuelo integer primary key, num_vuelo string,companyia string, origen string, hora_salida string, destino string, hora_llegada string)");
            statement.executeUpdate("insert into vuelos values(1, '22', 'IBE','BCN','09:00','SVQ','10:25')");
            statement.executeUpdate("insert into vuelos values(2, '33', 'VUE','BCN','09:00','BRU','11:25')");

            statement.executeUpdate("create table hoteles (id_hotel integer primary key, nom_hotel string, cadena string,numb_hab integer,calle string, numero integer,codigo_postal string,ciudad string,provincia string,pais string)");
            statement.executeUpdate("insert into hoteles values(1, 'Plaza', 'Plaza',150,'Plaza Espanya',1, '08003', 'Barcelona','Barcelona','Espanya')");
            statement.executeUpdate("insert into hoteles values(2, 'W', 'Hilton',120,'Paseo maritimo',1, '08003', 'Barcelona','Barcelona','Espanya')");

            ResultSet rs = statement.executeQuery("select * from usuarios");

            while (rs.next()) {
                // read the result set
                out.println("<br>Id usuario = " + rs.getString("id_usuario"));
                out.println("Password = " + rs.getString("password"));
            }

            rs = statement.executeQuery("select * from hoteles");

            while (rs.next()) {
                // read the result set
                out.println("<br>Id hotel = " + rs.getString("id_hotel"));
                out.println("Nombre = " + rs.getString("nom_hotel"));
            }
            out.println("<br><a href='index.jsp'>&lt Atrás</a>");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
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