package routes

import (
	"github.com/gorilla/mux"
	"handlers"
	"net/http"
)

type appHandler func(http.ResponseWriter, *http.Request) (int, error)

func (fn appHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	if status, err := fn(w, r); err != nil {
		switch status {
		// We can have cases as granular as we like, if we wanted to
		// return custom errors for specific status codes.
		case http.StatusNotFound:
			handlers.NotFoundHandler(w,r)
		case http.StatusInternalServerError:
			http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		default:
			// Catch any other errors we haven't explicitly handled
			http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		}
	}
}

func GetRouter() *mux.Router {



	router := mux.NewRouter()
	router.Handle("/home", appHandler(handlers.HomeHandler)).Methods("GET")
	router.NotFoundHandler = http.HandlerFunc(handlers.NotFoundHandler)
	router.Handle("/login", appHandler(handlers.LoginHandler))

	return router
}