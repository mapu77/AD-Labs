<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div id="content">
        <c:set var="message" value='${sessionScope.get("success")}'/>
        <c:if test="${not empty message}">
            <div id="context-message" class="alert alert-success fade in" role="alert">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Success</strong><br/><span>${message}</span>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
