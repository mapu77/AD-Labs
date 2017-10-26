package client;

import edu.adlabs.hotels.HotelsWS;
import edu.adlabs.hotels.HotelsWS_Service;

class HotelClient {

    private final HotelsWS service;

    public HotelClient() {
        service = new HotelsWS_Service().getHotelsWSPort();
    }

    int checkFreeRooms(int idHotel, int date) {
        int freeRooms = service.consultaLibres(idHotel, date);
        if (freeRooms < 0) System.err.println("There's no hotel with such id in such date");
        return freeRooms;
    }

    int bookRoom(int idHotel, int date) {
        int ocupedSeats = service.reservaHabitacion(idHotel, date);
        if (ocupedSeats < 0) System.err.println("There might be no free rooms in this hotel for this date");
        return ocupedSeats;
    }

}
