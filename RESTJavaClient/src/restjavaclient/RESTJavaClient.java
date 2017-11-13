/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restjavaclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class RESTJavaClient {

    public static void main(String[] args) {

        printMenu();
        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();
        while (opt != 0) {
            int id_vuelo;
            int fecha;
            int id_hotel;
            switch (opt) {
                case 1:
                    System.out.println("Enter the flight's id:");
                    id_vuelo = scanner.nextInt();
                    System.out.println("Enter the date: (aaaammdd)");
                    fecha = scanner.nextInt();
                    System.out.println("");
                    String emptySeats = doFlightsRequest("GET", id_vuelo, fecha);
                    if (Integer.valueOf(emptySeats) == -1) {
                        System.err.println("There's no flight with such id in such date");

                    } else {
                        System.out.println("Empty seats: " + emptySeats);
                    }
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Enter the flight's id:");
                    id_vuelo = scanner.nextInt();
                    System.out.println("Enter the date: (aaaammdd)");
                    fecha = scanner.nextInt();
                    System.out.println("");
                    String occupedSeats = doFlightsRequest("POST", id_vuelo, fecha);
                    if (Integer.valueOf(occupedSeats) == -1) {
                        System.err.println("There might be no free seats in this flight for this date");
                    } else {
                        System.out.println("Ocupped seats: " + occupedSeats);
                    }

                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Enter the hotel's id:");
                    id_hotel = scanner.nextInt();
                    System.out.println("Enter the date: (aaaammdd)");
                    fecha = scanner.nextInt();
                    System.out.println("");
                    String emptyRooms = doHotelsRequest("GET", id_hotel, fecha);
                    if (Integer.valueOf(emptyRooms) == -1) {
                        System.err.println("There's no hotel with such id in such date");

                    } else {
                        System.out.println("Empty rooms: " + emptyRooms);

                    }
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Enter the hotel's id:");
                    id_hotel = scanner.nextInt();
                    System.out.println("Enter the date: (aaaammdd)");
                    fecha = scanner.nextInt();
                    System.out.println("");
                    String occupedRooms = doHotelsRequest("POST", id_hotel, fecha);
                    if (Integer.valueOf(occupedRooms) == -1) {
                        System.err.println("There might be no free rooms in this hotel for this date");
                    } else {
                        System.out.println("Ocupped rooms: " + occupedRooms);
                    }

                    System.out.println("");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            printMenu();
            opt = scanner.nextInt();

        }
    }

    private static void printMenu() {
        System.out.println("RESTJavaClient");
        System.out.println("--------------");
        System.out.println("  1 - Check free flight's seats");
        System.out.println("  2 - Book flight");
        System.out.println("  3 - Check free hotel's rooms");
        System.out.println("  4 - Book room");
        System.out.println("  0 - Exit");
        System.out.println("");
    }

    private static String doFlightsRequest(String method, int id, int fecha) {
        return doRequest("flights", method, String.valueOf(id), String.valueOf(fecha));
    }

    private static String doHotelsRequest(String method, int id, int fecha) {
        return doRequest("hotels", method, String.valueOf(id), String.valueOf(fecha));
    }

    private static String doRequest(String statement, String method, String id, String fecha) {

        StringBuilder urlString = new StringBuilder();
        Map<String, Object> postParams = new LinkedHashMap<>();
        if (statement.equals("hotels")) {
            urlString.append("http://localhost:8080/REST-HotelsWS/webresources/generic/");
            if (method.equals("GET")) {
                urlString.append("consulta?id_hotel=" + id + "&fecha=" + fecha);
            } else {
                urlString.append("reserva");
                postParams.put("id_hotel", id);
                postParams.put("fecha", fecha);
            }
        } else {
            urlString.append("http://localhost:8080/REST-FlightsWS/webresources/generic/");
            if (method.equals("GET")) {
                urlString.append("consulta?id_vuelo=" + id + "&fecha=" + fecha);
            } else {
                urlString.append("reserva");
                postParams.put("id_vuelo", id);
                postParams.put("fecha", fecha);
            }
        }

        try {
            URL url = new URL(urlString.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            StringBuilder postData = new StringBuilder();
            if (method.equals("POST")) {
                for (Map.Entry<String, Object> param : postParams.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);
            }

            if (conn.getResponseCode() != 200) {
                System.out.println("Failed : HTTP error code : "
                        + conn.getResponseCode());
                return "-";
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            StringBuilder result = new StringBuilder();
            while ((output = br.readLine()) != null) {
                result.append(output);
            }
            conn.disconnect();
            return result.toString();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return "error";
    }
}
