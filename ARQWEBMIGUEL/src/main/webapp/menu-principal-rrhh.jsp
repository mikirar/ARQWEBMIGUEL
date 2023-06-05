<%-- 
    Document   : menu-principal-rrhh
    Created on : May 31, 2023, 8:25:35 PM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Menú</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
        .button {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="button-container">
        <a class="button" href="UsuarioControlador?action=listUser"style="font-size: 30px; text-decoration: none"">Usuarios</a>
        <a class="button" href="EmpresaControlador?action=listEmpresa"style="font-size: 30px; text-decoration: none">Empresas</a>
        <a class="button" href="ProyectoControlador?action=listProyecto"style="font-size: 30px; text-decoration: none">Proyectos</a>
        <a class="button" href="UsuarioProyectoControlador?action=listUserProject"style="font-size: 30px; text-decoration: none">Usuarios Proyectos</a>
        <a class="button" href="MarcadorControlador?action=listMarcador"style="font-size: 30px; text-decoration: none">Marcajes</a>
        <form action="LogoutControlador" method="POST">
            <br>            
            <button type="submit" class="btn btn-success" style="font-size: 15px; text-decoration: none"">Logout</button>
            <br>
        </form>
    </div>
</body>

</html>

