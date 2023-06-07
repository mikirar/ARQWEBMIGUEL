/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.EmpresaDAO;
import Modelo.DAO.MarcajeDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.Empresa;
import Modelo.Marcaje;
import Modelo.Proyecto;
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
 * Controlador para el manejo del login.
 * 
 * Este controlador gestiona las solicitudes relacionadas con el inicio de sesión.
 * Contiene métodos para el manejo de solicitudes GET y POST.
 */
public class LoginControlador extends HttpServlet{
    String forward;
    private String userID;
    private String password;
    UsuarioDAO usuarioDao = new UsuarioDAO();
    MarcajeDAO marcajeDao = new MarcajeDAO();
    EmpresaDAO empresaDao = new EmpresaDAO();
    ProyectoDAO proyectoDao = new ProyectoDAO();
    
    
    /**
     * Maneja las solicitudes GET del inicio de sesión.
     * 
     * Este método se llama cuando se realiza una solicitud GET al controlador de inicio de sesión.
     * Redirige al usuario a la página de inicio.
     *
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Log.insertLog("Entramos por el doGet");
        forward="index.jsp";
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    
    /**
     * Maneja las solicitudes POST del inicio de sesión.
     * 
     * Este método se llama cuando se realiza una solicitud POST al controlador de inicio de sesión.
     * Realiza la autenticación del usuario y redirige según el tipo de usuario y la validez de las credenciales.
     *
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Log.insertLog("Entramos por el doPost\n");
        // Obtener parámetros de la solicitud para el nombre de usuario y la contraseña
        String user = request.getParameter("usuario");
        String pwd = request.getParameter("password");
        Usuario usuarioBd = usuarioDao.obtenerUsuarioPorUsernameYPassword(user, pwd);
        
        try {
        
            userID = usuarioBd.getUsername();
            password = usuarioBd.getPassword();

            if(userID.equals(user) && password.equals(pwd) && usuarioBd.getTipo_usuario() == TipoUsuario.A){
                Cookie loginCookie = new Cookie("user",user);
                Cookie cookie = new Cookie("msg","4558858858585");
                //setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                response.addCookie(cookie);
                Log.insertLog("Logeamos como usuario administrador\n");
                response.sendRedirect("menu-principal-rrhh.jsp");
            } else if(userID.equals(user) && password.equals(pwd) && usuarioBd.getTipo_usuario() == TipoUsuario.U) {
                Cookie loginCookie = new Cookie("user",user);
                Cookie cookie = new Cookie("msg","4558858858585");
                loginCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                response.addCookie(cookie);
                //Metemos en la sesion tanto el usuario como su marcaje
                HttpSession session = request.getSession();
                List<Marcaje> marcajes = new ArrayList<>();
                marcajes = marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(usuarioBd.getUserid());
                List<Empresa> empresas = new ArrayList<>();
                empresas = empresaDao.obtenerEmpresasPorIdUsuario(usuarioBd.getUserid());
                List<Proyecto> proyectos = new ArrayList<>();
                proyectos = proyectoDao.obtenerTodosLosProyectosPorIdUsuario(usuarioBd.getUserid());
                session.setAttribute("user", usuarioBd);
                session.setAttribute("marcajes", marcajes);
                session.setAttribute("empresas", empresas);
                session.setAttribute("proyectos", proyectos);
                request.setAttribute("user", usuarioBd);
                request.setAttribute("marcajes", marcajes);
                request.setAttribute("empresas", empresas);
                request.setAttribute("proyectos", proyectos);
             
                Log.insertLog("Logeamos como usuario\n");
                response.sendRedirect("EmpleadoControlador?action=listEmpleado");

            }
            else {
                Log.insertLog("fallo en el login\n");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("index.jsp");
                PrintWriter out= response.getWriter();
                out.println("<font color=red>Usuario o password son incorrectos.</font>");
                rd.include(request, response);
            }

        }
        catch (Exception e) {
                Log.insertLog("Intento de inicio de sesión erróneo\n");
                    response.sendRedirect("/error_inicio_sesion.jsp");
            }
    }
}
