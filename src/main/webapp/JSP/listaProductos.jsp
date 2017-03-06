<%-- 
    Document   : listaProductos
    Created on : 02-feb-2017, 17:56:14
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
        <script>
            /*
             * Esta funcion permite funcionar a la paginacion.
             * 
             * Se pide a Ajax que envie al Controlador del paginado los parametros todos (que indica que la peticion llega
             * desde las pagina de todos los productos) y numPag(que indica que numero de pagina se ha pulsado).
             * 
             * La respuesta del servidor se recibe y se escribe dentro del div "listaProductos"
             */
            function paginar(numPag) {
                /*alert("Aviso" + numPag);*/
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/ContrPaginar",
                    data: {origen: "todos", numeroPag: numPag},
                    dataType: 'text',
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown);
                    },
                    success: function (responseText) {
                        /*alert("si");*/
                        $("#listaProductos").html(responseText);
                    }
                });

            }
        </script>
    </head>
    <body>
        <jsp:include page="../JSP/cabecera.jsp"/>
        <div class="container-fluid">
            <div id="listaProductos">
                <c:forEach items="${productosMostrar}" var="producto">
                    <div class="col-lg-6">
                        <p id="mensaje"><a href="ContrProducto?prod=${producto.idProducto}"><img src="${pageContext.request.contextPath}/imagenesProductos/${producto.imagenes[0]}" style="width: 120px; height: 120px;"></a><br/>
                            <c:out value="${producto.denominacion}"/>
                        <c:if test="${sessionScope.usuarioSesion !=null}">
                                <button class="btn btn-success" id="btnComprar">Comprar</button>
                            </c:if></p>
                    </div>
                </c:forEach>
            </div>
            <div id="botones-paginacion" class="container-fluid">
                <c:forEach items="${numPaginas}" var="numPagina">
                    <button onclick="paginar(${numPagina})" id="${numPagina}" class="btn btn-primary" name="numeroPagina" value="${numPagina}"><c:out value="${numPagina}"></c:out></button>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>
