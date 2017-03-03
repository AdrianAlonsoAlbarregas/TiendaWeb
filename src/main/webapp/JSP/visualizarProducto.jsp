<%-- 
    Document   : visualizarProducto
    Created on : 01-feb-2017, 19:36:59
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
            <div id="slider" class="carousel slide col-lg-6" data-ride="carousel">

                <div id="carousel" class="carousel-inner" role="listbox">
                    <div id="carousel-elemento" class="item active">
                        <img id="carousel-elemento-imagen" src="${pageContext.request.contextPath}/imagenesProductos/${productoSeleccionado.imagenes[0]}">
                        <p id="carousel-elemento-texto"><c:out value="${productoSeleccionado.denominacion}"/> </p>
                    </div>
                    <c:forEach var="imagen" items="${productoSeleccionado.imagenes}" begin="1">
                        <div id="carousel-elemento" class="item">
                            <img id="carousel-elemento-imagen" src="${pageContext.request.contextPath}/imagenesProductos/${imagen}">
                            <p id="carousel-elemento-texto"><c:out value="${productoSeleccionado.denominacion}"/> </p>
                        </div>
                    </c:forEach> 

                </div>
                <a id="carousel-flecha" class="left carousel-control" href="#slider" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Anterior</span>
                </a>
                <a id="carousel-flecha" class="right carousel-control" href="#slider" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Siguiente</span>
                </a>
            </div>
            <div class="col-lg-6">
                <h3>Descripcion:</h3>
                <p><c:out value="${productoSeleccionado.descripcion}"/></p><br/>
                <h3>Caracteristicas:</h3>
                <c:forEach items="${productoSeleccionado.caracteristicas}" var="caracteristica">
                    <p><b><c:out value="${caracteristica.nombre}"/>:</b> <c:out value="${caracteristica.descripcion}"/> </p>
                </c:forEach><br/>
                <p> <h3>Precio, IVA incluido: <c:out value="${productoSeleccionado.precioUnitario}"/>€</h3></p>
            </div>

        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>