package routes

import (
    "net/http"
	"handlers"
)

type Route struct {
    Name        string
    Method      string
    Pattern     string
    HandlerFunc http.HandlerFunc
}

type Routes []Route

var routes = Routes{
    Route{
        "NewFlight",
        "POST",
        "/flights",
        handlers.NewFlight,
    },
    Route{
        "GetFlights",
        "GET",
        "/flights",
        handlers.ListFlights,
    },
    Route{
        "NewHotel",
        "POST",
        "/hotels",
        handlers.NewHotel,
    },
	Route{
        "GetHotels",
        "GET",
        "/hotels",
        handlers.ListHotels,
    },
}