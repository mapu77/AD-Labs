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
            <jsp:include page="restflights/flights.jsp"/>
            <jsp:include page="resthotels/hotels.jsp"/>
        </div>
    </body>
</html>
