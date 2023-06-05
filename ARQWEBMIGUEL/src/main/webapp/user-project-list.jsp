<%-- 
    Document   : user-list
    Created on : May 29, 2023, 12:14:44 AM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
    </head>
    <body>
        <br>
        
        <div class="row">
            <!--<div class="alert alert-success" *ngIf='message '>{{message}}s/div» "-->
            <div class="container">
                <h3 class="text-center">List of Users Projects</h3>
                <hr>
                <br>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>User ID</th>
                            <th>Project ID</th>
                            <th>Fecha alta</th>
                            <th>Fecha baja</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${usuariosproyectos}" var="usuarioproyecto">
                            <tr>
                                <td scope="row"><c:out value="${usuarioproyecto.id}" /></td>
                                <td><c:out value="${usuarioproyecto.userid}" /></td>
                                <td><c:out value="${usuarioproyecto.proyectoid}" /></td>
                                <td><c:out value="${usuarioproyecto.fecha_alta}" /></td>
                                <td><c:out value="${usuarioproyecto.fecha_baja}" /></td>
                                <%--<td><a href="UsuarioControlador?action=edit?userid=<c:out value="${user.userid}" />">Edit</a></td>--%>
                                <td><a href="UsuarioProyectoControlador?action=edit&id=${usuarioproyecto.id}"" style="text-decoration: none; font-size: 15px">Edit</a></td>
                                <td><a href="UsuarioProyectoControlador?action=delete&id=${usuarioproyecto.id}"" style="text-decoration: none; font-size: 15px">Delete</a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            <p><a href="UsuarioProyectoControlador?action=insert">Add UsuarioProyecto</a></p>
            <p><a href="/menu-principal-rrhh.jsp" style="text-decoration: none; font-size: 20px">Menú</a></p>
    </body>
</html>
