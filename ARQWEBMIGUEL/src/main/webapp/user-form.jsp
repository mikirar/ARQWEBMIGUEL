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
                margin-bottom: 70px; 
            }
            
            .container {
                margin-top: 30px; 
            }
            
            .save-button {
                font-size: 16px;
                padding: 8px 16px;
                border-radius: 4px;
                background-color: #28a745;
                color: white;
                border: none;
                margin-top: 20px;
                
            }

            .save-button:hover {
                background-color: #218838;
            }

        </style>
        
        <script>
    document.addEventListener('DOMContentLoaded', function() {
        var tipoUsuarioInput = document.getElementsByName('tipo_usuario')[0];
        
        tipoUsuarioInput.addEventListener('input', function() {
            var inputValue = tipoUsuarioInput.value;
            var validValue = inputValue.replace(/[^AaUu]/g, '').toUpperCase();
            
            if (validValue.length > 1) {
                validValue = validValue.charAt(0);
            }
            
            tipoUsuarioInput.value = validValue;
        });
    });
</script>

    </head>
    
    <header>
        <h2 style="text-align: center; background-color: #33D1FF; color: black; padding: 10px; font-family: 'Abril Fatface';">Ingenieros al peso S.A</h2>
    </header>
    
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
                        
                    <button type="submit" class="save-button">Save</button> 
                    </form>
                    </div>
                </div>
            </div>
            
            <footer>
                <p>Miguel Calvo Soria - Arquitectura Web y Sistemas C/S</p>
            </footer>
    </body>
</html>
