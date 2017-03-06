<%-- 
    Document   : registroUsuarios
    Created on : 11-ene-2017, 20:29:25
    Author     : Adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="Adrian Alonso Montero" />
        <meta name="generator" content="Netbeans" />
        <meta name="robots" content="index, follow" />
        <title>Shopware</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/JS/validarClave.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilos.css">

    </head>
    <body>
        <jsp:include page="../JSP/cabecera.jsp"/>
        <h3 style="text-align: center">Introduzca su email y contraseña</h3>
        <div class="container-fluid">
            <form action="${pageContext.request.contextPath}/ContrRegistro" method="post">
                <div class="input-group">
                    <span class="input-group-addon">Email</span>
                    <input type="email" class="form-control" name="email" placeholder="Email" required>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">Contraseña</span>
                    <input id="clave" type="password" class="form-control" name="clave" placeholder="Contraseña" pattern=".{2,16}[^()/><\][\\\x22,;|ñ]+" title="Minimo 3 carácteres" required>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">Confirmar Contraseña</span>
                    <input id="confClave" type="password" class="form-control" placeholder="Confirmar Contraseña" required>
                </div>
                <input class="btn" onclick=" return validarClave()" type="submit" name="enviarRegistro" value="Aceptar">
            </form>
        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>