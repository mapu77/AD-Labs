<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rest Web Service</title>
       
    </head>
    <body class="container">
             <jsp:include page="menu.jsp"/> 
        <div id="content" class="row justify-content-between">
            <h1>REST Service</h1>
            <jsp:include page="vuelos/rest/main.jsp"/>
            <jsp:include page="hoteles/rest/main.jsp"/>
        </div>
    </body>
</html>
