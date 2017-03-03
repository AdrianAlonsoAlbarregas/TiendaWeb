<%-- 
    Document   : loginIncorrecto
    Created on : 16-ene-2017, 17:32:15
    Author     : Adrian
--%>

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
        <jsp:include page="../JSP/cabecera.jsp"/>
        <div class="container-fluid">
        <h1 id="login-mensaje" style="color: red">Ha habido un error: El email o la contraseña son incorrectos</h1>
        <a id="mensaje" href="${pageContext.request.contextPath}/JSP/login.jsp" class="btn btn-primary">Volver a intentarlo</a>
        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>
