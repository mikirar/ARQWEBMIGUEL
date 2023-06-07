/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.UsuarioDAO;
import Modelo.DAO.UsuarioProyectoDAO;
import Modelo.TipoUsuario;
import Modelo.UsuarioProyecto;
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
 * Controlador para la gestión de usuarios y proyectos.
 * Maneja las solicitudes relacionadas con la creación, edición, eliminación y listado de usuario_proyecto.
 * Extiende la clase HttpServlet para recibir las solicitudes HTTP.
 */

public class UsuarioProyectoControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user-project-form.jsp";
    private static String LIST_USER_PROJECT = "/user-project-list.jsp";
    private UsuarioProyectoDAO usuarioProyectoDao;
    
    /**
     * Constructor de la clase. Inicializa el objeto UsuarioProyectoDAO.
     * @throws SQLException si ocurre un error en la conexión con la base de datos.
     */
    public UsuarioProyectoControlador() throws SQLException {
        super();
        usuarioProyectoDao = new UsuarioProyectoDAO();
    }
    
    /**
     * Maneja las peticiones GET al servlet.
     * @param request la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error en el servlet.
     * @throws IOException si ocurre un error de entrada/salida.
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
            int id = Integer.parseInt(request.getParameter("id"));
            usuarioProyectoDao.eliminarUsuarioProyecto(id);
            forward = LIST_USER_PROJECT;
            request.setAttribute("usuariosproyectos", usuarioProyectoDao.obtenerTodosLosUsuarioProyecto());
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            UsuarioProyecto usuarioProyecto = usuarioProyectoDao.obtenerUsuarioProyectoPorId(id);
            request.setAttribute("usuarioproyecto", usuarioProyecto);
        } else if (action.equalsIgnoreCase("listUserProject")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_USER_PROJECT;
            request.setAttribute("usuariosproyectos", usuarioProyectoDao.obtenerTodosLosUsuarioProyecto());
        } else {
            Log.insertLog("Parametro valor vacio vamos a insertar\n");
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }
    
    /**
     * Maneja las peticiones POST al servlet.
     * @param request la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error en el servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Log.insertLog("Entramos por el doPost\n");
        UsuarioProyecto usuarioProyecto = new UsuarioProyecto();
        usuarioProyecto.setUserid(Integer.parseInt(request.getParameter("userid")));
        usuarioProyecto.setProyectoid(Integer.parseInt(request.getParameter("proyectoid")));
        String fechaAltaString = request.getParameter("fecha_alta");
        Timestamp fechaAlta = Common.parseStringToTimestamp(fechaAltaString);
        usuarioProyecto.setFecha_alta(fechaAlta);
        String fechaBajaString = request.getParameter("fecha_baja");
        Timestamp fechaBaja = Common.parseStringToTimestamp(fechaBajaString);
        usuarioProyecto.setFecha_baja(fechaBaja);

        String id = request.getParameter("id");
        if (id == null || id.isEmpty() || id.equalsIgnoreCase("")) {
            Log.insertLog("Vamos a añadir el usuario proyecto\n");
            usuarioProyectoDao.crearUsuarioProyecto(usuarioProyecto);
            
        } else {
            usuarioProyecto.setId(Integer.parseInt(id));
            Log.insertLog("Vamos a añadir el usuario\n");
            usuarioProyectoDao.actualizarUsuarioProyecto(usuarioProyecto);
        }
        request.setAttribute("usuariosproyectos", usuarioProyectoDao.obtenerTodosLosUsuarioProyecto());
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER_PROJECT);            
        view.forward(request, response);
    }
}
