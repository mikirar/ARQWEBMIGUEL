<%-- 
    Document   : UsuarioInsertarEditar
    Created on : May 27, 2023, 5:20:18 PM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/ui-lightness/jquery-ui.css" rel="stylesheet" /> 
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        <%--<link href="css/estilo.css" rel="stylesheet" type="text/css">--%>
        <title>Añadir nuevo usuario</title> 
    </head> 
    <body> 
        <%--<script> $(function() { $('input[name=dob]').datepicker(); }); </script>--%>
        <form method="POST" action='UsuarioControlador' name="frmAddUser" class="formulario"> <%--ESTA LÍNEA DE PROBLEMAS POR EL ACTION QUE REDIRIGE MAL--%>
            User ID : <input class="input-group" type="text" readonly="readonly" name="userid" value="<c:out value="${user.userid} /> <br/> 
            Username : <input class="input-group" type="text" name="userName" value="<c:out value="${user.username} /> <br/> 
            Password : <input class="input-group" type="text" name="password" value="<c:out value="${user.password} /> <br/>             
            DNI : <input class="input-group" type="text" name="dni" value="<c:out value="${user.dni}" /> <br/>
            Nombre : <input class="input-group" type="text" name="nombre" value="<c:out value="${user.nombre}" /> <br/> 
            Apellidos : <input class="input-group" type="text" name="apellidos" value="<c:out value="${user.apellidos}" /> <br/> 
            Fecha alta : <input class="input-group" type="date" name="fechaAlta" value="<c:out value="${user.fecha_alta}" /> <br/> 
            Fecha baja : <input class="input-group" type="date" name="fechaBaja" value="<c:out value="${user.fecha_baja}" /> <br/> 
            Tipo usuario : <input class="input-group" type="text" name="tipoUsuario" value="<c:out value="${user.tipo_usuario}" /> <br/> 
            <input type="submit" value="Submit" />
        </form> 
    </body> 
</html>
