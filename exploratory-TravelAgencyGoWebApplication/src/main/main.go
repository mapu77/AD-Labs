package main

import (
	"net/http"
	"routes"
	"log"
)

func main() {

	router := routes.GetRouter()

	log.Fatal(http.ListenAndServe(":8080", router))

	}
