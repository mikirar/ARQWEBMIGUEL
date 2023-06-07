/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.UsuarioDAO;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import Util.Common;
import Util.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miki
 */

/**
 * Controlador que maneja las solicitudes relacionadas con los usuarios.
 * Maneja las solicitudes relacionadas con la creación, edición, eliminación y listado de usuarios.
 * Extiende la clase HttpServlet para recibir las solicitudes HTTP.
 */
public class UsuarioControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user-form.jsp";
    private static String LIST_USER = "/user-list.jsp";
    private UsuarioDAO usuarioRepository;
    
    /**
     * Constructor de la clase UsuarioControlador.
     * Se encarga de inicializar el UsuarioDAO.
     *
     * @throws SQLException Si ocurre un error de SQL.
     */
    public UsuarioControlador() throws SQLException {
        super();
        usuarioRepository = new UsuarioDAO();
    }
    
    /**
     * Método para manejar las solicitudes GET.
     * Se encarga de procesar las solicitudes relacionadas con los usuarios enviadas mediante GET.
     *
     * @param request  La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String forward = "";
        Log.insertLog("Entramos en el doGet\n");
        String action = request.getParameter("action");
        Log.insertLog("Recogemos el parametro action con valor " + action+ "\n");
        if (action.equalsIgnoreCase("delete")) {
            Log.insertLog("Parametro valor DELETE");
            int userId = Integer.parseInt(request.getParameter("userid"));
            usuarioRepository.eliminarUsuario(userId);
            forward = LIST_USER;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userid"));
            Usuario user = usuarioRepository.obtenerUsuarioPorId(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_USER;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        } else {
            Log.insertLog("Parametro valor vacio vamos a insertar\n");
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }
    
    /**
     * Método para manejar las solicitudes POST.
     * Se encarga de procesar las solicitudes relacionadas con los usuarios enviadas mediante POST.
     *
     * @param request  La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Log.insertLog("Entramos por el doPost\n");
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getParameter("username"));
        usuario.setPassword(request.getParameter("password"));                
        usuario.setDni(request.getParameter("dni"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidos(request.getParameter("apellidos"));
        
        String fechaAltaString = request.getParameter("fecha_alta");
        Timestamp fechaAlta = Common.parseStringToTimestamp(fechaAltaString);
        usuario.setFecha_alta(fechaAlta);
        String fechaBajaString = request.getParameter("fecha_baja");
        Timestamp fechaBaja = Common.parseStringToTimestamp(fechaBajaString);
        usuario.setFecha_baja(fechaBaja);
        
        usuario.setTipo_usuario(TipoUsuario.valueOf(request.getParameter("tipo_usuario")));
        
        String userid = request.getParameter("userid");
        if (userid == null || userid.isEmpty() || userid.equalsIgnoreCase("")) {
            Log.insertLog("Vamos a añadir el usuario\n");
            usuarioRepository.crearUsuario(usuario);
            
        } else {
            usuario.setUserid(Integer.parseInt(userid));
            Log.insertLog("Vamos a añadir el usuario\n");
            usuarioRepository.actualizarUsuario(usuario);
        }
        request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);            
        view.forward(request, response);
    }
}
