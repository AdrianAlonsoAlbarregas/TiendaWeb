<%-- 
    Document   : index
    Created on : 19-ene-2017, 18:59:23
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
        <jsp:include page="JSP/cabecera.jsp"/>
        <div class="container-fluid">
            <p class="centrar colorVerde hidden" id="mensajeCompra">Producto añadido al carrito</p>
            <h1 id="mensaje"><u>Productos destacados por la comunidad:</u></h1>
            <div id="slider" class="carousel slide" data-ride="carousel">

                <div id="carousel" class="carousel-inner" role="listbox">
                    <div id="carousel-elemento" class="item active">
                        <a href="ContrProducto?prod=${productos[2].idProducto}"><img id="carousel-elemento-imagen" src="${pageContext.request.contextPath}/imagenesProductos/${productos[2].imagenes[0]}"></a>
                        <p id="carousel-elemento-texto"><h2><c:out value="${productos[2].denominacion}"/></h2>
                        puntuacion de la comunidad: <h4><c:out value="${productos[2].rating}"/>/5</p>
                            <c:if test="${sessionScope.usuarioSesion !=null}">
                                <button class="btn btn-success btnComprar" id="btnComprar" value="${productos[2].idProducto}">Añadir al carrito</button>
                            </c:if>
                    </div>

                    <c:forEach var="producto" items="${productos}" begin="3">
                        <c:if test="${producto.rating == 5}">
                            <div id="carousel-elemento" class="item">

                                <a href="ContrProducto?prod=${producto.idProducto}"><img id="carousel-elemento-imagen" src="${pageContext.request.contextPath}/imagenesProductos/${producto.imagenes[0]}"></a>
                                <p id="carousel-elemento-texto"><h2><c:out value="${producto.denominacion}"/></h2>
                                puntuacion de la comunidad: <h4><c:out value="${producto.rating}"/>/5</h4></p>
                                <c:if test="${sessionScope.usuarioSesion !=null}">
                                    <button class="btn btn-success btnComprar" id="btnComprar" value="${producto.idProducto}">Añadir al carrito</button>
                                </c:if>
                            </div>
                        </c:if>
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
        </div>
        <jsp:include page="JSP/pie.jsp"/>
        <script>
            $(".btnComprar").click(function () {
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/ContrCompra",
                    data: {cantidad: "1", producto: $(this).val()},
                    dataType: 'text',
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown);
                    },
                    success: function () {
                        $("#mensajeCompra").removeClass("hidden");
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