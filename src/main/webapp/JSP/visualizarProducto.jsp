<%-- 
    Document   : visualizarProducto
    Created on : 01-feb-2017, 19:36:59
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
                <p> <h3>Precio: <fmt:formatNumber value="${productoSeleccionado.precioUnitario/1.21}" maxFractionDigits="2"/>€</h3>
                <h3>Iva: <fmt:formatNumber value="${(productoSeleccionado.precioUnitario/1.21)*0.21}" maxFractionDigits="2"/>€</h3></p>
                <c:if test="${sessionScope.usuarioSesion !=null}">
                    <h3>Comprar: </h3>
                    <p>Cantidad: <input type="number" id="cantidad" min="1" value="1"></p>
                    <button class="btn btn-success btnComprar" id="btnComprar" value="${productoSeleccionado.idProducto}">Añadir al carrito</button>
                </c:if>
                
            </div>

        </div>
        <jsp:include page="../JSP/pie.jsp"/>
        <script>
            $(".btnComprar").click(function () {
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/ContrCompra",
                    data: {cantidad: $("#cantidad").val(), producto: $(this).val()},
                    dataType: 'text',
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown);
                    },
                    success: function () {
                        $("#mensajeCompra").removeClass("hidden")
                        setTimeout(ocultar(), 2000);
                    }
                });
            });
            function ocultar() {
                $("#mensajeCompra").addClass("hidden");
            }
        </script>
    </body>
</html>