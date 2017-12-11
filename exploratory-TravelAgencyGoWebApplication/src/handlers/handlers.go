package handlers

import (
	"net/http"
	"html/template"
	"github.com/gorilla/sessions"
	"log"
)

type User struct {
	Username string
	Password string
}

var cookie = sessions.NewCookieStore([]byte("travelAgencyCookie"))
var users = [2]User{{Username: "Edu", Password: "1234"},{Username: "Ian", Password:"1234"}}


func HomeHandler(w http.ResponseWriter, r *http.Request) (int, error) {

	u := User{ Username: getCookieUsername(w,r)}

	t, _ := template.ParseFiles("templates/menu.html", "templates/home.html")
	return http.StatusOK, t.ExecuteTemplate(w, "home.html" ,u)
}

func LoginHandler(w http.ResponseWriter, r *http.Request) (int, error) {

	if r.Method == "POST"{

		err := r.ParseForm()
		if err != nil {
			log.Fatal(err)
		}
		username := r.PostFormValue("username")
		password := r.PostFormValue("password")

		exists := false
		for _, user := range users{
			if user.Username == username && user.Password == password{
				exists = true
				break
			}
		}

		if exists {
			session, err := cookie.Get(r, "travelAgencySession")
			if err != nil {
				http.Error(w, err.Error(), http.StatusInternalServerError)
			}

			session.Values["username"] = username
			session.Save(r,w)
			http.Redirect(w, r, "/home", http.StatusSeeOther)
		} else {
			http.Error(w, "bad username or password", http.StatusBadRequest)
		}
		//return http.StatusOK, nil
	}

	if r.Method == "GET" {
		session, err := cookie.Get(r, "travelAgencySession")
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
		}

		logged := session.Values["username"] != nil
		if logged {
			http.Redirect(w, r, "/home", http.StatusSeeOther)
		}

		t, _ := template.ParseFiles("templates/login.html")
		return http.StatusOK, t.ExecuteTemplate(w, "login.html", nil)
	}
	return http.StatusMethodNotAllowed, nil
}

func NotFoundHandler(w http.ResponseWriter, r *http.Request){
	t, _ := template.ParseFiles("templates/404.html")
	t.ExecuteTemplate(w, "404.html", nil)
}

func LogoutHandler(w http.ResponseWriter, r *http.Request) (int, error){
	if r.Method == "GET" {
		session, err := cookie.Get(r, "travelAgencySession")
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
		}

		session.Values["username"] = nil
		session.Save(r,w)

		http.Redirect(w,r,"/login", http.StatusSeeOther)
		return http.StatusOK, nil
	}
	return http.StatusMethodNotAllowed, nil
}


func BuscarVueloHandler(w http.ResponseWriter, r *http.Request) (int, error){
	if r.Method == "POST"{

	}
	if r.Method == "GET"{

		u := User{ Username: getCookieUsername(w,r)}
		t, _ := template.ParseFiles("templates/buscarVuelo.html", "templates/menu.html")
		return http.StatusOK, t.ExecuteTemplate(w, "buscarVuelo.html", u)
	}
	return http.StatusMethodNotAllowed, nil
}

func AltaVueloHandler(w http.ResponseWriter, r *http.Request) (int, error){
	if r.Method == "POST"{

	}
	if r.Method == "GET"{
		u := User{ Username: getCookieUsername(w,r)}

		t, _ := template.ParseFiles("templates/altaVuelo.html", "templates/menu.html")
		return http.StatusOK, t.ExecuteTemplate(w, "altaVuelo.html", u)
	}
	return http.StatusMethodNotAllowed, nil
}


func BuscarHotelHandler(w http.ResponseWriter, r *http.Request) (int, error){
	if r.Method == "POST"{

	}
	if r.Method == "GET"{
		u := User{ Username: getCookieUsername(w,r)}

		t, _ := template.ParseFiles("templates/buscarHotel.html", "templates/menu.html")
		return http.StatusOK, t.ExecuteTemplate(w, "buscarHotel.html", u)
	}
	return http.StatusMethodNotAllowed, nil
}


func AltaHotelHandler(w http.ResponseWriter, r *http.Request) (int, error){
	if r.Method == "POST"{

	}
	if r.Method == "GET"{
		u := User{ Username: getCookieUsername(w,r)}

		t, _ := template.ParseFiles("templates/altaHotel.html", "templates/menu.html")
		return http.StatusOK, t.ExecuteTemplate(w, "altaHotel.html", u)
	}
	return http.StatusMethodNotAllowed, nil
}

func getCookieUsername(w http.ResponseWriter, r *http.Request) (string){
	session, err := cookie.Get(r, "travelAgencySession")
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
	}
	return session.Values["username"].(string)
}