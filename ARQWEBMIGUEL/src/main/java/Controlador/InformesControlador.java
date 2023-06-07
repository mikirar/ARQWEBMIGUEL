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

/**
 * Controlador para la generación de informes
 */
public class InformesControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String MENU = "/menu-informe-marcaje.jsp";
    private static String INFORME_EMPRESA = "/informe-empresa.jsp";
    private static String INFORME_USUARIO = "/informe-usuario.jsp";
    private static String INFORME_PROYECTO = "/informe-proyecto.jsp";
    private EmpresaDAO empresaDao;
    private UsuarioDAO usuarioDao;
    private ProyectoDAO proyectoDao;
    private MarcajeDAO marcajeDao;
    
    /**
     * Constructor de la clase InformesControlador.
     * @throws SQLException Si ocurre algún error de SQL.
     */
    public InformesControlador() throws SQLException {
        super();
        empresaDao = new EmpresaDAO();
        usuarioDao = new UsuarioDAO();
        proyectoDao = new ProyectoDAO();
        marcajeDao = new MarcajeDAO();
    }
    
    /**
     * Método GET para manejar las solicitudes de informes
     *
     * @param request  El objeto HttpServletRequest
     * @param response El objeto HttpServletResponse
     * @throws ServletException Si ocurre un error de Servlet
     * @throws IOException      Si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Log.insertLog("Entramos en el doGet\n");
        
        // Recuperar parámetros de la solicitud
        String empresaIdString = request.getParameter("empresaid");
        String nombre_empresa = request.getParameter("nombre_empresa");
        String userIdString = request.getParameter("userid");
        String nombre_usuario = request.getParameter("nombre_usuario");
        String proyectoIdString = request.getParameter("proyectoid");
        String nombre_proyecto = request.getParameter("nombre_proyecto");
        
        // Establecer atributos en el objeto request
        request.setAttribute("empresaid", empresaIdString);
        request.setAttribute("nombre_empresa", nombre_empresa);
        request.setAttribute("userid", userIdString);
        request.setAttribute("nombre_usuario", nombre_usuario);
        request.setAttribute("proyectoid", proyectoIdString);
        request.setAttribute("nombre_proyecto", nombre_proyecto);
        
        //Redirigimos directamente al menú con los parámetros 
        String forward = MENU;
        String action = request.getParameter("action");
        Log.insertLog("Recogemos el parametro action con valor " + action+ "\n");
        
        Log.insertLog("Redirigimos al menu de informes\n");
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
       
    }
    
    
    /**
     * Método POST que maneja las solicitudes POST al controlador.
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de E/S.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Inicializamos variables
        Log.insertLog("Entramos por el doPost\n");
        String forward = MENU;
        String empresaIdString = request.getParameter("empresaid");
        String nombre_empresa = request.getParameter("nombre_empresa");
        String userIdString = request.getParameter("userid");
        String nombre_usuario = request.getParameter("nombre_usuario");
        String proyectoIdString = request.getParameter("proyectoid");
        String nombre_proyecto = request.getParameter("nombre_proyecto");
        
        //Recuperamos parámetros de la request para generar el informe
        String tipoInforme = request.getParameter("tipoInforme");
        String fechaInicioString = request.getParameter("fecha_inicio");
        Timestamp fechaInicio = Common.parseStringToTimestamp(fechaInicioString);
        String fechaFinString = request.getParameter("fecha_fin");
        Timestamp fechaFin = Common.parseStringToTimestamp(fechaFinString);
        
        //Establecer atributos en el objeto request
        request.setAttribute("empresaid", empresaIdString);
        request.setAttribute("nombre_empresa", nombre_empresa);
        request.setAttribute("userid", userIdString);
        request.setAttribute("nombre_usuario", nombre_usuario);
        request.setAttribute("proyectoid", proyectoIdString);
        request.setAttribute("nombre_proyecto", nombre_proyecto);
        
        
        //Generamos informe según parámetros
        if (empresaIdString != null && !empresaIdString.equalsIgnoreCase("")) {
            Log.insertLog("Parametro valor empresa\n");
            if (tipoInforme.equalsIgnoreCase("semanal")){
                Log.insertLog("Parametro valor semanal\n");
                int empresaId = Integer.parseInt(empresaIdString);
                request.setAttribute("empresaid", empresaId);
                forward = INFORME_EMPRESA;
                request.setAttribute("marcajesEmpresa", marcajeDao.obtenerTodasLosMarcajesPorIdEmpresaSemanal(empresaId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("mensual")) {
                Log.insertLog("Parametro valor mensual\n");
                int empresaId = Integer.parseInt(empresaIdString);
                request.setAttribute("empresaid", empresaId);
                forward = INFORME_EMPRESA;
                request.setAttribute("marcajesEmpresa", marcajeDao.obtenerTodasLosMarcajesPorIdEmpresaMensual(empresaId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("anual")) {
                Log.insertLog("Parametro valor anual\n");
                int empresaId = Integer.parseInt(empresaIdString);
                request.setAttribute("empresaid", empresaId);
                forward = INFORME_EMPRESA;
                request.setAttribute("marcajesEmpresa", marcajeDao.obtenerTodasLosMarcajesPorIdEmpresaAnual(empresaId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("periodo")) {
                Log.insertLog("Parametro valor mensual\n");
                int empresaId = Integer.parseInt(empresaIdString);
                request.setAttribute("empresaid", empresaId);
                forward = INFORME_EMPRESA;
                request.setAttribute("marcajesEmpresa", marcajeDao.obtenerTodasLosMarcajesPorIdEmpresaPeriodo(empresaId, fechaInicio, fechaFin));
                
            }
               
        } else if (userIdString != null && !userIdString.equalsIgnoreCase("")){
            Log.insertLog("Parametro valor usuario\n");
            if (tipoInforme.equalsIgnoreCase("semanal")){
                Log.insertLog("Parametro valor semanal\n");
                int usuarioId = Integer.parseInt(userIdString);
                request.setAttribute("userid", usuarioId);
                forward = INFORME_USUARIO;
                request.setAttribute("marcajesUsuario", marcajeDao.obtenerTodasLosMarcajesPorIdUsuarioSemanal(usuarioId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("mensual")) {
                Log.insertLog("Parametro valor mensual\n");
                int usuarioId = Integer.parseInt(userIdString);
                request.setAttribute("userid", usuarioId);
                forward = INFORME_USUARIO;
                request.setAttribute("marcajesUsuario", marcajeDao.obtenerTodasLosMarcajesPorIdUsuarioMensual(usuarioId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("anual")) {
                Log.insertLog("Parametro valor anual\n");
                int usuarioId = Integer.parseInt(userIdString);
                request.setAttribute("userid", usuarioId);
                forward = INFORME_USUARIO;
                request.setAttribute("marcajesUsuario", marcajeDao.obtenerTodasLosMarcajesPorIdUsuarioAnual(usuarioId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("periodo")) {
                Log.insertLog("Parametro valor mensual\n");
                int usuarioId = Integer.parseInt(userIdString);
                request.setAttribute("userid", usuarioId);
                forward = INFORME_USUARIO;
                request.setAttribute("marcajesUsuario", marcajeDao.obtenerTodasLosMarcajesPorIdUsuarioPeriodo(usuarioId, fechaInicio, fechaFin));
                
            }
        }
        
        else if (proyectoIdString != null && !proyectoIdString.equalsIgnoreCase("")){
            Log.insertLog("Parametro valor proyecto\n");
            if (tipoInforme.equalsIgnoreCase("semanal")){
                Log.insertLog("Parametro valor semanal\n");
                int proyectoId = Integer.parseInt(proyectoIdString);
                request.setAttribute("proyectoid", proyectoId);
                forward = INFORME_PROYECTO;
                request.setAttribute("marcajeProyecto", marcajeDao.obtenerTodasLosMarcajesPorIdProyectoSemanal(proyectoId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("mensual")) {
                Log.insertLog("Parametro valor mensual\n");
                int proyectoId = Integer.parseInt(proyectoIdString);
                request.setAttribute("proyectoid", proyectoId);
                forward = INFORME_PROYECTO;
                request.setAttribute("marcajeProyecto", marcajeDao.obtenerTodasLosMarcajesPorIdProyectoMensual(proyectoId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("anual")) {
                Log.insertLog("Parametro valor anual\n");
                int proyectoId = Integer.parseInt(proyectoIdString);
                request.setAttribute("proyectoid", proyectoId);
                forward = INFORME_PROYECTO;
                request.setAttribute("marcajeProyecto", marcajeDao.obtenerTodasLosMarcajesPorIdProyectoAnual(proyectoId, fechaInicio));
                
            } else if (tipoInforme.equalsIgnoreCase("periodo")) {
                Log.insertLog("Parametro valor mensual\n");
                int proyectoId = Integer.parseInt(proyectoIdString);
                request.setAttribute("proyectoid", proyectoId);
                forward = INFORME_PROYECTO;
                request.setAttribute("marcajeProyecto", marcajeDao.obtenerTodasLosMarcajesPorIdProyectoPeriodo(proyectoId, fechaInicio, fechaFin));
                
            }
        }
       
        RequestDispatcher view = request.getRequestDispatcher(forward);            
        view.forward(request, response);
        //return;
    }
}
