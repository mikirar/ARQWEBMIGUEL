/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.DAO.UsuarioProyectoDAO;
import Modelo.UsuarioProyecto;
import java.sql.SQLException;

/**
 *
 * @author miki
 */

/**
 * Clase que representa el servicio de un usuario en un proyecto.
 */
public class UsuarioProyectoService {
    
    private UsuarioProyectoDAO usuarioProyectoRepository;

    /**
     * Constructor de la clase UsuarioProyectoService.
     * @throws SQLException si ocurre un error al inicializar el repositorio de usuarioProyecto.
     */
    public UsuarioProyectoService() throws SQLException{
        usuarioProyectoRepository = new UsuarioProyectoDAO();
    }
    
    /**
     * Crea un nuevo usuario en un proyecto.
     * @param usuarioProyecto objeto UsuarioProyecto a crear.
     */
    public void crearUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        usuarioProyectoRepository.crearUsuarioProyecto(usuarioProyecto);
    }

    /**
     * Obtiene un usuario en un proyecto por su ID.
     * @param idUsuarioProyecto ID del usuario en el proyecto a obtener.
     * @return objeto UsuarioProyecto correspondiente al ID proporcionado.
     */
    public UsuarioProyecto obtenerUsuarioProyecto(int idUsuarioProyecto) {
        return usuarioProyectoRepository.obtenerUsuarioProyectoPorId(idUsuarioProyecto);
    }

    /**
     * Actualiza los datos de un usuario en un proyecto.
     * @param usuarioProyecto objeto UsuarioProyecto con los datos actualizados.
     */
    public void actualizarUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        usuarioProyectoRepository.actualizarUsuarioProyecto(usuarioProyecto);
    }

    /**
     * Elimina un usuario en un proyecto por su ID.
     * @param id ID del usuario en el proyecto a eliminar.
     */
    public void eliminarUsuarioProyecto(int id) {
        usuarioProyectoRepository.eliminarUsuarioProyecto(id);
    }
}
