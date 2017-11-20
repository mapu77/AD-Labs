package servlets;

import static utils.Utils.buildPostBody;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HotelManagementServletRest extends HttpServlet {

    private static final String REST_URL = "http://localhost:8080/REST-HotelsWS/webresources/generic";

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
        URL url = new URL(REST_URL + "/consulta?id_hotel=" + id + "&fecha=" + date);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String emptySeats = br.readLine();
        br.close();
        request.getSession().setAttribute("emptyRoomsREST", emptySeats);
        response.sendRedirect("integrationrest.jsp");
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
        Map<String, Object> postParams = new LinkedHashMap<>();
        postParams.put("id_hotel", Integer.valueOf(request.getParameter("hotelId")));
        postParams.put("fecha", Integer.valueOf(request.getParameter("hotelDate")));

        URL url = new URL(REST_URL + "/reserva");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        byte[] postDataBytes = buildPostBody(postParams);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String ocuppiedSeats = br.readLine();
        br.close();

        request.getSession().setAttribute("occupiedRoomsREST", ocuppiedSeats);
        response.sendRedirect("integrationrest.jsp");
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
