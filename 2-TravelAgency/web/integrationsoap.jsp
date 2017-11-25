<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Service Tester</title>
    </head>
    <body class="container">
       <jsp:include page="menu.jsp"/> 
        <div id="content" class="row" style="padding-top: 15px">
            <h1>SOAP Service</h1>
            <jsp:include page="vuelos/soap/main.jsp"/>
            <jsp:include page="hoteles/soap/main.jsp"/>
        </div>
    </body>
</html>
