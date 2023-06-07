/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Marcaje;
import Modelo.DAO.MarcajeDAO;
import java.sql.SQLException;

/**
 *
 * @author miki
 */

/**
 * Clase que representa el servicio de un marcaje.
 */
public class MarcajeService {
    
    private MarcajeDAO marcajeRepository;
    
    /**
     * Constructor de la clase MarcajeService.
     * @throws SQLException si ocurre un error al inicializar el repositorio de marcaje.
     */
    public MarcajeService() throws SQLException{
        marcajeRepository = new MarcajeDAO();
    }
    
    /**
     * Crea un nuevo marcaje.
     * @param marcaje objeto Marcaje a crear.
     */
    public void crearMarcaje(Marcaje marcaje) {
       marcajeRepository.crearMarcaje(marcaje);
    }

    /**
     * Obtiene un marcaje por su ID.
     * @param idMarcaje ID del marcaje a obtener.
     * @return objeto Marcaje correspondiente al ID proporcionado.
     */
    public Marcaje obtenerMarcaje(int idMarcaje) {
        return marcajeRepository.obtenerMarcajePorId(idMarcaje);
    }

    /**
     * Actualiza los datos de un marcaje.
     * @param marcaje objeto Marcaje con los datos actualizados.
     */
    public void actualizarMarcaje(Marcaje marcaje) {
        marcajeRepository.actualizarMarcaje(marcaje);
    }

    /**
     * Elimina un marcaje por su ID.
     * @param id ID del marcaje a eliminar.
     */
    public void eliminarMarcaje(int id) {
        marcajeRepository.eliminarMarcaje(id);
    }
}
