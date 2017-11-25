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
    <title>Alta hotel</title>
</head>
<body>
<div class="container">
    <jsp:include page="/menu.jsp"/>
    <div id="content" style="padding-left: 20px; padding-right: 20px;">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Alta hotel</h3>
            </div>
            <div class="panel-body container-fluid">
                <form method="POST" action="${pageContext.request.contextPath}/GestionHotelesServlet">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-text-size"></span>
                                    <input type="text" class="form-control" placeholder="Nombre del hotel" name="nombre"
                                           required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-info-sign"></span>
                                    <input type="text" class="form-control" placeholder="Cadena hotelera" name="cadena" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-bed"></span>
                                    <input type="number" class="form-control" placeholder="Nº Habitaciones"
                                           name="num_hab"
                                           min="1" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-pushpin"></span>
                                    <input type="text" class="form-control" placeholder="Calle" name="calle" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="number" class="form-control" placeholder="Número" name="num" min="0" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="text" class="form-control" placeholder="Código postal" name="cp" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="text" class="form-control" placeholder="Ciudad"
                                           name="ciudad" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="text" class="form-control" placeholder="Provincia"
                                           name="provincia" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="text" class="form-control" placeholder="Pais"
                                           name="pais" required>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="">
                            <button class="btn btn-primary pull-right" type="submit">Alta hotel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>