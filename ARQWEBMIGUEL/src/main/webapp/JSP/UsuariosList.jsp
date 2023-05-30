<%-- 
    Document   : UsuarioJSP
    Created on : May 26, 2023, 10:21:16 PM
    Author     : miki
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="es-es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head> 
    <body> 
        
        <table class="table table-striped"> 
            <thead> 
                <tr> 
                    <th scope="col">User Id</th> 
                    <th scope="col">Username</th> 
                    <th scope="col">Password</th> 
                    <th scope="col">DNI</th> 
                    <th scope="col">Nombre</th> 
                    <th  scope="col">Apellidos</th>
                    <th  scope="col">Fecha alta</th>
                    <th  scope="col">Fecha baja</th> 
                    <th  scope="col">Tipo usuario</th> 

                </tr> 
            </thead> 
            <tbody> 
                <c:forEach items="${users}" var="user"> 
                    <tr> 
                        <td scope="row"><c:out value="${user.id_user}" /></td>
                        <td><c:out value="${user.username}" /></td> 
                        <td><c:out value="${user.password}" /></td>
                        <td><c:out value="${user.dni}" /></td>
                        <td><c:out value="${user.nombre}" /></td>
                        <td><c:out value="${user.apellidos}" /></td> 
                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${user.fecha_alta}" /></td>
                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${user.fecha_baja}" /></td>
                        <td><c:out value="${user.tipo_usuario}" /></td> 
                        <td><a href="UsuarioControlador?action=edit&userId=<c:out value="${user.userid}"/>">Update</a></td> 
                        <td><a href="UsuarioControlador?action=delete&userId=<c:out value="${user.userid}"/>">Delete</a></td> 
                    </tr> 
                </c:forEach> 
            </tbody> 
        </table> 
        <p><a href="UsuarioControlador?action=insert">Add User</a></p>
    </body>
</html>
