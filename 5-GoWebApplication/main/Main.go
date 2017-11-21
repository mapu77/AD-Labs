package main

import (
    "log"
    "net/http"
	"routes"
)

func main() {

    router := NewRouter()

    log.Fatal(http.ListenAndServe(":8080", router))
}