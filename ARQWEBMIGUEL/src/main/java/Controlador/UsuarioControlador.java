/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.UsuarioDAO;
import Modelo.TipoUsuario;
import Modelo.Usuario;
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


public class UsuarioControlador extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user-form.jsp";
    private static String LIST_USER = "/user-list.jsp";
    private UsuarioDAO usuarioRepository;
    
    public UsuarioControlador() throws SQLException {
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
        Log.insertLog("Entramos en el doGet\n");
        String action = request.getParameter("action");
        Log.insertLog("Recogemos el parametro action con valor " + action+ "\n");
        if (action.equalsIgnoreCase("delete")) {
            Log.insertLog("Parametro valor DELETE");
            int userId = Integer.parseInt(request.getParameter("userid"));
            usuarioRepository.eliminarUsuario(userId);
            forward = LIST_USER;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        } else if (action.equalsIgnoreCase("edit")) {
            Log.insertLog("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userid"));
            Usuario user = usuarioRepository.obtenerUsuarioPorId(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")) {
            Log.insertLog("Parametro valor LIST\n");
            forward = LIST_USER;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
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
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getParameter("username"));
        usuario.setPassword(request.getParameter("password"));                
        usuario.setDni(request.getParameter("dni"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidos(request.getParameter("apellidos"));
        String fechaAltaString = request.getParameter("fecha_alta");
        //Date fechaAlta = Common.parseStringToDate(fechaAltaString);
        Timestamp fechaAlta = Common.parseStringToTimestamp(fechaAltaString);
        usuario.setFecha_alta(fechaAlta);
        String fechaBajaString = request.getParameter("fecha_baja");
        //Date fechaBaja = Common.parseStringToDate(fechaBajaString);
        Timestamp fechaBaja = Common.parseStringToTimestamp(fechaBajaString);
        usuario.setFecha_baja(fechaBaja);
        usuario.setTipo_usuario(TipoUsuario.valueOf(request.getParameter("tipo_usuario")));
        
        String userid = request.getParameter("userid");
        if (userid == null || userid.isEmpty() || userid.equalsIgnoreCase("")) {
            Log.insertLog("Vamos a añadir el usuario\n");
            usuarioRepository.crearUsuario(usuario);

            /*try {
                //Log.insertLog("Vamos a añadir el usuario\n");
                usuarioRepository.crearUsuario(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
        } else {
            usuario.setUserid(Integer.parseInt(userid));
            Log.insertLog("Vamos a añadir el usuario\n");
            usuarioRepository.actualizarUsuario(usuario);
        }
        request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);            
        view.forward(request, response);
        //return;
    }
}
