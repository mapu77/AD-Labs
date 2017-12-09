package handlers

import (
	"net/http"
	"html/template"
	"fmt"
)

type User struct {
	Username string
}


func HomeHandler(w http.ResponseWriter, r *http.Request) (int, error) {
	if r.Method == "POST" {
		//error?
	}
	t, _ := template.ParseGlob("templates/*")
	u := User{Username : "whtever"}
	return http.StatusOK, t.ExecuteTemplate(w, "hompe.html" ,u)
}

func LoginHandler(w http.ResponseWriter, r *http.Request) (int, error) {

	t, _ := template.ParseGlob("templates/*")
	logged := false
	if logged {
		http.Redirect(w, r, "/home", http.StatusSeeOther)
	}
	return http.StatusOK, t.ExecuteTemplate(w, "login.html", nil)
}

func NotFoundHandler(w http.ResponseWriter, r *http.Request){
	fmt.Fprint(w, "error 404 :)")
}



