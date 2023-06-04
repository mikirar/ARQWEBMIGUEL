<%-- 
    Document   : errorPage
    Created on : May 29, 2023, 12:10:55 AM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage = "true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
    <center>
        <h1>Error</h1>
        <h2><%=exception.getMessage()%></h2>
        <p>Información adicional:</p>
        <<ul>
            <li>Causa del error: <%=exception.getCause()%></li>
            <li>Trazas de la pila de errores:</li>
            <pre><%=exception.getStackTrace()%></pre>
        </ul>
    </center>
    </body>
</html>
