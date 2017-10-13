<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
    pageContext.setAttribute("vuelos", request.getAttribute("vuelos"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vuelos encontrados</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="menu.jsp"/>
            <h2>Vuelos encontrados</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id Vuelo</th>
                        <th>Nº Vuelo</th>
                        <th>Compañia</th>
                        <th>Origen</th>
                        <th>Hora Salida</th>
                        <th>Destino</th>
                        <th>Hora Llegada</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="vuelo" items="${vuelos}">
                    <tr>
                    <c:forEach var="dato" items="${vuelo}">
                        <td><c:out value="${dato}"/></td>
                    </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

