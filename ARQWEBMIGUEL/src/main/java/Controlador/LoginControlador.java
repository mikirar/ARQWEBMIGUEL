/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO.MarcajeDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.Marcaje;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import Util.Common;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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


public class LoginControlador extends HttpServlet{
    String forward;
    private String userID;
    private String password;
    UsuarioDAO usuarioDao = new UsuarioDAO();
    MarcajeDAO marcajeDao = new MarcajeDAO();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Log.log.info("Entramos por el doGet");
        forward="index.jsp";
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get request parameters for userID and password
        String user = request.getParameter("usuario");
        String pwd = request.getParameter("password");
        Usuario usuarioBd = usuarioDao.obtenerUsuarioPorUsernameYPassword(user, pwd);
        userID = usuarioBd.getUsername();
        password = usuarioBd.getPassword();
        
        if(userID.equals(user) && password.equals(pwd) && usuarioBd.getTipo_usuario() == TipoUsuario.A){
            Cookie loginCookie = new Cookie("user",user);
            Cookie cookie = new Cookie("msg","4558858858585");
            //setting cookie to expiry in 30 mins
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);
            response.addCookie(cookie);
            response.sendRedirect("menu-principal-rrhh.jsp");
        } else if(userID.equals(user) && password.equals(pwd) && usuarioBd.getTipo_usuario() == TipoUsuario.U) {
            Cookie loginCookie = new Cookie("user",user);
            Cookie cookie = new Cookie("msg","4558858858585");
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);
            response.addCookie(cookie);
            //Metemos en la sesion tanto el usuario como su marcaje
            HttpSession session = request.getSession();
            List<Marcaje> marcajes = new ArrayList<>();
            marcajes = marcajeDao.obtenerTodasLosMarcajesPorIdUsuario(usuarioBd.getUserid());
            session.setAttribute("user", usuarioBd);
            session.setAttribute("marcajes", marcajes);
            request.setAttribute("user", usuarioBd);
            request.setAttribute("marcajes", marcajes);
            //RequestDispatcher dispatcher = request.getRequestDispatcher("EmpleadoControlador?action=listEmpleado");
            //dispatcher.forward(request, response);
            response.sendRedirect("EmpleadoControlador?action=listEmpleado");

        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("index.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Usuario o password son incorrectos.</font>");
            rd.include(request, response);
        }


    }
}
