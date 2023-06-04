/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.UsuarioDAO;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import Util.Common;
import static Util.Common.parseStringToTimestamp;
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
import java.util.Enumeration;
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


public class EmpleadoControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user-empleado-form.jsp";
    private static String LIST_EMPLEADO = "/empleado-list.jsp";
    private UsuarioDAO usuarioRepository;
    
    public EmpleadoControlador() throws SQLException {
        super();
        usuarioRepository = new UsuarioDAO();
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
            int userId = Integer.parseInt(request.getParameter("userid"));
            usuarioRepository.eliminarUsuario(userId);
            forward = LIST_EMPLEADO;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        } else if (action.equalsIgnoreCase("edit")) {
            //Log.log.info("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userid"));
            Usuario user = usuarioRepository.obtenerUsuarioPorId(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listEmpleado")) {
            //Log.log.info("Parametro valor LIST\n");
            System.out.println("ENTRA EN EL LISTEMPLEADO DE MIERDA");
            forward = LIST_EMPLEADO;
            HttpSession session = request.getSession();
            request.setAttribute("user", session.getAttribute("user"));
            request.setAttribute("marcajes", session.getAttribute("marcajes"));

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

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getParameter("username"));
        usuario.setPassword(request.getParameter("password"));                
        usuario.setDni(request.getParameter("dni"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidos(request.getParameter("apellidos"));
        String fechaAltaString = request.getParameter("fecha_alta");
        Timestamp fechaAlta = parseStringToTimestamp(fechaAltaString);
        //Date fechaAlta = Common.parseStringToDate(fechaAltaString);
        //Timestamp fechaAlta = Common.parseStringToTimestamp(fechaAltaString);
        usuario.setFecha_alta(fechaAlta);
        String fechaBajaString = request.getParameter("fecha_baja");
        //Date fechaBaja = Common.parseStringToDate(fechaBajaString);
        Timestamp fechaBaja = Common.parseStringToTimestamp(fechaBajaString);
        usuario.setFecha_baja(fechaBaja);
        usuario.setTipo_usuario(TipoUsuario.valueOf(request.getParameter("tipo_usuario")));
        //usuario.setTipo_usuario();
        usuario.setUserid(Integer.parseInt(request.getParameter("userid")));
        String userid = request.getParameter("userid");
        if (userid == null || userid.isEmpty() || userid.equalsIgnoreCase("")) {
            usuarioRepository.crearUsuario(usuario);
            
        } else {
            usuario.setUserid(Integer.parseInt(userid));
            usuarioRepository.actualizarUsuario(usuario);
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", usuario);
        request.setAttribute("user", usuario);
        RequestDispatcher view = request.getRequestDispatcher(LIST_EMPLEADO);            
        view.forward(request, response);
        //return;
    }
}
