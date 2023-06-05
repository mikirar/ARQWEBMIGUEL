/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.EmpresaDAO;
import Modelo.DAO.MarcajeDAO;
import Modelo.Empresa;
import Modelo.Marcaje;
import Modelo.TipoMarcaje;
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


public class MarcadorControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/marcaje-form.jsp";
    private static String LIST_MARCAJE = "/marcaje-list.jsp";
    private MarcajeDAO marcajeDao;
    
    public MarcadorControlador() throws SQLException {
        super();
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
        
        String forward = "";
        Log.insertLog("Entramos en el doGet\n");
        String action = request.getParameter("action");
        Log.insertLog("Recogemos el parametro action con valor " + action+ "\n");
        if (action.equalsIgnoreCase("delete")) {
            Log.insertLog("Parametro valor DELETE");
            int marcajeId = Integer.parseInt(request.getParameter("id"));
            marcajeDao.eliminarMarcaje(marcajeId);
            forward = LIST_MARCAJE;
            request.setAttribute("marcajes", marcajeDao.obtenerTodasLosMarcajes());
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int marcajeId = Integer.parseInt(request.getParameter("id"));
            Marcaje marcaje = marcajeDao.obtenerMarcajePorId(marcajeId);
            request.setAttribute("marcaje", marcaje);
        } else if (action.equalsIgnoreCase("listMarcador")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_MARCAJE;
            request.setAttribute("marcajes", marcajeDao.obtenerTodasLosMarcajes());
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
        Marcaje marcaje = new Marcaje();
        String fechaString = request.getParameter("fecha");
        Timestamp fecha = Common.parseStringToTimestamp(fechaString);
        marcaje.setFecha(fecha);
        
        marcaje.setTipo_marcaje(TipoMarcaje.valueOf(request.getParameter("tipo_marcaje")));
        
        marcaje.setUsuarioid(Integer.parseInt(request.getParameter("usuarioid")));

        String marcajeid = request.getParameter("id");
        if (marcajeid == null || marcajeid.isEmpty() || marcajeid.equalsIgnoreCase("")) {
            Log.insertLog("Vamos a añadir el marcaje\n");
            marcajeDao.crearMarcaje(marcaje);

            /*try {
                //Log.insertLog("Vamos a añadir el usuario\n");
                usuarioRepository.crearUsuario(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
        } else {
            marcaje.setId(Integer.parseInt(marcajeid));
            Log.insertLog("Vamos a actualizar el marcaje\n");
            marcajeDao.actualizarMarcaje(marcaje);
        }
        request.setAttribute("marcajes", marcajeDao.obtenerTodasLosMarcajes());
        RequestDispatcher view = request.getRequestDispatcher(LIST_MARCAJE);            
        view.forward(request, response);
        //return;
    }
}
