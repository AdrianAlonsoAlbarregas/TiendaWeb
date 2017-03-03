<%-- 
    Document   : loginExitoso
    Created on : 21-ene-2017, 19:46:57
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
            <h3 id="login-mensaje">Bienvenido, <c:out value="${sessionScope.usuarioSesion.email}"/> </h3>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary">Volver</a>
        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>