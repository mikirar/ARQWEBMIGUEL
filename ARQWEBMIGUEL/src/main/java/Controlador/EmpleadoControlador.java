/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.UsuarioDAO;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import Util.Common;
import static Util.Common.parseStringToTimestamp;
import Util.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miki
 */

/**
 * Controlador para la gestión de empleados.
 */
public class EmpleadoControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user-empleado-form.jsp";
    private static String LIST_EMPLEADO = "/empleado-list.jsp";
    private UsuarioDAO usuarioRepository;
    
    /**
     * Constructor de la clase EmpleadoControlador.
     * @throws SQLException Si ocurre algún error de SQL.
     */
    public EmpleadoControlador() throws SQLException {
        super();
        usuarioRepository = new UsuarioDAO();
    }
    
    /**
     * Método GET que maneja las solicitudes GET al controlador.
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de E/S.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String forward = "";
        Log.insertLog("Entramos en el doGet\n");
        String action = request.getParameter("action");
        Log.insertLog("Recogemos el parametro action con valor " + action+ "\n");
        
        // Comprobamos la acción solicitada
        if (action.equalsIgnoreCase("delete")) {
            Log.insertLog("Parametro valor DELETE");
            int userId = Integer.parseInt(request.getParameter("userid"));
            usuarioRepository.eliminarUsuario(userId);
            forward = LIST_EMPLEADO;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userid"));
            Usuario user = usuarioRepository.obtenerUsuarioPorId(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listEmpleado")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_EMPLEADO;
            HttpSession session = request.getSession();
            request.setAttribute("user", session.getAttribute("user"));
            request.setAttribute("marcajes", session.getAttribute("marcajes"));

        } else {
            Log.insertLog("Parametro valor vacio vamos a insertar\n");
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
        //return;
    }
    
    /**
     * Método POST que maneja las solicitudes POST al controlador.
     * @param request La solicitud HTTP
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de E/S.
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
        Timestamp fechaAlta = parseStringToTimestamp(fechaAltaString);
        
        usuario.setFecha_alta(fechaAlta);
        String fechaBajaString = request.getParameter("fecha_baja");
        Timestamp fechaBaja = Common.parseStringToTimestamp(fechaBajaString);
        
        usuario.setFecha_baja(fechaBaja);
        usuario.setTipo_usuario(TipoUsuario.valueOf(request.getParameter("tipo_usuario")));
 
        usuario.setUserid(Integer.parseInt(request.getParameter("userid")));
        String userid = request.getParameter("userid");
        
        // Comprobamos si es una inserción o una actualización
        if (userid == null || userid.isEmpty() || userid.equalsIgnoreCase("")) {
            Log.insertLog("Vamos a añadir el empleado\n");
            usuarioRepository.crearUsuario(usuario);
            
        } else {
            usuario.setUserid(Integer.parseInt(userid));
            Log.insertLog("Vamos a actualizar el empleado\n");
            usuarioRepository.actualizarUsuario(usuario);
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", usuario);
        request.setAttribute("user", usuario);
        RequestDispatcher view = request.getRequestDispatcher(LIST_EMPLEADO);            
        view.forward(request, response);
        
    }
}
