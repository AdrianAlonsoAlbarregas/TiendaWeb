<%-- 
    Document   : login
    Created on : 14-ene-2017, 18:56:34
    Author     : Adrians
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopware</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilos.css">
    </head>
    <body>

        <jsp:include page="../JSP/cabecera.jsp"></jsp:include>
        <h3 id="login-mensaje">Introduzca su email y contraseña</h3>
        <div class="container">
            <form action="${pageContext.request.contextPath}/ContrRegistro" method="post">
                <div class="input-group">
                    <span class="input-group-addon">Email</span>
                    <input type="email" class="form-control" name="email" placeholder="Email" required>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">Contraseña</span>
                    <input type="password" class="form-control" name="clave" placeholder="Contraseña" pattern=".{2,16}[^()/><\][\\\x22,;|ñ]+" title="Minimo 3 carácteres" required>
                </div>
                <input class="btn" type="submit" name="enviarLogin" value="Aceptar">
            </form>
            <p><em>¿Aún no estas registrado?</em></p>
            <a class="btn" href="registroUsuarios.jsp">Registrate</a>
        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>