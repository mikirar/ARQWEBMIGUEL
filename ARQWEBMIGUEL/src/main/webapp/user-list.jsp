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
                <h3 class="text-center">List of Users</h3>
                <hr>
                <br>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Fecha alta</th>
                            <th>Fecha baja</th>
                            <th>Tipo usuario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td scope="row"><c:out value="${user.userid}" /></td>
                                <td><c:out value="${user.username}" /></td>
                                <td><c:out value="${user.password}" /></td>
                                <td><c:out value="${user.dni}" /></td>
                                <td><c:out value="${user.nombre}" /></td>
                                <td><c:out value="${user.apellidos}" /></td>
                                <td><c:out value="${user.fecha_alta}" /></td>
                                <td><c:out value="${user.fecha_baja}" /></td>
                                <td><c:out value="${user.tipo_usuario}" /></td>
                                <td><a href="edit?id_user=<c:out value="${user.userid}" />">Edit</a></td>
                                <td><a href="delete?id_user=<c:out value="${user.userid}" />">Delete</a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            <p><a href="UsuarioControlador?action=insert">Add User</a></p>
    </body>
</html>
