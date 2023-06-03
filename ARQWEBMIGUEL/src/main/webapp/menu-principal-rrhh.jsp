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
        <a class="button" href="UsuarioControlador?action=listUser"">Usuarios</a>
        <a class="button" href="EmpresaControlador?action=listEmpresa"">Empresas</a>
        <a class="button" href="ProyectoControlador?action=listProyecto"">Proyectos</a>
        <a class="button" href="MarcadorControlador?action=listMarcador"">Marcajes</a>
    </div>
</body>
</html>

