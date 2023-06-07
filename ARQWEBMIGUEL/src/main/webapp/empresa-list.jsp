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
        <title>Empresas</title>
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
                margin-bottom: 100px; 
            }
            
            .container {
                margin-top: 30px; 
            }
            
            .logout-button {
            margin-bottom: 10px;
            display: inline-block;
            padding: 10px 20px;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            color: #fff;
            background-color: #FF3333;
            border-radius: 4px;
            transition: background-color 0.3s ease;
            }

            .logout-button:hover {
                background-color: #ff1b1b;
            }

            .edit-button {
                    font-size: 15px;
                    text-decoration: none;
                    padding: 5px 10px;
                    background-color: #17a2b8;
                    color: #fff;
                    border-radius: 4px;
                }
            
            .edit-button:hover {
                background-color: #138496;
            }
            
            .report-button {
                    font-size: 15px;
                    text-decoration: none;
                    padding: 5px 10px;
                    background-color: #17a2b8;
                    color: #fff;
                    border-radius: 4px;
                }
            
            .report-button:hover {
                background-color: #138496;
            }
            
            .delete-button {
                font-size: 15px;
                text-decoration: none;
                padding: 5px 10px;
                background-color: #dc3545;
                color: #fff;
                border-radius: 4px;
            }
            
            .delete-button:hover {
                background-color: #c82333;
            }
            
            .empresa-button {
                    font-size: 15px;
                    text-decoration: none;
                    padding: 5px 10px;
                    background-color: #17a2b8;
                    color: #fff;
                    border-radius: 4px;
                }
            
            .empresa-button:hover {
                background-color: #138496;
            }
            
            .menu-button {
                font-size: 15px;
                text-decoration: none;
                padding: 5px 10px;
                background-color: #1CC16C;
                color: #fff;
                border-radius: 4px;
            }
            
            .menu-button:hover {
                background-color: #25EB85;
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
                <h3 class="text-center">Lista de Empresas</h3>
                <hr>
                <br>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${empresas}" var="empresa">
                            <tr>
                                <td scope="row"><c:out value="${empresa.empresaid}" /></td>
                                <td><c:out value="${empresa.nombre_empresa}" /></td>
                                <%--<td><a href="UsuarioControlador?action=edit?userid=<c:out value="${user.userid}" />">Edit</a></td>--%>
                                <td><a href="EmpresaControlador?action=edit&empresaid=${empresa.empresaid}"" style="text-decoration: none; font-size: 15px" class="edit-button"">Editar</a></td>
                                <td><a href="EmpresaControlador?action=delete&empresaid=${empresa.empresaid}"" style="text-decoration: none; font-size: 15px" class="delete-button"">Eliminar</a></td>
                                <!--<td><a href="InformeControlador?action=informeEmpresa&empresaid=${empresa.empresaid}"" style="text-decoration: none; font-size: 15px">Report</a></td>-->
                                <td><a href="InformesControlador?action=menu&empresaid=${empresa.empresaid}&nombre_empresa=${empresa.nombre_empresa}"" style="text-decoration: none; font-size: 15px" class="report-button"">Report</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            <p><a href="EmpresaControlador?action=insert" style="text-decoration: none; font-size: 20px"class="empresa-button"">Añadir Empresa</a></p>
            <p><a href="/menu-principal-rrhh.jsp" style="text-decoration: none; font-size: 20px" class="menu-button"">Menú</a></p>
            
            <br>
            <footer>
                <p>Miguel Calvo Soria - Arquitectura Web y Sistemas C/S</p>
            </footer>
    </body>
</html>
