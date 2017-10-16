<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
    pageContext.setAttribute("hoteles", request.getAttribute("hoteles"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hoteles encontrados</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="menu.jsp"/>
            <c:choose>
                <c:when test="${empty hoteles}">
                    <h2>No hay resultados para su búsqueda</h2>
                </c:when>
                <c:otherwise>
                    <h2>Hoteles encontrados</h2>
                    <table class="table table-striped table-condensed table-hover">
                        <thead>
                            <tr>
                                <th>Id Hotel</th>
                                <th>Nombre hotel</th>
                                <th>Cadena</th>
                                <th>Nº habitaciones</th>
                                <th>Calle</th>
                                <th>Número</th>
                                <th>Código postal</th>
                                <th>Ciudad</th>
                                <th>Provincia</th>
                                <th>Pais</th>
                            </tr>
                        </thead>
                        <tbody class="table-hover">
                            <c:forEach var="hotel" items="${hoteles}">
                                <tr>
                                    <c:forEach var="dato" items="${hotel}">
                                        <td><c:out value="${dato}"/></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
