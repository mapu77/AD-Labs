<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="utils.VueloUtils" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
    VueloUtils vueloUtils = new VueloUtils();
    List<String> companyias = new ArrayList();
   companyias.add("Todas");
   companyias.addAll(vueloUtils.getCompanyias());

    List<String> ciudades = new ArrayList();
    ciudades.add("Todas");
    ciudades.addAll(vueloUtils.getCiudades());
    
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
        <div class="container">
            <jsp:include page="menu.jsp"/>
            <div id="content" style="padding-left: 20px; padding-right: 20px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Buscador de vuelos</h3>
                    </div>
                    <div class="panel-body container-fluid">

                        <form action="GestionVuelosServlet" method="GET">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon glyphicon glyphicon-plane"></span>
                                            <input type="text" class="form-control" placeholder="Número de vuelo" name="numero_vuelo">
                                        </div>
                                    </div>
                                    <label>Selecciona una compañia:</label>
                                    <select class="form-control" name="companyia" >
                                        <c:forEach var="companyia" items="${companyias}">
                                            <option value="${companyia}">${companyia}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-6">
                                    <label>Selecciona una ciudad de origen:</label>
                                    <select class="form-control" name="ciudad_origen">
                                        <c:forEach var="ciudad" items="${ciudades}">
                                            <option value="${ciudad}">${ciudad}</option>
                                        </c:forEach>
                                    </select></br>

                                    <label>Selecciona una ciudad de destino:</label>
                                    <select class="form-control" name="ciudad_destino" >
                                        <c:forEach var="ciudad" items="${ciudades}">
                                            <option value="${ciudad}">${ciudad}</option>
                                        </c:forEach>
                                    </select></br>
                                    <button class="btn btn-primary pull-right" type="submit">Buscar vuelo</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
