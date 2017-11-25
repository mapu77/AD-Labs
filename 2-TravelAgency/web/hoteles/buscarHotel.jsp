<%@page import="utils.HotelUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
    pageContext.removeAttribute("success");
    HotelUtils hotelUtils = new HotelUtils();
    List<String> cadenas = new ArrayList();
    cadenas.add("Todas");
    cadenas.addAll(hotelUtils.getCadenas());

    List<String> ciudades = new ArrayList();
    ciudades.add("Todas");
    ciudades.addAll(hotelUtils.getCiudades());

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
        <div class="container">
            <jsp:include page="/menu.jsp"/>
            <div id="content" style="padding-left: 20px; padding-right: 20px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Buscador de hoteles</h3>
                    </div>
                    <div class="panel-body container-fluid">

                        <form action="${pageContext.request.contextPath}/GestionHotelesServlet" method="GET">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label>Introduzca el nombre del hotel: </label>
                                        <div class="input-group">
                                            <span class="input-group-addon glyphicon glyphicon-text-size"></span>
                                            <input type="text" class="form-control" placeholder="Nombre del hotel"
                                                   name="nombre">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label>Selecciona una ciudad: </label>
                                    <select class="form-control" name="ciudad">
                                        <c:forEach var="ciudad" items="${ciudades}">
                                            <option value="${ciudad}">${ciudad}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-6">
                                    <label>Selecciona una cadena hotelera: </label>
                                    <select class="form-control" name="cadena">
                                        <c:forEach var="cadena" items="${cadenas}">
                                            <option value="${cadena}">${cadena}</option>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <button class="btn btn-primary pull-right" type="submit">Buscar hotel</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
