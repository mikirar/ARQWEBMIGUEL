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
    
    public InformesControlador() throws SQLException {
        super();
        empresaDao = new EmpresaDAO();
        usuarioDao = new UsuarioDAO();
        proyectoDao = new ProyectoDAO();
        marcajeDao = new MarcajeDAO();
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
        
        Log.insertLog("Entramos en el doGet\n");

        String empresaIdString = request.getParameter("empresaid");
        String nombre_empresa = request.getParameter("nombre_empresa");
        String userIdString = request.getParameter("userid");
        String nombre_usuario = request.getParameter("nombre_usuario");
        String proyectoIdString = request.getParameter("proyectoid");
        String nombre_proyecto = request.getParameter("nombre_proyecto");
        
        request.setAttribute("empresaid", empresaIdString);
        request.setAttribute("nombre_empresa", nombre_empresa);
        request.setAttribute("userid", userIdString);
        request.setAttribute("nombre_usuario", nombre_usuario);
        request.setAttribute("proyectoid", proyectoIdString);
        request.setAttribute("nombre_proyecto", nombre_proyecto);
        System.out.println("Entro al get");
        System.out.println("Empresaid " + empresaIdString);
        System.out.println("Userid " + userIdString);
        System.out.println("Proyectoid " + proyectoIdString);
        /*
        if (empresaIdString != null && !empresaIdString.equalsIgnoreCase("")) {
            int empresaId = Integer.parseInt(empresaIdString);
            request.setAttribute("empresaid", empresaId);
        }
        else if (userIdString != null && !userIdString.equalsIgnoreCase("")) {
            int userId = Integer.parseInt(userIdString);
            request.setAttribute("userid", userId);
        }
        else if (proyectoIdString != null && !proyectoIdString.equalsIgnoreCase("")) {
            int proyectoId = Integer.parseInt(proyectoIdString);
            request.setAttribute("proyectoid", proyectoId);
        }*/
        
        String forward = MENU;
        String action = request.getParameter("action");
        Log.insertLog("Recogemos el parametro action con valor " + action+ "\n");
        
        /*
        if (action.equalsIgnoreCase("informeEmpresa")) {
            //int empresaId = Integer.parseInt(request.getParameter("empresaid"));
            //request.setAttribute("empresaid", empresaId);
            Log.insertLog("Parametro valor informeEmpresa\n");
            forward = INFORME_EMPRESA;
            request.setAttribute("marcajesEmpresa", marcajeDao.obtenerTodasLosMarcajesPorIdEmpresa(empresaId));
        } else if (action.equalsIgnoreCase("informeUsuario")) {
            Log.insertLog("Parametro valor informeUsuario\n");
            forward = INFORME_USUARIO;
            request.setAttribute("marcajesUsuario", marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(userId));
        } else if (action.equalsIgnoreCase("informeProyecto")) {
            Log.insertLog("Parametro valor informeProyecto\n");
            forward = INFORME_PROYECTO;
            request.setAttribute("marcajesProyecto", marcajeDao.obtenerTodasLosMarcajesPorIdProyecto(proyectoId));
        } */
        
        /*
        if (action.equalsIgnoreCase("generarInforme")) {
            Log.insertLog("Parametro valor generarInforme\n");
            if (empresaIdString != null && !empresaIdString.equalsIgnoreCase("")) {
                Log.insertLog("Parametro valor empresa\n");
                int empresaId = Integer.parseInt(empresaIdString);
                request.setAttribute("empresaid", empresaId);
                forward = INFORME_EMPRESA;
                request.setAttribute("marcajesEmpresa", marcajeDao.obtenerTodasLosMarcajesPorIdEmpresa(empresaId));
                
            } else if (userIdString != null && !userIdString.equalsIgnoreCase("")) {
                Log.insertLog("Parametro valor usuario\n");
                int userId = Integer.parseInt(userIdString);
                request.setAttribute("userid", userId);
                forward = INFORME_USUARIO;
                request.setAttribute("marcajesUsuario", marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(userId));
                        
            } else if (proyectoIdString != null && !proyectoIdString.equalsIgnoreCase("")) {
                Log.insertLog("Parametro valor proyecto\n");
                int proyectoId = Integer.parseInt(proyectoIdString);
                request.setAttribute("proyectoid", proyectoId);
                forward = INFORME_PROYECTO;
                request.setAttribute("marcajesProyecto", marcajeDao.obtenerTodasLosMarcajesPorIdProyecto(proyectoId));
            }
        }*/
        
        //else {
        Log.insertLog("Parametro valor vacio vamos al menu\n");
        forward = MENU;
        //}
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
        //return;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Log.insertLog("Entramos por el doPost\n");
        String forward = MENU;
        String empresaIdString = request.getParameter("empresaid");
        String nombre_empresa = request.getParameter("nombre_empresa");
        String userIdString = request.getParameter("userid");
        String nombre_usuario = request.getParameter("nombre_usuario");
        String proyectoIdString = request.getParameter("proyectoid");
        String nombre_proyecto = request.getParameter("nombre_proyecto");
        
        String tipoInforme = request.getParameter("tipoInforme");
        String fechaInicioString = request.getParameter("fecha_inicio");
        Timestamp fechaInicio = Common.parseStringToTimestamp(fechaInicioString);
        String fechaFinString = request.getParameter("fecha_fin");
        Timestamp fechaFin = Common.parseStringToTimestamp(fechaFinString);
        
        request.setAttribute("empresaid", empresaIdString);
        request.setAttribute("nombre_empresa", nombre_empresa);
        request.setAttribute("userid", userIdString);
        request.setAttribute("nombre_usuario", nombre_usuario);
        request.setAttribute("proyectoid", proyectoIdString);
        request.setAttribute("nombre_proyecto", nombre_proyecto);
        
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
       
        RequestDispatcher view = request.getRequestDispatcher(forward);            
        view.forward(request, response);
        //return;
    }
}
