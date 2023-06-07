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
        <title>Informes</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-gg0yROixCbMQv3Xipma34MD+dH/1f0784/j6cY/iJTQUOhcWr7x9JvORXT2MZWIT"
        crossorigin="anonymous">
        <style>
            header {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                text-align: center;
                background-color: #33D1FF;
                color: black;
                padding: 10px;
                font-family: "Abril fatface";
                margin-bottom: 70px;
            }
            
            footer {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                text-align: center;
                background-color: #33D1FF;
                color: black;
                padding: 10px;
                font-family: "Abril fatface";
            }
            
            body {
                font-family: "Abril Fatface";
                margin-top: 70px; 
                margin-bottom: 70px; 
            }
            
            .container {
                margin-top: 30px; 
            }
        </style>
    </head>
    
    <header>
        <h2 style="text-align: center; background-color: #33D1FF; color: black; padding: 10px; font-family: 'Abril Fatface';">Ingenieros al peso S.A</h2>
    </header>
    
    <body>
        <br>
        
        <div class="row">
            <!--<div class="alert alert-success" *ngIf='message '>{{message}}s/div» "-->
            <div class="container">
                <h3>Solicitar informe</h3>
                <!--?action=generarInforme&empresaid=$"{empresa.empresaid}&userid=$"{user.id_user}&proyecto=$"{proyecto.proyectoid}}-->
                <form method="POST" action='InformesControlador' name="frmGetInforme">
                <%--<form method="GET" action="<%
                if (${empresaid} != null || ${empresaid} != "") {
                    out.print("InformesControlador?action=informeEmpresa&empresaid="${empresaid});
                } else if (${userid} != null || ${userid} != "") {
                    out.print("InformesControlador?action=informeUsuario&usuarioid="${usuarioid});
                } else if (${proyectoid} != null || ${proyectoid} != "") {
                    out.print("InformesControlador?action=informeUsuario&usuarioid="${proyectoid}");
                }
                %>" name="frmGetInforme"> --%>
                <fieldset>
                        <c:if test="${empresaid != null}">
                            <input type="hidden" name="empresaid" value="<c:out value='${empresaid}' />" />
                        </c:if>
                        <c:if test="${nombre_empresa != null}">
                            <input type="hidden" name="nombre_empresa" value="<c:out value='${nombre_empresa}' />" />
                        </c:if>
                        <c:if test="${userid != null}">
                            <input type="hidden" name="userid" value="<c:out value='${userid}' />" />
                        </c:if>
                        <c:if test="${nombre_usuario != null}">
                            <input type="hidden" name="nombre_usuario" value="<c:out value='${nombre_usuario}' />" />
                        </c:if>
                        <c:if test="${proyectoid != null}">
                            <input type="hidden" name="proyectoid" value="<c:out value='${proyectoid}' />" />
                        </c:if>
                        <c:if test="${nombre_proyecto != null}">
                            <input type="hidden" name="nombre_proyecto" value="<c:out value='${nombre_proyecto}' />" />
                        </c:if>
                            
                        <legend>Seleccionar tipo de informe:</legend>

                        <label>
                            <input type="radio" name="tipoInforme" value="semanal" checked onclick="mostrarFechaFin('semanal')"> Semanal
                        </label>

                        <label>
                            <input type="radio" name="tipoInforme" value="mensual" onclick="mostrarFechaFin('mensual')"> Mensual
                        </label>

                        <label>
                            <input type="radio" name="tipoInforme" value="anual" onclick="mostrarFechaFin('anual')"> Anual
                        </label>

                        <label>
                            <input type="radio" name="tipoInforme" value="periodo" onclick="mostrarFechaFin('periodo')"> Periodo
                        </label>
                    </fieldset>
                    
                    <fieldset id="fecha_inicio" class="form-group"> 
                            <label>Fecha inicio informe</label> <input type="datetime-local" class="form-control" 
                            name="fecha_inicio" required>
                    </fieldset>
                    
                    <fieldset id="fecha_fin" class="form-control" style="display: none;">
                        <label>Fecha fin informe</label> <input type="datetime-local" class="form-control" name="fecha_fin">
                    </fieldset>
                            
                    <br>
                    <button type="submit" class="btn btn-success">Generar</button> 
                            
                </form>
            <!--<p><a href="InformeControlador?action=insert">Informe</a></p> -->
            
                            
                <script>
                    function mostrarFechaFin(tipoInforme) {
                        if (tipoInforme === 'periodo') {
                            document.getElementById('fecha_inicio').style.display = 'block';
                            document.getElementById('fecha_fin').style.display = 'block';
                        } else {
                            document.getElementById('fecha_inicio').style.display = 'block';
                            document.getElementById('fecha_fin').style.display = 'none';
                        }
                    }
                </script>
            </div>
            
            <footer>
                <p>Miguel Calvo Soria - Arquitectura Web y Sistemas C/S</p>
            </footer>
    </body>

</html>
