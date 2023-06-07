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
    <title>Error 500 - Error interno del servidor</title>
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
    <h1 class="error-code">Error 500</h1>
    <h2 class="error-message">Error interno del servidor</h2>
    <p class="additional-info">Se produjo un error interno en el servidor. Por favor, inténtalo de nuevo más tarde.</p>
</div>
</body>
</html>

