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
	if r.Method == "POST" {
		//error?
	}
	t, _ := template.ParseGlob("templates/*")
	u := User{Username : "whtever"}
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

		t, _ := template.ParseGlob("templates/*")
		return http.StatusOK, t.ExecuteTemplate(w, "login.html", nil)
	}
	return http.StatusMethodNotAllowed, nil
}

func NotFoundHandler(w http.ResponseWriter, r *http.Request){
	t, _ := template.ParseGlob("templates/*")
	t.ExecuteTemplate(w, "404.html", nil)
}

func LogoutHandler(w http.ResponseWriter, r *http.Request) (int, error){
	if r.Method == "GET" {
		session, err := cookie.Get(r, "travelAgencySession")
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
		}

		session.Values["username"] = nil

		http.Redirect(w,r,"/login", http.StatusOK)
	}
	return http.StatusOK, nil
}

