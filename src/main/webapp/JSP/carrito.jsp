<%-- 
    Document   : carrito
    Created on : 03-mar-2017, 1:27:26
    Author     : Adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilos.css">
    </head>
    <body>
        <h1 id="mensaje">Bienvenido a su carrito de la compra</h1>
        <c:choose>
            <c:when test="${requestScope.listaPedidos==null}">
                <h3 id="mensaje">Usted no ha realizado aun ningun pedido</h3>
            </c:when>
            <c:otherwise>
                
            </c:otherwise>
        </c:choose>
    </body>
</html>
