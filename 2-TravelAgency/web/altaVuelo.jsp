<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
    session.removeAttribute("success");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Alta vuelo</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div id="content" style="padding-left: 20px; padding-right: 20px;">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Alta vuelo</h3>
            </div>
            <div class="panel-body container-fluid">
                <form method="POST" action="GestionVuelosServlet">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-plane"></span>
                                    <input type="text" class="form-control" placeholder="Nº Vuelo" name="num_vuelo">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-pushpin"></span>
                                    <input type="text" class="form-control" placeholder="Ciudad origen" name="origen"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-time"></span>
                                    <input type="time" class="form-control" placeholder="Hora de salida"
                                           name="hora_salida" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-info-sign"></span>
                                    <input type="text" class="form-control" placeholder="Compañia" name="companyia">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-pushpin"></span>
                                    <input type="text" class="form-control" placeholder="Ciudad destino" name="destino"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-time"></span>
                                    <input type="time" class="form-control" placeholder="Hora de llegada"
                                           name="hora_llegada" required>
                                </div>
                            </div>
                            <button class="btn btn-primary pull-right" type="submit">Alta vuelo</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
