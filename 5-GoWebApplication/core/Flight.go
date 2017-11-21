package core

type Flight struct {
	Id            int
	Code          string
	Company       string
	DepartureTime string
	DepartureCity string
	ArrivalTime   string
	ArrivalCity   string
}


func newFlight(id int, code string, company string, departureTime string, departureCity string, arrivalTime string, arrivalCity string)(*Flight) {
	return nil
}

// Make a flight persistent. Returns nil if ok, the produced error otherwise
func persist(flight *Flight) error {
	return nil
}

