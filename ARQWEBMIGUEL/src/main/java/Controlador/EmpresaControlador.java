/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.EmpresaDAO;
import Modelo.Empresa;
import Util.Common;
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


public class EmpresaControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/empresa-form.jsp";
    private static String LIST_EMPRESA = "/empresa-list.jsp";
    private EmpresaDAO empresaDao;
    
    public EmpresaControlador() throws SQLException {
        super();
        empresaDao = new EmpresaDAO();
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
        //Log.log.info("Entramos en el doGet\n");
        String action = request.getParameter("action");
        //Log.log.info("Recogemos el parametro action con valor " + action+ "\n");
        if (action.equalsIgnoreCase("delete")) {
            //Log.log.info("Parametro valor DELETE");
            int empresaId = Integer.parseInt(request.getParameter("empresaid"));
            empresaDao.eliminarEmpresa(empresaId);
            forward = LIST_EMPRESA;
            request.setAttribute("empresas", empresaDao.obtenerTodasLasempresa());
        } else if (action.equalsIgnoreCase("edit")) {
            //Log.log.info("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int empresaId = Integer.parseInt(request.getParameter("empresaid"));
            Empresa empresa = empresaDao.obtenerEmpresaPorId(empresaId);
            request.setAttribute("empresa", empresa);
        } else if (action.equalsIgnoreCase("listEmpresa")) {
            //Log.log.info("Parametro valor LIST\n");
            forward = LIST_EMPRESA;
            request.setAttribute("empresas", empresaDao.obtenerTodasLasempresa());
        } else {
            //Log.log.info("Parametro valor vacio vamos a insertar\n");
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
        //return;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Log.log.info("Entramos por el doPost\n");
        //processRequest(request, response);
        Empresa empresa = new Empresa();
        empresa.setNombre_empresa(request.getParameter("nombre_empresa"));

        String empresaid = request.getParameter("empresaid");
        if (empresaid == null || empresaid.isEmpty() || empresaid.equalsIgnoreCase("")) {
            empresaDao.crearEmpresa(empresa);

            /*try {
                //Log.log.info("Vamos a añadir el usuario\n");
                usuarioRepository.crearUsuario(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
        } else {
            empresa.setEmpresaid(Integer.parseInt(empresaid));
            empresaDao.actualizarEmpresa(empresa);
        }
        request.setAttribute("empresas", empresaDao.obtenerTodasLasempresa());
        RequestDispatcher view = request.getRequestDispatcher(LIST_EMPRESA);            
        view.forward(request, response);
        //return;
    }
}
