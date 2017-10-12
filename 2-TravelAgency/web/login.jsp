<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    if (session.getAttribute("user") != null) {
        response.sendRedirect("menu.jsp");
    }
%>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script
            src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha256-k2WSCIexGzOj3Euiig+TlR8gA0EmPjuc79OEeY5L45g="
            crossorigin="anonymous"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body class="col-8-md col-offset-2 container">
<div class="jumbotron">
    <h1>Travel Agency</h1>
    <p>Welcome to the new revolutionary travel agency web application.</p>
    <p>Here you will find flights and hotels to book.</p>
    <div class="container row">
        <div class="col-md-4 col">
            <form method="POST" action="loginServlet" class="form-signin">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon glyphicon glyphicon-user"></span>
                        <input type="text" class="form-control" placeholder="Username" name="username" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon glyphicon glyphicon-lock"></span>
                        <input type="password" class="form-control" placeholder="Password" name="password" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="">
                        <button type="submit" class="btn btn-lg btn-primary btn-block">Login</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>