<%@page import="utils.GestorVuelos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    GestorVuelos gestorVuelos = new GestorVuelos();
    List<String> companyias = gestorVuelos.getCompanyias();
    pageContext.setAttribute("companyias", companyias);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar vuelos</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <select>
            <c:forEach items="${companyias}" var="companyia">
                <option value="${companyia}">${companyia}</option>
            </c:forEach>
        </select>
    </body>
</html>
