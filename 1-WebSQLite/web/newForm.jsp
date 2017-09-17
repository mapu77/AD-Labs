<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Nuevo Hotel</title>
</head>
<body>
<h2>Nuevo hotel</h2>
<form action="dBServlet" method="post">
    <label>
        Nombre del hotel
        <input type="text" name="nombre">
    </label>
    <br>
    <label>
        Cadena del hotel
        <input type="text" name="cadena">
    </label>
    <br>
    <label>
        Nº de habitaciones
        <input type="number" name="habs">
    </label>
    <br>
    <label>
        Dirección postal
        <input type="text" name="direccion">
    </label>
    <br>
    <label>
        Nº postal
        <input type="number" name="calle">
    </label>
    <br>
    <label>
        Código postal
        <input type="text" name="codigo_postal">
    </label>
    <br>
    <label>
        Ciudad
        <input type="text" name="ciudad">
    </label>
    <br>
    <br>
    <input type="submit">
</form>
</body>
</html>
