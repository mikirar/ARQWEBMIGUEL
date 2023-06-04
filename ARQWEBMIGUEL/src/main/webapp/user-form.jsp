<%-- 
    Document   : user-form
    Created on : May 29, 2023, 1:01:57 AM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User form</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
    </head>
    <body>
        <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                    <c:if test="${user != null}">
                        <form method="POST" action='UsuarioControlador' name="frmAddUser"">
                    </c:if>
                    <c:if test="${user == null}">
                        <form method="POST" action='UsuarioControlador' name="frmAddUser"">
                    </c:if>
                        
                    <caption>
                         <h2> 
                            <c:if test="${user != null}">
                               Edit User 
                            </c:if>
                            <c:if test="${user == null}">
                               Add New User 
                            </c:if> 
                         </h2>   
                    </caption>
                               
                    <c:if test="${user != null}">
                        <input type="hidden" name="userid" value="<c:out value='${user.userid}' />" />
                    </c:if>
                    
                    <fieldset class="form-group"> 
                        <label>Username</label> <input type="text"
                        value="<c:out value='${user.username}' />" class="form-control" 
                        name="username" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Password</label> <input type="text"
                        value="<c:out value='${user.password}' />" class="form-control" 
                        name="password" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>DNI</label> <input type="text"
                        value="<c:out value='${user.dni}' />" class="form-control" 
                        name="dni" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Nombre</label> <input type="text"
                        value="<c:out value='${user.nombre}' />" class="form-control" 
                        name="nombre" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Apellidos</label> <input type="text"
                        value="<c:out value='${user.apellidos}' />" class="form-control" 
                        name="apellidos" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Fecha alta</label> <input type="datetime-local"
                        value="<c:out value='${user.fecha_alta}' />" class="form-control" 
                        name="fecha_alta" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Fecha baja</label> <input type="datetime-local"
                        value="<c:out value='${user.fecha_baja}' />" class="form-control" 
                        name="fecha_baja">
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Tipo usuario</label> <input type="text"
                        value="<c:out value='${user.tipo_usuario}' />" class="form-control" 
                        name="tipo_usuario" required>
                    </fieldset>
                        
                    <button type="submit" class="btn btn-success">Save</button> 
                    </form>
                    </div>
                </div>
            </div>
                        
    </body>
</html>
