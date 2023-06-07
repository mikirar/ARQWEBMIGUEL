/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.EmpresaDAO;
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

/**
 *
 * @author miki
 */


/**
 * Controlador para la gestión de empresas.
 */
public class EmpresaControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/empresa-form.jsp";
    private static String LIST_EMPRESA = "/empresa-list.jsp";
    private EmpresaDAO empresaDao;
    
    /**
     * Constructor de la clase EmpresaControlador.
     * @throws SQLException Si ocurre algún error de SQL.
     */
    public EmpresaControlador() throws SQLException {
        super();
        empresaDao = new EmpresaDAO();
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
            int empresaId = Integer.parseInt(request.getParameter("empresaid"));
            empresaDao.eliminarEmpresa(empresaId);
            forward = LIST_EMPRESA;
            request.setAttribute("empresas", empresaDao.obtenerTodasLasEmpresa());
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int empresaId = Integer.parseInt(request.getParameter("empresaid"));
            Empresa empresa = empresaDao.obtenerEmpresaPorId(empresaId);
            request.setAttribute("empresa", empresa);
        } else if (action.equalsIgnoreCase("listEmpresa")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_EMPRESA;
            request.setAttribute("empresas", empresaDao.obtenerTodasLasEmpresa());
        } else {
            Log.insertLog("Parametro valor vacio vamos a insertar\n");
            forward = INSERT_OR_EDIT;
        }
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

        Log.insertLog("Entramos por el doPost\n");
        
        Empresa empresa = new Empresa();
        empresa.setNombre_empresa(request.getParameter("nombre_empresa"));

        String empresaid = request.getParameter("empresaid");
        // Comprobamos si es una inserción o una actualización
        if (empresaid == null || empresaid.isEmpty() || empresaid.equalsIgnoreCase("")) {
            Log.insertLog("Vamos a añadir el usuario\n");
            empresaDao.crearEmpresa(empresa);
            
        } else {
            empresa.setEmpresaid(Integer.parseInt(empresaid));
            Log.insertLog("Vamos a actualizar la empresa\n");
            empresaDao.actualizarEmpresa(empresa);
        }
        request.setAttribute("empresas", empresaDao.obtenerTodasLasEmpresa());
        RequestDispatcher view = request.getRequestDispatcher(LIST_EMPRESA);            
        view.forward(request, response);
        
    }
}
