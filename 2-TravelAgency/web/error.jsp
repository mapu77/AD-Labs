<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR!</title>
    </head>
    <body>
        <h1>An error occurred</h1>
        <span>${requestScope['javax.servlet.error.message']}</span>
    </body>
</html>
