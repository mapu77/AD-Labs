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
        <title>Alta vuelo</title>
    </head>
    <body>
        <form method="POST" action="GestionVuelosServlet">
            <label>Nº Vuelo</label>
            <input type="text" name="num_vuelo"><br>
            <label>Compañia</label>
            <input type="text" name="companyia"><br>
            <label>Origen</label>
            <input type="text" name="origen"><br>
            <label>Hora Salida</label>
            <input type="text" name="hora_salida"><br>
            <label>Destino</label>
            <input type="text" name="destino"><br>
            <label>Hora Llegada</label>
            <input type="text" name="hora_llegada"><br>
            <input type="submit" value="Alta vuelo">
        </form>
    </body>
</html>
