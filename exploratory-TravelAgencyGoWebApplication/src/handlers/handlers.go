package handlers

import (
	"net/http"
	"html/template"

)

type User struct {
	Username string
}

func HomeHandler(w http.ResponseWriter, r *http.Request) {

	t, _ := template.ParseFiles("templates/home.html")
		u := User{Username : "whtever"}
		//p := Page{Title: "Primera pagina", Body: "Haura sortit be?"}
		t.Execute(w, u)


	//fmt.Fprintln(w,"Welcome to the Home! =)")
}




