<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="altaVuelo.jsp"/>Alta vuelo</li>
                <li><a href="altaHotel.jsp"/>Alta hotel</li>
                <li><a href="buscarVuelo.jsp"/>Buscar vuelo</li>
                <li><a href="buscarHotel.jsp"/>Buscar hotel</li>
            </ul>
        </nav>
    </body>
</html>
