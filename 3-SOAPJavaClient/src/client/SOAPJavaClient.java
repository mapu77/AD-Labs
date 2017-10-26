package client;

import java.util.Scanner;

public class SOAPJavaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlightClient flightClient = new FlightClient();
        HotelClient hotelClient = new HotelClient();
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
                    System.out.println("Empty seats: " + flightClient.checkFreeSeats(id_vuelo, fecha));
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Enter the flight's id:");
                    id_vuelo = scanner.nextInt();
                    System.out.println("Enter the date: (aaaammdd)");
                    fecha = scanner.nextInt();
                    System.out.println("");
                    System.out.println("Ocupped seats: " + flightClient.bookSeat(id_vuelo, fecha));
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Enter the hotel's id:");
                    id_hotel = scanner.nextInt();
                    System.out.println("Enter the date: (aaaammdd)");
                    fecha = scanner.nextInt();
                    System.out.println("");
                    System.out.println("Empty rooms: " + hotelClient.checkFreeRooms(id_hotel, fecha));
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Enter the hotel's id:");
                    id_hotel = scanner.nextInt();
                    System.out.println("Enter the date: (aaaammdd)");
                    fecha = scanner.nextInt();
                    System.out.println("");
                    System.out.println("Ocupped rooms: " + hotelClient.bookRoom(id_hotel, fecha));
                    System.out.println("");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            printMenu();
            opt = scanner.nextInt();
        }
        System.out.println("Exit success");
    }

    private static void printMenu() {
        System.out.println("SOAPJavaClient");
        System.out.println("--------------");
        System.out.println("  1 - Check free flight's seats");
        System.out.println("  2 - Book flight");
        System.out.println("  3 - Check free hotel's rooms");
        System.out.println("  4 - Book room");
        System.out.println("  0 - Exit");
        System.out.println("");
    }

}
