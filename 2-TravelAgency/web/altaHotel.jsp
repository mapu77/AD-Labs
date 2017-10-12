<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Alta hotel</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div id="content" style="padding-left: 20px; padding-right: 20px;">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Alta hotel</h3>
            </div>
            <div class="panel-body container-fluid">
                <form method="POST" action="GestionHotelesServlet">
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
                                    <input type="text" class="form-control" placeholder="Cadena hotelera" name="cadena">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-bed"></span>
                                    <input type="number" class="form-control" placeholder="Nº Habitaciones"
                                           name="num_hab"
                                           min="1">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-pushpin"></span>
                                    <input type="text" class="form-control" placeholder="Calle" name="calle">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="number" class="form-control" placeholder="Número" name="num">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="text" class="form-control" placeholder="Código postal" name="cp">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="time" class="form-control" placeholder="Ciudad"
                                           name="ciudad">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="time" class="form-control" placeholder="Provincia"
                                           name="provincia">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon glyphicon glyphicon-globe"></span>
                                    <input type="time" class="form-control" placeholder="Pais"
                                           name="pais">
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

<script type="text/javascript">
    $(function () {
        $('#datetimepicker2').datetimepicker({
            locale: 'ru'
        });
    });
</script>