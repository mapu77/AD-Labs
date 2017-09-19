<%-- 
    Document   : menu
    Created on : 19-sep-2017, 16:44:30
    Author     : 1185191
--%>

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
        <title>Menu</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
