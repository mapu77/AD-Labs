package handlers

import (
	"net/http"
	"core"
	//"github.com/gorilla/mux"
	"encoding/json"
	"log"
	"fmt"
)

func NewFlight(w http.ResponseWriter, r *http.Request) {
	var f core.Flight
	var err error
	err = json.NewDecoder(r.Body).Decode(&f)
	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	if err != nil {
		w.WriteHeader(500)
		return
	}
	err = f.PersistFlight()
	if err != nil {
		w.WriteHeader(500)
		return
	}
	w.WriteHeader(201)
}

func ListFlights(w http.ResponseWriter, r *http.Request) {
	params := r.URL.Query()
	var code string
	if len(params["code"]) > 0 {
		code = params["code"][0]
	}
	var company string
	if len(params["company"]) > 0 {
		company = params["company"][0]
	}
	var departureCity string
	if len(params["departureCity"]) > 0 {
		departureCity = params["departureCity"][0]
	}
	var arrivalCity string
	if len(params["arrivalCity"]) > 0 {
		arrivalCity = params["arrivalCity"][0]
	}
	var flights []core.Flight
	var err error
	flights, err = core.ListBy(code, company, departureCity, arrivalCity)
	if err != nil {
		log.Fatal(err)
		w.WriteHeader(500)
		return
	}
	w.Header().Set("Content-Type", "application/json")
	err = json.NewEncoder(w).Encode(flights)
	if err != nil {
		log.Fatal(err)
		w.WriteHeader(500)
		return
	} else {
		w.WriteHeader(200)
	}
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
