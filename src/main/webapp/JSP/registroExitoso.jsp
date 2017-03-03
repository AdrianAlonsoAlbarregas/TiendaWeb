<%-- 
    Document   : registroExitoso
    Created on : 15-ene-2017, 19:29:59
    Author     : Adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h3 id="login-mensaje">Gracias por registrarse en nuestra web, si lo desea puede <a href="${pageContext.request.contextPath}/JSP/finalizarRegistro.jsp">Finalizar su registro</a></h3>
            <p id="login-mensaje">Tambien puede <a href="${pageContext.request.contextPath}/index.jsp">Continuar sus compras</a></p>
        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>