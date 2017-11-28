package main

import (
    "log"
    "net/http"
	"routes"
)

func main() {

    router := routes.NewRouter()

    log.Fatal(http.ListenAndServe(":8080", router))
}