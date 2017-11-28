package handlers

import (
    //"encoding/json"
    "fmt"
    "net/http"
    "core"
    //"github.com/gorilla/mux"
)

func NewFlight(w http.ResponseWriter, r *http.Request) {
    var f = core.Flight{Id: 1, Code: "rand", Company: "rand", DepartureTime: "rand", DepartureCity: "rand", ArrivalTime: "rand", ArrivalCity: "rand"}
    f.PersistFlight()
    fmt.Fprintln(w, "Welcome!")
}

func ListFlights(w http.ResponseWriter, r *http.Request) {
    //todos := Todos{
    //    Todo{Name: "Write presentation"},
    //    Todo{Name: "Host meetup"},
    //}
	//
    //if err := json.NewEncoder(w).Encode(todos); err != nil {
    //    panic(err)
    //}
    fmt.Fprintln(w, "ListFlights")

}

func NewHotel(w http.ResponseWriter, r *http.Request) {
    //vars := mux.Vars(r)
    //todoId := vars["todoId"]
    //fmt.Fprintln(w, "Todo show:", todoId)
}

func ListHotels(w http.ResponseWriter, r *http.Request) {
    //vars := mux.Vars(r)
    //todoId := vars["todoId"]
    //fmt.Fprintln(w, "Todo show:", todoId)

    fmt.Fprintln(w, "ListHotels")
}
