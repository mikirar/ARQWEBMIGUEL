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
                    <c:if test="${empresa != null}">
                        <form method="POST" action='EmpresaControlador' name="frmAddEmpresa"">
                    </c:if>
                    <c:if test="${empresa == null}">
                        <form method="POST" action='EmpresaControlador' name="frmAddEmpresa"">
                    </c:if>
                        
                    <caption>
                         <h2> 
                            <c:if test="${empresa != null}">
                               Edit Empresa 
                            </c:if>
                            <c:if test="${empresa == null}">
                               Add New Empresa 
                            </c:if> 
                         </h2>   
                    </caption>
                               
                    <c:if test="${empresa != null}">
                        <input type="hidden" name="empresaid" value="<c:out value='${empresa.empresaid}' />" />
                    </c:if>
                    
                    <fieldset class="form-group"> 
                        <label>Nombre Empresa</label> <input type="text"
                        value="<c:out value='${empresa.nombre_empresa}' />" class="form-control" 
                        name="nombre_empresa" required>
                    </fieldset>
                        
                    <button type="submit" class="btn btn-success">Save</button> 
                    </form>
                    </div>
                </div>
            </div>
                        
    </body>
</html>
