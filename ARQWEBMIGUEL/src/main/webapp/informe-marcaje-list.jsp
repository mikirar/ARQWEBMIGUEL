<%-- 
    Document   : user-list
    Created on : May 29, 2023, 12:14:44 AM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marcajes</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
    </head>
    <body>
        <br>
        
        <div class="row">
            <!--<div class="alert alert-success" *ngIf='message '>{{message}}s/div» "-->
            <div class="container">
                <h3 class="text-center">List of Marcajes</h3>
                <hr>
                <br>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Tipo marcaje</th>
                            <th>Id Usuario</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${marcajes}" var="marcaje">
                            <tr>
                                <td scope="row"><c:out value="${marcaje.id}" /></td>
                                <td><c:out value="${marcaje.fecha}" /></td>
                                <td><c:out value="${marcaje.tipo_marcaje}" /></td>
                                <td><c:out value="${marcaje.usuarioid}" /></td>
                                <%--<td><a href="UsuarioControlador?action=edit?userid=<c:out value="${user.userid}" />">Edit</a></td>--%>
                                <td><a href="MarcadorControlador?action=edit&id=${marcaje.id}">Edit</a></td>
                                <td><a href="MarcadorControlador?action=delete&id=${marcaje.id}">Delete</a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br><br><br><br>
                <form>
                    <fieldset class="form-group"> 
                            <label>Fecha informe</label> <input type="datetime-local"
                            value="<c:out value='${fecha_informe}' />" class="form-control" 
                            name="fecha_alta" required>
                    </fieldset>
                </form>
            <p><a href="InformeControlador?action=insert">Informe</a></p>
    </body>
</html>
