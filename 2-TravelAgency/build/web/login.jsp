<%-- 
    Document   : login
    Created on : 19-sep-2017, 17:00:11
    Author     : 1185191
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
if(session.getAttribute("user") != null){
	response.sendRedirect("menu.jsp");
}
%>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form method="POST" action="loginServlet">
            <label> Username </label> <br/>
            <input type="text" name="username"/> <br/>
            <label> Password </label>  <br/>
            <input type="password" name="password"/>  <br/>
            <input type="submit" value="Login"> 
        </form>
    </body>
</html>

