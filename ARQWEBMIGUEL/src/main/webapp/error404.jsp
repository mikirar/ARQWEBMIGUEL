<%-- 
    Document   : errorPage
    Created on : May 29, 2023, 12:10:55 AM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error 404 - Página no encontrada</title>
    <style>
        body {
            background-color: #f4f4f4;
            font-family: Arial, sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            text-align: center;
        }

        .error-code {
            font-size: 100px;
            margin-bottom: 20px;
            color: #e74c3c;
        }

        .error-message {
            font-size: 24px;
            margin-bottom: 20px;
            color: #e74c3c;
        }

        .additional-info {
            font-size: 18px;
            margin-bottom: 40px;
            color: #555555;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="error-code">Error 404</h1>
    <h2 class="error-message">Página no encontrada</h2>
    <p class="additional-info">La página que estás buscando no existe o ha sido movida.</p>
</div>
</body>
</html>

