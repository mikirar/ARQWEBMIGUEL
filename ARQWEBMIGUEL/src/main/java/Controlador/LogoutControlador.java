/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.MarcajeDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.Marcaje;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import Util.Common;
import Util.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miki
 */

/**
 * Controlador para el manejo del logout.
 * 
 * Este controlador gestiona las solicitudes relacionadas con la finalización de sesión.
 * Contiene métodos para el manejo de solicitudes GET y POST.
 */
public class LogoutControlador extends HttpServlet{
    
    /**
     * Maneja las solicitudes GET del logout.
     * 
     * Este método se llama cuando se realiza una solicitud GET al controlador de logout.
     * No realiza ninguna acción.
     *
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Maneja las solicitudes POST del logout.
     * 
     * Este método se llama cuando se realiza una solicitud POST al controlador de logout.
     * Realiza las acciones necesarias para cerrar la sesión del usuario y redirige a la página de inicio.
     *
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Log.insertLog("Hacemos logout\n");
        
        response.setContentType("text/html");
        
        // Eliminar la cookie de inicio de sesión
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")){
                    loginCookie = cookie;
                    break;
                }
            }
        }
        if(loginCookie != null){
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        
        //Reseteamos cache del navegador
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
        // Invalidar la sesión actual y redirigir a la página de inicio
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        if (session.getAttribute("marcajes") != null) {
            session.removeAttribute("marcajes");
        }
        if (session.getAttribute("empresas") != null) {
            session.removeAttribute("empresas");
        }
        if (session.getAttribute("proyectos") != null) {
            session.removeAttribute("proyectos");
        }
        session.invalidate();        
        response.sendRedirect("index.jsp");

    }
}

