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
        <style>
            header {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                text-align: center;
                background-color: #33D1FF;
                color: black;
                padding: 10px;
                font-family: "Abril fatface";
                margin-bottom: 70px;
            }
            
            footer {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                text-align: center;
                background-color: #33D1FF;
                color: black;
                padding: 10px;
                font-family: "Abril fatface";
            }
            
            body {
                font-family: "Abril Fatface";
                margin-top: 70px; 
                margin-bottom: 100px; 
            }
            
            .container {
                margin-top: 30px; 
            }
            
            .logout-button {
            margin-bottom: 10px;
            display: inline-block;
            padding: 10px 20px;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            color: #fff;
            background-color: #FF3333;
            border-radius: 4px;
            transition: background-color 0.3s ease;
            }

            .logout-button:hover {
                background-color: #ff1b1b;
            }

            .edit-button {
                    font-size: 15px;
                    text-decoration: none;
                    padding: 5px 10px;
                    background-color: #17a2b8;
                    color: #fff;
                    border-radius: 4px;
                }
            
            .edit-button:hover {
                background-color: #138496;
            }
            
            .delete-button {
                font-size: 15px;
                text-decoration: none;
                padding: 5px 10px;
                background-color: #dc3545;
                color: #fff;
                border-radius: 4px;
            }
            
            .delete-button:hover {
                background-color: #c82333;
            }
            
            .marcaje {
                    font-size: 15px;
                    text-decoration: none;
                    padding: 5px 10px;
                    background-color: #17a2b8;
                    color: #fff;
                    border-radius: 4px;
                }
            
            .marcaje:hover {
                background-color: #138496;
            }
        </style>

    </head>
    
    <header>
        <h2 style="text-align: center; background-color: #33D1FF; color: black; padding: 10px; font-family: 'Abril Fatface';">Ingenieros al peso S.A</h2>
    </header>

    <body>
        <br><br><br>
        <h1>Bienvenido ${user.nombre} ${user.apellidos}!</h1>
        <form action="LogoutControlador" method="POST">
                        <button type="submit" class="logout-button" style="font-size: 15px; text-decoration: none"">Logout</button>
                        <br>
        </form>
        
        <div class="row">
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
                                <td><a href="MarcadorEmpleadoControlador?action=edit&id=${marcaje.id}"" style="text-decoration: none; font-size: 15px" class="edit-button">Editar</a></td>
                                <td><a href="MarcadorEmpleadoControlador?action=delete&id=${marcaje.id}"" style="text-decoration: none; font-size: 15px" class="delete-button">Eliminar</a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            <p><a href="MarcadorEmpleadoControlador?action=insert"" style="text-decoration: none; font-size: 20px" class="marcaje"">Añadir Marcaje</a></p>
            
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
                        <button  class="edit-button"><a href="EmpleadoControlador?action=edit&userid=${user.userid}"" style="font-size: 15px; text-decoration: none" class="edit-button">Editar</a></button>
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
            <br>
            <footer>
                <p>Miguel Calvo Soria - Arquitectura Web y Sistemas C/S</p>
            </footer>

            
            
    </body>
</html>
