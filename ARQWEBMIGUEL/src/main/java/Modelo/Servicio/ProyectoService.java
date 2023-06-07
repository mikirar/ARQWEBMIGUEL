/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Proyecto;
import Modelo.DAO.ProyectoDAO;
import java.sql.SQLException;

/**
 *
 * @author miki
 */

/**
 * Clase que representa el servicio de un proyecto.
 */
public class ProyectoService {
    private ProyectoDAO proyectoRepository;
    
    /**
     * Constructor de la clase ProyectoService.
     * @throws SQLException si ocurre un error al inicializar el repositorio de proyectos.
     */
    public ProyectoService() throws SQLException{
        proyectoRepository = new ProyectoDAO(); 
    }
    
    /**
     * Crea un nuevo proyecto.
     * @param proyecto objeto Proyecto a crear.
     */
    public void crearProyecto(Proyecto proyecto) {
        proyectoRepository.crearProyecto(proyecto);
    }
    
    /**
     * Obtiene un proyecto por su ID.
     * @param idProyecto ID del proyecto a obtener.
     * @return objeto Proyecto correspondiente al ID proporcionado.
     */
    public Proyecto obtenerProyecto(int idProyecto) {
        return proyectoRepository.obtenerProyectoPorId(idProyecto);
    }
    
    /**
     * Actualiza los datos de un proyecto.
     * @param proyecto objeto Proyecto con los datos actualizados.
     */
    public void actualizarProyecto(Proyecto proyecto) {
        proyectoRepository.actualizarProyecto(proyecto);
    }
    
    /**
     * Elimina un proyecto por su ID.
     * @param id ID del proyecto a eliminar.
     */
    public void eliminarProyecto(int id) {
        proyectoRepository.eliminarProyecto(id);
    }
}
