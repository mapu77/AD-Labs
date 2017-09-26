<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta hotel</title>
    </head>
    <body>
        <form method="POST" action="GestionHotelesServlet">
            <label>Nombre del hotel</label>
            <input type="text" name="nombre"><br>
            <label>Cadena hotelera</label>
            <input type="text" name="cadena"><br>
            <label>Calle</label>
            <input type="text" name="calle"><br>
            <label>Número</label>
            <input type="number" name="num" min="0" value="0"><br>
            <label>Código Postal</label>
            <input type="text" name="cp"><br>
            <label>Ciudad</label>
            <input type="text" name="ciudad"><br>
            <label>Provincia</label>
            <input type="text" name="provincia"><br>
            <label>País</label>
            <input type="text" name="pais"><br>
            <label>Nº Habitaciones</label>
            <input type="number" name="num_hab" min="0" value="0"><br>
            <input type="submit" value="Alta hotel">
        </form>
    </body>
</html>
