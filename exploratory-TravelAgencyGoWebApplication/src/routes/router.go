package routes

import (
	"github.com/gorilla/mux"
	"handlers"
	"net/http"
	"github.com/gorilla/sessions"

)

type appHandler func(http.ResponseWriter, *http.Request) (int, error)

func (fn appHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {

	cookie := sessions.NewCookieStore([]byte("travelAgencyCookie"))
	session, err := cookie.Get(r, "travelAgencySession")
	session.Options = &sessions.Options{
		MaxAge:   3600,
		HttpOnly: true,
	}
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
	}
	if session.Values["username"] == nil {
		if r.URL.Path != "/login" {
			http.Redirect(w, r, "/login", http.StatusSeeOther)
			//handlers.LoginHandler(w,r)
			return
		}
	}
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
	router.Handle("/login", appHandler(handlers.LoginHandler))
	router.Handle("/home", appHandler(handlers.HomeHandler)).Methods("GET")
	router.Handle("/buscarVuelo", appHandler(handlers.BuscarVueloHandler)).Methods("GET", "POST")
	router.Handle("/altaVuelo", appHandler(handlers.AltaVueloHandler)).Methods("GET", "POST")
	router.Handle("/buscarHotel", appHandler(handlers.BuscarHotelHandler)).Methods("GET", "POST")
	router.Handle("/altaHotel", appHandler(handlers.AltaHotelHandler)).Methods("GET", "POST")
	router.Handle("/logout", appHandler(handlers.LogoutHandler)).Methods("GET")
	router.NotFoundHandler = http.HandlerFunc(handlers.NotFoundHandler)

	return router
}