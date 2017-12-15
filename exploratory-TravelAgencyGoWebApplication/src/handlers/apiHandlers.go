package handlers

import (
	"net/http"
	"github.com/parnurzeal/gorequest"
	"fmt"
	"html/template"
	"encoding/json"
)

type Flight struct {
	Id			  string `json:"id"`
	Code          string `json:"Code"`
	Company       string `json:"Company"`
	DepartureTime string `json:"DepartureTime"`
	DepartureCity string `json:"DepartureCity"`
	ArrivalTime   string `json:"ArrivalTime"`
	ArrivalCity   string `json:"ArrivalCity"`
}

type Return struct {
	Username string
	Flights []Flight
}

func NewFlight(w http.ResponseWriter, r *http.Request) (int, error){
	r.ParseForm()
	f := Flight{Code : r.PostFormValue("Code"),
				Company: r.PostFormValue("Company"),
				DepartureTime: r.PostFormValue("DepartureTime"),
				DepartureCity: r.PostFormValue("DepartureCity"),
				ArrivalTime: r.PostFormValue("ArrivalTime"),
				ArrivalCity: r.PostFormValue("ArrivalCity"),}
	request := gorequest.New()
	resp, body, errs := request.Post("http://127.0.0.1:8081/flights").
		Send(f).
		End()
	fmt.Print(resp, body, errs)
	if resp.StatusCode != 500 {
		u := User{ Username: getCookieUsername(w,r)}

		t, _ := template.ParseFiles("templates/success.html", "templates/menu.html")
		return http.StatusOK, t.ExecuteTemplate(w, "success.html", u)

	} else {
		return 500, nil
	}
	return resp.StatusCode, nil
}

func GetFlights(w http.ResponseWriter, r *http.Request)(int,error){

	resp, body, errs := gorequest.New().Get("http://127.0.0.1:8081/flights").End()
	fmt.Print("resp:" , resp)
	fmt.Print("body:" , body)
	fmt.Print("errs:" , errs)

	if resp.StatusCode != 500 {
		var flights []Flight
		err := json.Unmarshal([]byte(body), &flights)
		if err != nil {
			fmt.Print("ERRORRR")
		}
		//fmt.Print(flights)
		u := getCookieUsername(w,r)

		r := Return{Username: u, Flights: flights}

		t, _ := template.ParseFiles("templates/tablaVuelos.html", "templates/menu.html")
		return http.StatusOK, t.ExecuteTemplate(w, "tablaVuelos.html",r )

	} else {
		return 500, nil
	}
	return resp.StatusCode, nil
}