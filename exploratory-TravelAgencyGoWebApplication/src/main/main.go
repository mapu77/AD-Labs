package main

import (
	"net/http"
	"routes"
	"log"
	"github.com/gorilla/sessions"
)

func main() {

	sessions.NewCookieStore([]byte("travelAgencyCookie"))
	router := routes.GetRouter()

	log.Fatal(http.ListenAndServe(":8080", router))

	}
