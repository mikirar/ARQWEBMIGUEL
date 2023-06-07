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

/**
 *
 * @author miki
 */

/**
 * Controlador para el manejo de operaciones relacionadas con los marcajes.
 * Permite agregar, editar, eliminar y listar marcajes.
 * 
 * El controlador se encarga de recibir las solicitudes HTTP relacionadas con los marcajes
 * y realizar las operaciones correspondientes en la capa de datos.
 * 
 * Las operaciones disponibles son:
 * - Agregar marcaje
 * - Editar marcaje
 * - Eliminar marcaje
 * - Listar marcajes
 * 
 * Estas operaciones son realizadas mediante solicitudes GET y POST.
 * 
 * La clase extiende HttpServlet para manejar las solicitudes HTTP.
 * 
 */
public class MarcadorControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/marcaje-form.jsp";
    private static String LIST_MARCAJE = "/marcaje-list.jsp";
    private MarcajeDAO marcajeDao;
    
    /**
     * Constructor de la clase.
     * Se encarga de inicializar el objeto MarcajeDAO para interactuar con la capa de datos.
     * 
     * @throws SQLException Si ocurre un error al establecer la conexión con la base de datos.
     */
    public MarcadorControlador() throws SQLException {
        super();
        marcajeDao = new MarcajeDAO();
    }
    
    /**
     * Método para manejar las solicitudes GET.
     * Se encarga de procesar las solicitudes relacionadas con los marcajes y redirigir a las vistas correspondientes.
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
        
    }
    
    /**
     * Método para manejar las solicitudes POST.
     * Se encarga de procesar las solicitudes relacionadas con los marcajes y redirigir a la vista de lista de marcajes.
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
            Log.insertLog("Vamos a añadir el marcaje\n");
            marcajeDao.crearMarcaje(marcaje);
            
        } else {
            marcaje.setId(Integer.parseInt(marcajeid));
            Log.insertLog("Vamos a actualizar el marcaje\n");
            marcajeDao.actualizarMarcaje(marcaje);
        }
        request.setAttribute("marcajes", marcajeDao.obtenerTodasLosMarcajes());
        RequestDispatcher view = request.getRequestDispatcher(LIST_MARCAJE);            
        view.forward(request, response);
        
    }
}
