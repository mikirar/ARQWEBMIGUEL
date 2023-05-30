/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.UsuarioDAO;
import Modelo.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
        //Log.log.info("Entramos en el doGet\n");
        String action = request.getParameter("action");
        //Log.log.info("Recogemos el parametro action con valor " + action+ "\n");
        if (action.equalsIgnoreCase("delete")) {
            //Log.log.info("Parametro valor DELETE");
            int userId = Integer.parseInt(request.getParameter("userId"));
            usuarioRepository.eliminarUsuario(userId);
            forward = LIST_USER;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        } else if (action.equalsIgnoreCase("edit")) {
            //Log.log.info("Parametro valor EDIT");
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            Usuario user = usuarioRepository.obtenerUsuarioPorId(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")) {
            //Log.log.info("Parametro valor LIST\n");
            forward = LIST_USER;
            request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
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
        usuario.setUsername(request.getParameter("userName"));
        usuario.setPassword(request.getParameter("password"));                
        usuario.setDni(request.getParameter("dni"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidos(request.getParameter("apellidos"));
        //usuario.setFecha_alta(request.getParameter("fechaAlta"));
        //usuario.setFecha_baja(request.getParameter("fechaBaja"));
        //usuario.setTipo_usuario(request.getParameter("tipoUsuario"));

        String userid = request.getParameter("userid");
        if (userid == null || userid.isEmpty()) {
            try {
                //Log.log.info("Vamos a añadir el usuario\n");
                usuarioRepository.crearUsuario(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            usuario.setUserid(Integer.parseInt(userid));
            usuarioRepository.actualizarUsuario(usuario);
        }
        request.setAttribute("users", usuarioRepository.obtenerTodosLosUsuarios());
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);            
        view.forward(request, response);
        return;
    }
}
