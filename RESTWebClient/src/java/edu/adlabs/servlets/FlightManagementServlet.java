package edu.adlabs.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightManagementServlet extends HttpServlet {

    private static String REST_URL = "http://localhost:8080/REST-FlightsWS/webresources/generic";

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
        int id = Integer.valueOf(request.getParameter("flightId"));
        int date = Integer.valueOf(request.getParameter("flightDate"));
        URL url = new URL(REST_URL + "/consulta?id_vuelo=" + id + "&fecha=" + date);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String emptySeats = br.readLine();

        request.getSession().setAttribute("emptySeats", emptySeats);
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
        Map<String, Object> postParams = new LinkedHashMap<>();
        postParams.put("id_hotel", Integer.valueOf(request.getParameter("flightId")));
        postParams.put("fecha", Integer.valueOf(request.getParameter("flightDate")));

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

        request.getSession().setAttribute("occupiedSeats", ocuppiedSeats);
        response.sendRedirect("index.jsp");
        response.setStatus(200);
    }

    private byte[] buildPostBody(Map<String, Object> postParams) throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : postParams.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        return postDataBytes;
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
