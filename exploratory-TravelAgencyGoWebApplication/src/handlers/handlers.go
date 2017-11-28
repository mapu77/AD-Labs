package handlers

import (
	"net/http"
	"fmt"
)

func HomeHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w,"Welcome to the Home! =)")
}
