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
    <title>Login demo</title>
</head>
<body>
<center>
    <h1 class="centrado">Bienvenido</h1>
<br/>
<h2 class="centrado">Para poder continuar inserta tu usuario y tu contraseña</h2>
<br/>
<div class="centrado">
    <form method="POST" action="LoginControlador">
        Usuario: <input type="text" value="" name="usuario" id="user" onclick="document.getElementById("user").value
        = """/><br/><br/>
        Password: <input type="password" value="" name="password" id="password"/><br/><br/>
        <input type="submit" id="submit" name="submit" value="Entrar"/>
    </form>
</div>
</center>
</body>
</html>
