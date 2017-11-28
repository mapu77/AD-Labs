package core

import (
	"fmt"
)

type Flight struct {
	Id            int
	Code          string
	Company       string
	DepartureTime string
	DepartureCity string
	ArrivalTime   string
	ArrivalCity   string
}


//func newFlight(id int, code string, company string, departureTime string, departureCity string, arrivalTime string, arrivalCity string) *Flight {
//	return &Flight{Id: id, Code: code, Company: company, DepartureTime: departureTime, DepartureCity: departureCity, ArrivalTime: arrivalTime, ArrivalCity: arrivalCity}
//}

// Make a flight persistent. Returns nil if ok, the produced error otherwise
func (f *Flight) PersistFlight() error {
	fmt.Print("Flight persisted")
	return nil
}
