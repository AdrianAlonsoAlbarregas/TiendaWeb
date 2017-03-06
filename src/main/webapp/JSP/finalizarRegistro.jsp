<%-- 
    Document   : finalizarRegistro
    Created on : 11-ene-2017, 20:29:42
    Author     : Adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilos.css">
    </head>
    <body>
        <jsp:include page="../JSP/cabecera.jsp"/>
        <div class="container-fluid">
            <form action="${pageContext.request.contextPath}/ContrRegCliente" method="post">
                <div class="input-group">
                    <span class="input-group-addon">Nombre</span>
                    <input type="text" class="form-control" name="nombre" placeholder="Nombre" value="<c:out value="${sessionScope.clienteSesion.nombre}"/>" required>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">Apellidos</span>
                    <input id="apellidos" type="text" class="form-control" name="apellidos" placeholder="Apellidos" value="<c:out value="${sessionScope.clienteSesion.apellidos}"/> " required>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">NIF</span>
                    <input id="nif" type="text" class="form-control" name="nif" placeholder="Nif" pattern="(([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Z]{1}))" value="<c:out value="${sessionScope.clienteSesion.nif}"/>"
                </div></div>
                <input class="btn" type="submit" name="enviarRegCliente" value="Aceptar">
            </form>
        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>