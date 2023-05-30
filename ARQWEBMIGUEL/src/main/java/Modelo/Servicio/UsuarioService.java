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
public class UsuarioService {
    
    private UsuarioDAO usuarioRepository;
    
    public UsuarioService() throws SQLException{
        usuarioRepository = new UsuarioDAO();
    }
    
    public void crearUsuario(Usuario usuario) throws SQLException {
        usuarioRepository.crearUsuario(usuario);
    }

    public Usuario obtenerUsuario(int idUsuario) {
        return usuarioRepository.obtenerUsuarioPorId(idUsuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.eliminarUsuario(id);
    }
}
