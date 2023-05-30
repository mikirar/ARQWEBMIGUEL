/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miki
 */
@WebServlet(name = "WelcomeServlet", urlPatterns = { "/welcome" }, loadOnStartup = 1)
public class WelcomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí puedes realizar la lógica y obtener los datos necesarios de la base de datos o de donde sea necesario
        
        // Por ejemplo, puedes crear una lista ficticia de usuarios para mostrar en la página de bienvenida
        List<Usuario> users = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setUserid(1);
        usuario.setUsername("PRUEBA DE USUARIO");
        users.add(usuario);
        
        // Coloca los datos en el atributo de solicitud para que la página JSP pueda acceder a ellos
        request.setAttribute("listUser", users);
        
        // Redirige a la página de bienvenida (user-list.jsp)
        request.getRequestDispatcher("/user-list.jsp").forward(request, response);
    }
    
    // Si también necesitas manejar una solicitud POST, puedes agregar el método doPost() y colocar la lógica correspondiente
}
