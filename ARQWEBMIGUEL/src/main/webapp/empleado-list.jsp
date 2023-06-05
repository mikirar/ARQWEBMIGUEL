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
        <title>Marcajes Empleado</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
    </head>
    <body>
        <h1>Bienvenido ${user.nombre} ${user.apellidos}!</h1>
        <form action="LogoutControlador" method="POST">
                        <button type="submit" class="btn btn-success" style="font-size: 15px; text-decoration: none"">Logout</button>
                        <br>
        </form>
        
        <div class="row">
            <!--<div class="alert alert-success" *ngIf='message '>{{message}}s/div» "-->
            <div class="container">
                <h3 class="text-center">Tus Marcajes</h3>
                <hr>
                <br>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Tipo marcaje</th>
                            <th>Id Usuario</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${marcajes}" var="marcaje">
                            <tr>
                                <td scope="row"><c:out value="${marcaje.id}" /></td>
                                <td><c:out value="${marcaje.fecha}" /></td>
                                <td><c:out value="${marcaje.tipo_marcaje}" /></td>
                                <td><c:out value="${marcaje.usuarioid}" /></td>
                                <%--<td><a href="UsuarioControlador?action=edit?userid=<c:out value="${user.userid}" />">Edit</a></td>--%>
                                <td><a href="MarcadorEmpleadoControlador?action=edit&id=${marcaje.id}"" style="text-decoration: none; font-size: 15px">Edit</a></td>
                                <td><a href="MarcadorEmpleadoControlador?action=delete&id=${marcaje.id}"" style="text-decoration: none; font-size: 15px">Delete</a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            <p><a href="MarcadorEmpleadoControlador?action=insert"" style="text-decoration: none; font-size: 20px">Add Marcaje</a></p>
            
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <%--
                    <c:if test="${user != null}">
                        <form method="POST" action='EmpleadoControlador' name="frmAddUser"">
                    </c:if>
                    <c:if test="${user == null}">
                        <form method="POST" action='EmpleadoControlador' name="frmAddUser"">
                    </c:if>--%>
                        
                    <caption>
                         <h2> 
                            Datos personales
                         </h2>   
                    </caption>
                               
                    <c:if test="${user != null}">
                        <input type="hidden" name="userid" value="<c:out value='${user.userid}' />" />
                    </c:if>
                    
                    <fieldset class="form-group"> 
                        <label>Username</label> <input type="text"
                        value="<c:out value='${user.username}' />" class="form-control" 
                        name="username" required readonly>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Password</label> <input type="text"
                        value="<c:out value='${user.password}' />" class="form-control" 
                        name="password" required readonly>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>DNI</label> <input type="text"
                        value="<c:out value='${user.dni}' />" class="form-control" 
                        name="dni" required readonly>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Nombre</label> <input type="text"
                        value="<c:out value='${user.nombre}' />" class="form-control" 
                        name="nombre" required readonly>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Apellidos</label> <input type="text"
                        value="<c:out value='${user.apellidos}' />" class="form-control" 
                        name="apellidos" required readonly>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Fecha alta</label> <input type="datetime-local"
                        value="<c:out value='${user.fecha_alta}' />" class="form-control" 
                        name="fecha_alta" required readonly>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Fecha baja</label> <input type="datetime-local"
                        value="<c:out value='${user.fecha_baja}' />" class="form-control" 
                        name="fecha_baja" readonly>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Tipo usuario</label> <input type="text"
                        value="<c:out value='${user.tipo_usuario}' />" class="form-control" 
                        name="tipo_usuario" required readonly>
                    </fieldset>
                        <br>
                        <button><a href="EmpleadoControlador?action=edit&userid=${user.userid}" style="font-size: 15px; text-decoration: none"">Edit</a></button>
                    <%--</form>--%>
                    </div>
                </div>
            </div>
                    
            <div>
                <h2> 
                    Empresas
                </h2>
                  <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${empresas}" var="empresa">
                            <tr>
                                <td scope="row"><c:out value="${empresa.empresaid}" /></td>
                                <td><c:out value="${empresa.nombre_empresa}" /></td>
                                <%--<td><a href="UsuarioControlador?action=edit?userid=<c:out value="${user.userid}" />">Edit</a></td>--%>

                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>      
            </div>
                    
            <div>
                <h2>
                    Proyectos
                </h2>
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

                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
                        
            </div>
            
            
            
    </body>
</html>
