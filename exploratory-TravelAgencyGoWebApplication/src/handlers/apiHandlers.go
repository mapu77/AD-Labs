package handlers

import (
	"net/http"
	"github.com/parnurzeal/gorequest"
	"fmt"
)

type Flight struct {

	Code          string
	Company       string
	DepartureTime string
	DepartureCity string
	ArrivalTime   string
	ArrivalCity   string
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
		http.Redirect(w,r,"/home", http.StatusSeeOther)

	} else {
		return 500, nil
	}
	return resp.StatusCode, nil
}
