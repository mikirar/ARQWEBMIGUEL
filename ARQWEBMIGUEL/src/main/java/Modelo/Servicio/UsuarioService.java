/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.DAO.UsuarioDAO;
import Modelo.Usuario;
import java.sql.SQLException;

/**
 *
 * @author miki
 */

/**
 * Clase que representa el servicio de un usuario.
 */
public class UsuarioService {
    
    private UsuarioDAO usuarioRepository;
    
    /**
     * Constructor de la clase UsuarioService.
     * @throws SQLException si ocurre un error al inicializar el repositorio de usuarios.
     */
    public UsuarioService() throws SQLException{
        usuarioRepository = new UsuarioDAO();
    }
    
    /**
     * Crea un nuevo usuario.
     * @param usuario objeto Usuario a crear.
     * @throws SQLException si ocurre un error al crear el usuario.
     */
    public void crearUsuario(Usuario usuario) throws SQLException {
        usuarioRepository.crearUsuario(usuario);
    }

    /**
     * Obtiene un usuario por su ID.
     * @param idUsuario ID del usuario a obtener.
     * @return objeto Usuario correspondiente al ID proporcionado.
     */
    public Usuario obtenerUsuario(int idUsuario) {
        return usuarioRepository.obtenerUsuarioPorId(idUsuario);
    }

    /**
     * Actualiza los datos de un usuario.
     * @param usuario objeto Usuario con los datos actualizados.
     */
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(usuario);
    }

    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario a eliminar.
     */
    public void eliminarUsuario(int id) {
        usuarioRepository.eliminarUsuario(id);
    }
}
