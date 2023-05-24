/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Repository.UsuarioRepository;
import Modelo.Usuario;
import java.sql.SQLException;

/**
 *
 * @author miki
 */
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;
    
    public UsuarioService() throws SQLException{
        usuarioRepository = new UsuarioRepository();
    }
    
    public void crearUsuario(Usuario usuario) {
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
