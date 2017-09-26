<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="utils.GestorHoteles"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    GestorHoteles gestorHoteles = new GestorHoteles();
    List<String> cadenas = gestorHoteles.getCadenas();
    cadenas.add(null);
    List<String> ciudades = gestorHoteles.getCiudades();
    ciudades.add(null);
    pageContext.setAttribute("cadenas", cadenas);
    pageContext.setAttribute("ciudades", ciudades);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar hoteles</title>
    </head>
    <body>
        <h1>Buscador de hoteles</h1>
    <form action="GestionHotelesServlet" method="GET" >    
        <label>Nombre del hotel</label> <br/>
        <input type="text" name="nombre"> <br/><br/>
        
        <label>Selecciona una cadena hotelera: </label>
        <select name="cadena">
            <c:forEach var="cadena" items="${cadenas}">
                <option value="${cadena}">${cadena}</option>
            </c:forEach>
        </select><br/>
        
        <label>Selecciona una ciudad: </label>
        <select name="ciudad">
            <c:forEach var="ciudad" items="${ciudades}">
                <option value="${ciudad}">${ciudad}</option>
            </c:forEach>
        </select><br/>
        <input type="submit" value="Buscar">
    </form>
    </body>
</html>
