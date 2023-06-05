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
/*import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

/**
 *
 * @author miki
 */


public class ProyectoControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/proyecto-form.jsp";
    private static String LIST_PROYECTO = "/proyecto-list.jsp";
    private ProyectoDAO proyectoDao;
    
    public ProyectoControlador() throws SQLException {
        super();
        proyectoDao = new ProyectoDAO();
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
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
        
        //return;
    }
    
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

            /*try {
                //Log.insertLog("Vamos a añadir el usuario\n");
                usuarioRepository.crearUsuario(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
        } else {
            proyecto.setProyectoid(Integer.parseInt(proyectoid));
            Log.insertLog("Vamos a actualizar el proyecto\n");
            proyectoDao.actualizarProyecto(proyecto);
        }
        request.setAttribute("proyectos", proyectoDao.obtenerTodosLosProyectos());
        RequestDispatcher view = request.getRequestDispatcher(LIST_PROYECTO);            
        view.forward(request, response);
        //return;
    }
}
