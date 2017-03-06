<%-- 
    Document   : cabecera
    Created on : 13-ene-2017, 18:25:07
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
        <script>
            /*
             * Esta funcion es necesaria para que los dropdown funcionen al hacer click en ellos
             */
            $(document).ready(function () {
                $('.dropdown-submenu a.test').on("click", function (e) {
                    $(this).next('ul').toggle();
                    e.stopPropagation();
                    e.preventDefault();
                });
            });

        </script>
        <div class="container-fluid">
            <div class="row">
                <header id="cabecera-header">
                    <div class="col-lg-3" id="cabecera-icono">
                        <a href="${pageContext.request.contextPath}/index.jsp"><img id="imagen-alinear" src="${pageContext.request.contextPath}/Imagenes/logo1.png" alt="Logo"></a>
                    </div>
                    <div class="col-lg-7">
                        <div class="input-group" id="cabecera-buscador">

                            <!--<form action="" method="post">-->
                            <div class="input-group">
                                <input id="buscador-input" type="text" class="form-control dropdown-toggle" data-toggle="dropdown" placeholder="Busqueda" name="buscador">
                                <div id="resultadosBusqueda" class="dropdown-menu">
                                </div>
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                            <!--</form>-->
                        </div>
                    </div>
                    <div class="col-lg-2">
                        <div id="cabecera-menu">
                            <c:choose>
                                <c:when test="${sessionScope.usuarioSesion!=null}">
                                    <div class="dropdown">
                                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Menu <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="${pageContext.request.contextPath}/JSP/perfilUsuario.jsp">Perfil</a></li>
                                            <li><a href="${pageContext.request.contextPath}/ContrCarrito">Carrito</a></li>
                                            <li><a href="${pageContext.request.contextPath}/ContrLogout">Salir de la cuenta</a></li>
                                        </ul>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="dropdown">
                                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Login/Registro <span class="caret"/></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="${pageContext.request.contextPath}/JSP/login.jsp">Login</a></li>
                                            <li><a href="${pageContext.request.contextPath}/JSP/registroUsuarios.jsp">Registro</a></li>
                                        </ul>
                                    </div>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </header>
            </div>
            <div class="row">
                <div class="container-fluid">
                    <nav  id="cabecera-navegacion" class="nav navbar-default">
                        <div class="col-lg-8">
                            <div class="navbar-header">
                                <p id="navegacion-elemento" class="navbar-brand">Shopware</p>
                            </div>
                            <ul class="nav navbar-nav">
                                <li><a class="dropdown" id="navegacion-elemento" data-toggle="dropdown">Productos<span class="caret"></span></a>
                                    <ul class="dropdown-menu multi-level">
                                        <li><a href="${pageContext.request.contextPath}/ContrListas">Todos los productos</a></li>
                                        <li class="dropdown-submenu"><a class="test" id="navegacion-elemento" role="button">Por Categoria<span class="caret"></span></a>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${categorias}" var="categoria">
                                                    <li><a id="navegacion-elemento"  href="${pageContext.request.contextPath}/ContrListas?cat=${categoria.idCategoria}"><c:out value="${categoria.nombre}"/></a></li>
                                                    </c:forEach>
                                            </ul></li>
                                    </ul></li>
                            </ul>
                        </div>
                        <div class="col-lg-4">
                            <c:choose>
                                <c:when test="${sessionScope.clienteSesion!=null && sessionScope.clienteSesion.nombre!='Nombre'}">
                                    <p id="navegacion-mensaje">Bienvenido, <c:out value="${sessionScope.clienteSesion.nombre}"/></p>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${sessionScope.usuarioSesion !=null}">
                                        <p id="navegacion-mensaje">Bienvenido, <c:out value="${sessionScope.usuarioSesion.email}"/></p>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <script>
            /*
             * Esta funcion sirve para que funcionen las sugerencias de busqueda.
             * 
             * Se le dice a ajax que llame al Controlador de la busqueda enviando el valor del input de busqueda.
             * 
             * La respuesta del controlador se recibe y se escrive dentro del div "resultadosBusqueda" cada vez que
             * el usuario escripa algo en el input.
             */
            $("#buscador-input").keyup(function () {
                if ($(this).val() == null) {
                    $("#resultadosBusqueda".html(""));
                } else {
                    $.ajax({
                        type: "GET",
                        url: "${pageContext.request.contextPath}/ContrBusqueda",
                        data: {cadena: $(this).val()},
                        dataType: 'text',
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert(errorThrown);
                        },
                        success: function (responseText) {
                            /*alert("si");*/
                            $("#resultadosBusqueda").html(responseText);
                        }
                    });
                }
            });
        </script>
    </body>
</html>