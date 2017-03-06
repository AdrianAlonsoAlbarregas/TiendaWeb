<%-- 
    Document   : carrito
    Created on : 03-mar-2017, 1:27:26
    Author     : Adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="Adrian Alonso Montero" />
        <meta name="generator" content="Netbeans" />
        <meta name="robots" content="index, follow" />
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilos.css">
    </head>

    <body>
        <jsp:include page="../JSP/cabecera.jsp"/>
        <h1 id="mensaje">Bienvenido a su carrito de la compra</h1>
        <c:out value="${listaPedidos[0].lineaPedidos[0].producto[0].imagenes[0]}"/>
        <c:choose>
            <c:when test="${carritoVacio==1}">
                <h3 id="mensaje">Usted no ha realizado aun ningun pedido</h3>
            </c:when>
            <c:otherwise>
                <c:forEach items="${listaPedidos}" var="pedido">
                    <div class="col-lg-12">
                        Pedido <c:out value="${pedido.idPedido}"/>: <ul>
                            <c:forEach items="${pedido.lineaPedidos}" var="linea">
                                <li><img src="${pageContext.request.contextPath}/imagenesProductos/${linea.producto[0].imagenes[0]}" style="width: 60px; height: 60px;"><c:out value="${linea.producto[0].denominacion}"/> <c:out value="${linea.precioUnitario}"/> <c:out value="${linea.cantidad}"/> </li>
                                </c:forEach>
                        </ul>
                        iva=<c:out value="${pedido.iva}"/> 
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>

</html>
