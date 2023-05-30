/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.DAO.UsuarioDAO;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.servlet.RequestDispatcher;
import jakarta.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
import jakarta.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author miki
 */
@WebServlet("/home/Insert")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO userDAO;
    
    public void init() throws ServletException {
        userDAO = new UsuarioDAO();
        
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("POST request, doGet");
        //processRequest(request, response);
        String action = request.getServletPath();
        switch(action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            {
            try {
                insertUser(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                break;


            case "/delete":
            {
            try {
                deleteUser(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                break;


            case "/edit":
            {
            try {
                showEditForm(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                break;

            
            case "/update":
            {
            try {
                updateUser(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                break;


            default:
            {
            try {
                listUser(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                break;


            
            
        }
    }
    
    //Nuevo formulario para crear usuario
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getParameter("username"));
        usuario.setPassword(request.getParameter("password"));
        usuario.setDni(request.getParameter("dni"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidos(request.getParameter("apellidos"));
        
        String fechaAltaString = request.getParameter("fecha_alta");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaAlta = sdf.parse(fechaAltaString);
        
        usuario.setFecha_alta(fechaAlta);
        String fechaBajaString = request.getParameter("fecha_baja");
        Date fechaBaja = sdf.parse(fechaBajaString);
        usuario.setFecha_baja(fechaBaja);
        
        usuario.setTipo_usuario(TipoUsuario.valueOf(request.getParameter("tipo_usuario")));
        System.out.println("ENTRA AL INSERT");
        userDAO.crearUsuario(usuario);
        //Esto es una action, nos saltará en el default en el switch de más arriba
        response.sendRedirect("/user-list.jsp");
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id_user"));
        try {
            userDAO.eliminarUsuario(id);
        } catch (Exception e){
            System.out.println("Error al eliminar usuario: " + e);
        }
        //De nuevo, es un action, para que en el switch salte al default
        response.sendRedirect("list");
        
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id_user"));
        Usuario usuario;
        
        try {
            usuario = userDAO.obtenerUsuarioPorId(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            request.setAttribute("user", usuario);
            dispatcher.forward(request, response);
        } catch (Exception e){
            System.out.println("Error al editar usuario: " + e);
        }
        
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id_user"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        
        String fechaAltaString = request.getParameter("fecha_alta");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaAlta = sdf.parse(fechaAltaString);
        
        String fechaBajaString = request.getParameter("fecha_baja");
        Date fechaBaja = sdf.parse(fechaBajaString);
        
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(request.getParameter("tipo_usuario"));
        
        Usuario usuario = new Usuario();
        usuario.setUserid(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setFecha_alta(fechaAlta);
        usuario.setFecha_baja(fechaBaja);
        usuario.setTipo_usuario(tipoUsuario);
        
        userDAO.actualizarUsuario(usuario);
        response.sendRedirect("user-list.jsp");
    }
    
    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        
        try {
            List<Usuario> listUser = userDAO.obtenerTodosLosUsuarios();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error al mostrar la lista de usuarios: " + e);
        }
        
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("POST request, doPost");
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
