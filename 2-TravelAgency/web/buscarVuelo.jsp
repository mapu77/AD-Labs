<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="utils.GestorVuelos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    GestorVuelos gestorVuelos = new GestorVuelos();
    List<String> companyias = gestorVuelos.getCompanyias();
    companyias.add(null);
    List<String> ciudades = gestorVuelos.getCiudades();
    ciudades.add(null);
    pageContext.setAttribute("companyias", companyias);
    pageContext.setAttribute("ciudades", ciudades);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar vuelos</title>
    </head>
    <body>
        <h1>Buscador de vuelos</h1>
    <form action="GestionVuelosServlet" method="GET" >    
        <label>Número de vuelo</label> <br/>
        <input type="text" name="numero_vuelo"> <br/><br/>
        
        <label>Selecciona una ciudad de origen:</label>
        <select name="ciudad_origen">
            <c:forEach var="ciudad" items="${ciudades}">
                <option value="${ciudad}">${ciudad}</option>
            </c:forEach>
        </select><br/>
        
        <label>Selecciona una ciudad de destino:</label>
        <select name="ciudad_destino">
            <c:forEach var="ciudad" items="${ciudades}">
                <option value="${ciudad}">${ciudad}</option>
            </c:forEach>
        </select><br/>
        
        <label>Selecciona una compañia:</label>
        <select name="companyia">
            <c:forEach var="companyia" items="${companyias}">
                <option value="${companyia}">${companyia}</option>
            </c:forEach>
        </select><br/>
        <input type="submit" value="Buscar">
    </form>
    </body>
</html>
