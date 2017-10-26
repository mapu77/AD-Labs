package client;

import edu.adlabs.flights.FlightsWS;
import edu.adlabs.flights.FlightsWS_Service;

class FlightClient {
    
    private final FlightsWS service;
    
    public FlightClient() {
        service = new FlightsWS_Service().getFlightsWSPort();
    }
    
    int checkFreeSeats(int idFlight, int date){
        int freeSeats = service.consultaLibres(idFlight, date);
        if (freeSeats < 0) System.err.println("There's no flight with such id in such date");
        return freeSeats;
    }
    
    int bookSeat(int idFlight, int date) {
        int ocupedSeats = service.reservaPlaza(idFlight, date);
        if (ocupedSeats < 0) System.err.println("There might be no free seats in this flight for this date");
        return ocupedSeats;
    }
    
}
