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

/**
 *
 * @author miki
 */

/**
 * Controlador para manejar las operaciones relacionadas con los marcajes de los empleados.
 * 
 * Este servlet se encarga de recibir las solicitudes relacionadas con los marcajes de los empleados,
 * procesar los datos y redirigir a la vista correspondiente.
 * 
 * La clase MarcadorEmpleadoControlador extiende HttpServlet y maneja las solicitudes GET y POST.
 */
public class MarcadorEmpleadoControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/marcaje-empleado-form.jsp";
    private static String LIST_EMPLEADO = "/empleado-list.jsp";
    private MarcajeDAO marcajeDao;
    
    /**
     * Constructor de la clase MarcadorEmpleadoControlador.
     * 
     * @throws SQLException Si ocurre un error al crear el objeto MarcajeDAO.
     */
    public MarcadorEmpleadoControlador() throws SQLException {
        super();
        marcajeDao = new MarcajeDAO();
    }
    
    /**
     * Método para manejar las solicitudes GET.
     * Se encarga de procesar las solicitudes relacionadas con los marcajes de los empleados y redirigir a la vista correspondiente.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
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
        
    }
    
    /**
     * Método para manejar las solicitudes POST.
     * Se encarga de procesar las solicitudes relacionadas con los marcajes de los empleados enviadas mediante POST.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Log.insertLog("Entramos por el doPost\n");
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
   
    }
}
