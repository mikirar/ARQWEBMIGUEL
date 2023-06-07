/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.ProyectoDAO;
import Modelo.Proyecto;
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
 * Controlador para la gestión de proyectos.
 * Maneja las solicitudes relacionadas con la creación, edición, eliminación y listado de proyectos.
 * Extiende la clase HttpServlet.
 * 
 */
public class ProyectoControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/proyecto-form.jsp";
    private static String LIST_PROYECTO = "/proyecto-list.jsp";
    private ProyectoDAO proyectoDao;
    
    /**
     * Constructor de la clase.
     * Inicializa el ProyectoDAO.
     *
     * @throws SQLException Si ocurre un error en la conexión con la base de datos.
     */
    public ProyectoControlador() throws SQLException {
        super();
        proyectoDao = new ProyectoDAO();
    }
    
    /**
     * Método para manejar las solicitudes GET.
     * Se encarga de procesar las solicitudes relacionadas con los proyectos enviadas mediante GET.
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
            int proyectoId = Integer.parseInt(request.getParameter("proyectoid"));
            proyectoDao.eliminarProyecto(proyectoId);
            forward = LIST_PROYECTO;
            request.setAttribute("proyectos", proyectoDao.obtenerTodosLosProyectos());
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int proyectoId = Integer.parseInt(request.getParameter("proyectoid"));
            Proyecto proyecto = proyectoDao.obtenerProyectoPorId(proyectoId);
            request.setAttribute("proyecto", proyecto);
        } else if (action.equalsIgnoreCase("listProyecto")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_PROYECTO;
            request.setAttribute("proyectos", proyectoDao.obtenerTodosLosProyectos());
        } else {
            Log.insertLog("Parametro valor vacio vamos a insertar\n");
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }
    
    /**
     * Método para manejar las solicitudes POST.
     * Se encarga de procesar las solicitudes relacionadas con los proyectos enviadas mediante POST.
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
        //processRequest(request, response);
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(request.getParameter("nombre"));
        String empresaIdString = request.getParameter("empresaid");
        proyecto.setEmpresaid(Integer.parseInt(empresaIdString));
        String proyectoid = request.getParameter("proyectoid");
        if (proyectoid == null || proyectoid.isEmpty() || proyectoid.equalsIgnoreCase("")) {
            Log.insertLog("Vamos a añadir el proyecto\n");
            proyectoDao.crearProyecto(proyecto);
            
        } else {
            proyecto.setProyectoid(Integer.parseInt(proyectoid));
            Log.insertLog("Vamos a actualizar el proyecto\n");
            proyectoDao.actualizarProyecto(proyecto);
        }
        request.setAttribute("proyectos", proyectoDao.obtenerTodosLosProyectos());
        RequestDispatcher view = request.getRequestDispatcher(LIST_PROYECTO);            
        view.forward(request, response);
    }
}
