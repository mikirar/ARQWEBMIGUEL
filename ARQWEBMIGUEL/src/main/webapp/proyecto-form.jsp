<%-- 
    Document   : empresa-form
    Created on : May 29, 2023, 1:01:57 AM
    Author     : miki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empresa form</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
    </head>
    <body>
        <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                    <c:if test="${proyecto != null}">
                        <form method="POST" action='ProyectoControlador' name="frmAddProyecto"">
                    </c:if>
                    <c:if test="${proyecto == null}">
                        <form method="POST" action='ProyectoControlador' name="frmAddProyecto"">
                    </c:if>
                        
                    <caption>
                         <h2> 
                            <c:if test="${proyecto != null}">
                               Edit Proyecto 
                            </c:if>
                            <c:if test="${proyecto == null}">
                               Add New Proyecto 
                            </c:if> 
                         </h2>   
                    </caption>
                               
                    <c:if test="${proyecto != null}">
                        <input type="hidden" name="proyectoid" value="<c:out value='${proyecto.proyectoid}' />" />
                    </c:if>
                    
                    <fieldset class="form-group"> 
                        <label>Nombre</label> <input type="text"
                        value="<c:out value='${proyecto.nombre}' />" class="form-control" 
                        name="nombre" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>ID Empresa</label> <input type="text"
                        value="<c:out value='${proyecto.empresaid}' />" class="form-control" 
                        name="empresaid" required>
                    </fieldset>
                        
                    <button type="submit" class="btn btn-success">Save</button> 
                    </form>
                    </div>
                </div>
            </div>
                        
    </body>
</html>
