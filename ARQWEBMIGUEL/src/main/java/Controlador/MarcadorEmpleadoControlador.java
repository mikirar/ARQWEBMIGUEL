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
import Modelo.Usuario;
import Util.Common;
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
import java.util.List;
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


public class MarcadorEmpleadoControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/marcaje-empleado-form.jsp";
    private static String LIST_EMPLEADO = "/empleado-list.jsp";
    private MarcajeDAO marcajeDao;
    
    public MarcadorEmpleadoControlador() throws SQLException {
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
            forward = LIST_EMPLEADO;
            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("user");
            request.setAttribute("marcajes", marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(user.getUserid()));
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int marcajeId = Integer.parseInt(request.getParameter("id"));
            Marcaje marcaje = marcajeDao.obtenerMarcajePorId(marcajeId);
            request.setAttribute("marcaje", marcaje);
        } else if (action.equalsIgnoreCase("listEmpleado")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_EMPLEADO;
            //request.setAttribute("marcajes", marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(Integer.parseInt(request.getParameter("usuarioid"))));
            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("user");
            request.setAttribute("marcajes", marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(user.getUserid()));
        } else {
            Log.insertLog("Parametro valor vacio vamos a insertar\n");
            forward = INSERT_OR_EDIT;
            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("user");
            int idUsuario = user.getUserid();
            request.setAttribute("idUsuario", idUsuario);
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
            Log.insertLog("Vamos a añadir el marcaje del empleado\n");
            marcajeDao.crearMarcaje(marcaje);
            

            /*try {
                //Log.insertLog("Vamos a añadir el usuario\n");
                usuarioRepository.crearUsuario(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
        } else {
            marcaje.setId(Integer.parseInt(marcajeid));
            Log.insertLog("Vamos a actualizar el marcaje del empleado\n");
            marcajeDao.actualizarMarcaje(marcaje);
        }
        HttpSession session = request.getSession();
        List<Marcaje> marcajes = marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(Integer.parseInt(request.getParameter("usuarioid")));
        session.setAttribute("marcajes", marcajes);
        request.setAttribute("marcajes", marcajes);
        RequestDispatcher view = request.getRequestDispatcher(LIST_EMPLEADO);            
        view.forward(request, response);
        //return;
    }
}
