<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div id="content" style="padding-left: 20px; padding-right: 20px;">
        <p>Random content</p>
    </div>
</div>
</body>
</html>
