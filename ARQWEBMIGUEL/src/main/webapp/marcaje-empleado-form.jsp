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
        <title>Marcaje form</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
    </head>
    <body>
        <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                    <c:if test="${marcaje != null}">
                        <form method="POST" action='MarcadorEmpleadoControlador' name="frmAddMarcaje"">
                    </c:if>
                    <c:if test="${marcaje == null}">
                        <form method="POST" action='MarcadorEmpleadoControlador' name="frmAddMarcaje"">
                    </c:if>
                        
                    <caption>
                         <h2> 
                            <c:if test="${marcaje != null}">
                               Edit Marcaje 
                            </c:if>
                            <c:if test="${marcaje == null}">
                               Add New Marcaje 
                            </c:if> 
                         </h2>   
                    </caption>
                               
                    <c:if test="${marcaje != null}">
                        <input type="hidden" name="id" value="<c:out value='${marcaje.id}' />" />
                    </c:if>
                    
                    <fieldset class="form-group"> 
                        <label>Fecha</label> <input type="datetime-local"
                        value="<c:out value='${marcaje.fecha}' />" class="form-control" 
                        name="fecha" required>
                    </fieldset>
                    <fieldset class="form-group"> 
                        <label>Tipo marcaje</label> <input type="text"
                        value="<c:out value='${marcaje.tipo_marcaje}' />" class="form-control" 
                        name="tipo_marcaje" required>
                    </fieldset>
                    <c:if test="${marcaje != null}">
                        <fieldset class="form-group" readonly> 
                        <label>ID Usuario</label> <input type="text"
                        value="<c:out value='${marcaje.usuarioid}' />" class="form-control" 
                        name="usuarioid" required readonly>
                    </fieldset>
                    </c:if>
                    <c:if test="${marcaje == null}">
                        <fieldset class="form-group" readonly> 
                        <label>ID Usuario</label> <input type="text"
                        value="<c:out value='${idUsuario}' />" class="form-control" 
                        name="usuarioid" required readonly>
                    </fieldset>
                    </c:if>
                        
                    <button type="submit" class="btn btn-success">Save</button> 
                    </form>
                    </div>
                </div>
            </div>
                        
    </body>
</html>
