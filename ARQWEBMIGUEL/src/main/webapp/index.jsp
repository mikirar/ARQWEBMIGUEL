<%-- 
    Document   : index
    Created on : May 21, 2023, 5:40:04 PM
    Author     : miki
--%>

<%--<<jsp:forward page="/UsuarioControlador?action=listUser"/>--%>

<%--<<jsp:forward page="menu-principal-rrhh.jsp"/>--%>

<%--
<%@ page import="Modelo.DAO.UsuarioDAO" %>
<%@ page import="Modelo.Usuario" %>
<% UsuarioDAO usuarioDao = new UsuarioDAO(); %>
<% Usuario usuario = usuarioDao.obtenerUsuarioPorId(1); %>
<% session.setAttribute("user", usuario); %> --%>

<!--<a class="button" href="MarcadorEmpleadoControlador?action=listEmpleado">Marcajes</a>-->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
    <title>Login</title>
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
            
            .entrar {
                font-size: 16px;
                padding: 8px 16px;
                border-radius: 4px;
                background-color: #28a745;
                color: white;
                border: none;
                margin-top: 20px;
                
            }

            .entrar:hover {
                background-color: #218838;
            }
            
            #bienvenido {
                margin-top: 150px;
            }
            
            .centrado {
            text-align: center;
        }

        form {
            margin-top: 20px;
        }

        input[type="text"],
        input[type="password"] {
            width: 250px;
            padding: 5px;
        }
        
        .toggle-password {
            background-color: transparent;
            border: none;
            cursor: pointer;
            outline: none;
            position: relative;
            right: 0px;
            top: 50%;
        }

        .password-container {
            position: relative;
        }

        .password-container input[type="password"] {
            padding-right: 10px; 
        }
        </style>
</head>

    <header>
        <h2 style="text-align: center; background-color: #33D1FF; color: black; padding: 10px; font-family: 'Abril Fatface';">Ingenieros al peso S.A</h2>
    </header>
    <body>
    <center>
        <h1 id="bienvenido" class="centrado">Bienvenido</h1>
    <br/>
    <h2 class="centrado">Para poder continuar inserta tu usuario y tu contraseña</h2>
    <br/>
    <div class="centrado">
        <form method="POST" action="LoginControlador">
            Usuario: <input type="text" value="" name="usuario" id="user" onclick="document.getElementById("user").value= """/><br/><br/>
            <div class="password-container">
                Password: <input type="password" value="" name="password" id="password"/>
                <button type="button" class="toggle-password" onclick="togglePasswordVisibility()">O</button><br/><br/>
            </div>
            <input type="submit" id="submit" name="submit" value="Entrar" class="entrar""/>
        </form>
    </div>
    </center>

            <footer>
                <p>Miguel Calvo Soria - Arquitectura Web y Sistemas C/S</p>
            </footer>
    
    <script>
        function togglePasswordVisibility() {
            var passwordInput = document.getElementById("password");
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
            } else {
                passwordInput.type = "password";
            }
        }
    </script>
</body>
</html>
