<%-- 
    Document   : errorPage
    Created on : May 29, 2023, 12:10:55 AM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333333;
        }
        
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        
        h1 {
            font-size: 24px;
            color: #333333;
            margin-top: 0;
            text-align: center;
        }
        
        h2 {
            font-size: 18px;
            color: #ff0000;
            margin-bottom: 20px;
            text-align: center;
        }
        
        p {
            font-size: 16px;
            color: #333333;
            margin-bottom: 10px;
        }
        
        ul {
            list-style-type: none;
            padding: 0;
            margin-bottom: 20px;
        }
        
        li {
            margin-bottom: 10px;
        }
        
        pre {
            font-family: Consolas, Monaco, monospace;
            background-color: #f2f2f2;
            padding: 10px;
            overflow-x: auto;
        }
    </style>
</head>
    <body>
        <div class="container">
            <h1>Error</h1>
            <% if (exception != null) { %>
                <h2><%=exception.getMessage()%></h2>
                <p>Información adicional:</p>
                <ul>
                    <li>Causa del error: <%=exception.getCause()%></li>
                    <li>Trazas de la pila de errores:</li>
                    <pre><%=exception.getStackTrace()%></pre>
                </ul>
            <% } else { %>
                <p>Se produjo un error inesperado.</p>
                <p>Por favor, inténtelo de nuevo más tarde.</p>
            <% } %>
        </div>
    </body>
</html>

