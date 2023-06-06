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
        <title>Proyectos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
    </head>
    <body>
        <br>
        
        <div class="row">
            <!--<div class="alert alert-success" *ngIf='message '>{{message}}s/div» "-->
            <div class="container">
                <h3 class="text-center">List of Proyectos</h3>
                <hr>
                <br>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>ID Empresa</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${proyectos}" var="proyecto">
                            <tr>
                                <td scope="row"><c:out value="${proyecto.proyectoid}" /></td>
                                <td><c:out value="${proyecto.nombre}" /></td>
                                <td><c:out value="${proyecto.empresaid}" /></td>
                                <%--<td><a href="UsuarioControlador?action=edit?userid=<c:out value="${user.userid}" />">Edit</a></td>--%>
                                <td><a href="ProyectoControlador?action=edit&proyectoid=${proyecto.proyectoid}"" style="text-decoration: none; font-size: 15px">Edit</a></td>
                                <td><a href="ProyectoControlador?action=delete&proyectoid=${proyecto.proyectoid}"" style="text-decoration: none; font-size: 15px">Delete</a></td>
                                <td><a href="InformesControlador?action=menu&proyectoid=${proyecto.proyectoid}&nombre_proyecto=${proyecto.nombre}"" style="text-decoration: none; font-size: 15px">Report</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            <p><a href="ProyectoControlador?action=insert"" style="text-decoration: none; font-size: 20px">Add Proyecto</a></p>
            <p><a href="/menu-principal-rrhh.jsp"" style="text-decoration: none; font-size: 20px">Menú</a></p>
    </body>
</html>
