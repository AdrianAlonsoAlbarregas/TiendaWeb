<%-- 
    Document   : perfilUsuario
    Created on : 25-ene-2017, 16:42:27
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
            <h3 id="datos-mod"></h3>
            <h3 id="mensaje">¿Desea modificar sus datos?</h3>
            <div class="col-lg-6" style="text-align: center;">
                <button id="modUser" class="btn btn-primary">Modificar datos de Usuario</button>
            </div>
            <div class="col-lg-6" style="text-align: center;">
                <button id="modClient" class="btn btn-primary">Modificar perfil de Cliente</button>
            </div>
            <div id="datosUsuario" class="hidden">
                <form>
                    <div class="input-group">
                        <span class="input-group-addon">Email</span>
                        <input type="email" class="form-control" name="email" placeholder="Email" value="${sessionScope.usuarioSesion.email}" readonly="" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Vieja Contraseña</span>
                        <input id="clave" type="password" class="form-control" name="clave" placeholder="Contraseña" pattern=".{2,16}[^()/><\][\\\x22,;|ñ]+" title="Minimo 3 carácteres" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Nueva Contraseña</span>
                        <input id="nuevaClave" type="password" class="form-control" placeholder="Nueva Contraseña" required>
                    </div>
                    <input class="btn" id="botonModUser" type="button" name="enviarModUser" value="Aceptar">
                </form>
            </div>
            <div id="datosCliente" class="hidden">
                <form>
                    <div class="input-group">
                        <span class="input-group-addon">Nombre</span>
                        <input type="text" id="nombre" class="form-control" name="nombre" placeholder="Nombre" value="${sessionScope.clienteSesion.nombre}" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Apellidos</span>
                        <input id="apellidos" type="text" class="form-control" name="apellidos" placeholder="Apellidos" value="${sessionScope.clienteSesion.apellidos}" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">NIF</span>
                        <c:choose>
                            <c:when test="${sessionScope.clienteSesion.nif!='Nif'}">
                                <input id="nif" type="text" class="form-control" name="nif" placeholder="Nif" pattern="(([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Z]{1}))" value="${sessionScope.clienteSesion.nif}" readonly="" required>
                            </c:when>
                            <c:otherwise>
                                <input id="nif" type="text" class="form-control" name="nif" placeholder="Nif" pattern="(([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Z]{1}))" value="${sessionScope.clienteSesion.nif}" required>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <input class="btn" id="botonModClient" type="button" name="enviarModClient" value="Aceptar">
                </form>
            </div>
        </div>
        <script>
            /*Dependiendo de que boton se pulse se muestra un formulario u otro*/
            $("#modUser").click(function () {
                if ($("#datosUsuario").hasClass("hidden")) {
                    if (!$("#datosCliente").hasClass("hidden")) {
                        $("#datosCliente").addClass("hidden");
                    }
                    $("#datosUsuario").removeClass("hidden");
                }
            });
            $("#modClient").click(function () {
                if ($("#datosCliente").hasClass("hidden")) {
                    if (!$("#datosUsuario").hasClass("hidden")) {
                        $("#datosUsuario").addClass("hidden");
                    }
                    $("#datosCliente").removeClass("hidden");
                }
            });
            /*Estas funciones sirven para modificar los datos sin recargar la pagina*/
            $("#botonModUser").click(function () {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/ContrModificacion",
                    data: {modificar: "usuario", oldPass: $("#clave").val(), newPass: $("#nuevaClave").val()},
                    dataType: 'text',
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown);
                    },
                    success: function (responseText) {
                        /*alert("si");*/
                        if (responseText == "positiva") {
                            $("#datos-mod").html("Datos Modificados");
                        } else {
                            $("#datos-mod").html("Contraseña Incorrecta");
                        }
                    }
                });
            });
            $("#botonModClient").click(function () {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/ContrModificacion",
                    data: {modificar: "cliente", newFName: $("#nombre").val(), newLName: $("#apellidos").val(), newNif: $("#nif").val()},
                    dataType: 'text',
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown);
                    },
                    success: function () {
                        /*alert("si");*/
                        $("#datos-mod").html("Datos Modificados");
                    }
                });
            });
        </script>
        <jsp:include page="../JSP/pie.jsp"/>
    </body>
</html>
