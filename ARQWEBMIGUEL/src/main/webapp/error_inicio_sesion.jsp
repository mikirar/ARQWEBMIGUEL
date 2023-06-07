<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error de inicio de sesión</title>
    <style>
        body {
            background-color: #f1f1f1;
            font-family: Arial, sans-serif;
        }
        
        .container {
            width: 400px;
            margin: 0 auto;
            margin-top: 100px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        
        h2 {
            color: #ff5c5c;
            font-size: 24px;
            text-align: center;
        }
        
        p {
            text-align: center;
            margin-top: 20px;
        }
        
        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s;
        }
        
        a:hover {
            background-color: #45a049;
        }
    </style>
    </head>
    <body>
        <div class="container">
            <h2>Error de inicio de sesión</h2>
            <p>Inicio de sesión incorrecto, inténtelo de nuevo.</p>
            <p><a href="/index.jsp">Volver al inicio de sesión</a></p>
        </div>
    </body>
</html>
