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
        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
        .button {
            margin-bottom: 10px;
            display: inline-block;
            padding: 10px 20px;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            color: #fff;
            background-color: #33D1FF;
            border-radius: 4px;
            transition: background-color 0.3s ease;
            color: black;
        }
        
        .button:hover {
            background-color: #1b9bff;
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
                display: flex;
                justify-content: center;
                align-items: center;
                height: 85vh;
            }
            
            .container {
                margin-top: 30px; 
            }
    </style>
</head>

<header>
    <h2 style="text-align: center; background-color: #33D1FF; color: black; padding: 10px; font-family: 'Abril Fatface';">Ingenieros al peso S.A</h2>
</header>

<body>
    <div class="button-container">
        <a class="button" href="UsuarioControlador?action=listUser"style="font-size: 30px; text-decoration: none"">Usuarios</a>
        <a class="button" href="EmpresaControlador?action=listEmpresa"style="font-size: 30px; text-decoration: none">Empresas</a>
        <a class="button" href="ProyectoControlador?action=listProyecto"style="font-size: 30px; text-decoration: none">Proyectos</a>
        <a class="button" href="UsuarioProyectoControlador?action=listUserProject"style="font-size: 30px; text-decoration: none">Usuarios Proyectos</a>
        <a class="button" href="MarcadorControlador?action=listMarcador"style="font-size: 30px; text-decoration: none">Marcajes</a>
        <form action="LogoutControlador" method="POST">
            <br>            
            <button type="submit" class="logout-button" style="font-size: 15px; text-decoration: none"">Logout</button>
            <br>
        </form>
    </div>
    
    <footer>
        <p>Miguel Calvo Soria - Arquitectura Web y Sistemas C/S</p>
    </footer>
    
</body>

</html>

