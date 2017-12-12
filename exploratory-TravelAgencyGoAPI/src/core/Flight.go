package core

import (
	"gopkg.in/mgo.v2"
	"log"
	"gopkg.in/mgo.v2/bson"
)

type Flight struct {
	Id            bson.ObjectId `json:"id" bson:"_id,omitempty"`
	Code          string
	Company       string
	DepartureTime string
	DepartureCity string
	ArrivalTime   string
	ArrivalCity   string
}

// Returns the flight matching the parameters if specified, or the error if occurs.
func ListBy(code string, company string, departureCity string, arrivalCity string) ([]Flight, error) {
	session, err := mgo.Dial("localhost:27017")
	if err != nil {
		log.Fatal(err)
	}
	defer session.Close()

	session.SetMode(mgo.Monotonic, true)

	c := session.DB("ad-travel-agency").C("flights")
	var args = bson.M{}
	if code != "" {
		args["code"] = code
	}
	if company != "" {
		args["company"] = company
	}
	if company != "" {
		args["departureCity"] = departureCity
	}
	if company != "" {
		args["arrivalCity"] = arrivalCity
	}
	var flights []Flight
	err = c.Find(args).Sort("_id").All(&flights)
	return flights, err
}

// Make a flight persistent. Returns nil if ok, the produced error otherwise
func (f Flight) PersistFlight() error {
	session, err := mgo.Dial("localhost:27017")
	if err != nil {
		log.Fatal(err)
	}
	defer session.Close()

	session.SetMode(mgo.Monotonic, true)

	c := session.DB("ad-travel-agency").C("flights")
	err = c.Insert(&f)
	if err != nil {
		log.Fatal(err)
	}

	return err
}
