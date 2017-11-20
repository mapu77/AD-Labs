package servlets;

import database.MyDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Statement statement = MyDB.getStatement();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) as exist FROM usuarios WHERE id_usuario = '" + username + "' AND password = '" + password + "'");
            rs.next();
            if (rs.getInt("exist") == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                session.setMaxInactiveInterval(30 * 60);
                response.sendRedirect("home.jsp");
            } else {
                response.sendError(401, "Your username or password are incorrect");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "This should not occur and in any case it is not your fault");
        } finally {
            MyDB.disconnect();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
