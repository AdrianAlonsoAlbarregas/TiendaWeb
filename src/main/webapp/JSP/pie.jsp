<%-- 
    Document   : pie
    Created on : 13-ene-2017, 18:25:18
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
        <div class="container-fluid">
            <footer id="pie" class="nav navbar-default">
                <div class="col-lg-6">
                    <h4 class="navbar-header" id="pie-mensaje">Visitanos en las redes sociales:</h4>
                    <ul class="nav navbar-nav">
                        <li><a href="https://www.twitter.com"><img id="pie-imagen" src="${pageContext.request.contextPath}/Imagenes/twitter.png"></a></li>
                        <li><a href="https://www.facebook.com"><img id="pie-imagen" src="${pageContext.request.contextPath}/Imagenes/facebook.png"></a></li>
                        <li><a href="https://plus.google.com/"><img id="pie-imagen" src="${pageContext.request.contextPath}/Imagenes/Googlep.png"></a></li>
                    </ul>
                </div>
            </footer>
        </div>
    </body>
</html>