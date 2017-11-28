package routes

import (
	"github.com/gorilla/mux"
	"handlers"
)

func GetRouter() *mux.Router {

	router := mux.NewRouter()
	router.HandleFunc("/home", handlers.HomeHandler)

	return router
}