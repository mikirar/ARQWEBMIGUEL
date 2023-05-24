/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Marcaje;
import Modelo.Repository.MarcajeRepository;
import java.sql.SQLException;

/**
 *
 * @author miki
 */
public class MarcajeService {
    
    private MarcajeRepository marcajeRepository;
    
    public MarcajeService() throws SQLException{
        marcajeRepository = new MarcajeRepository();
    }
    
    public void crearMarcaje(Marcaje marcaje) {
       marcajeRepository.crearMarcaje(marcaje);
    }

    public Marcaje obtenerMarcaje(int idMarcaje) {
        return marcajeRepository.obtenerMarcajePorId(idMarcaje);
    }

    public void actualizarMarcaje(Marcaje marcaje) {
        marcajeRepository.actualizarMarcaje(marcaje);
    }

    public void eliminarMarcaje(int id) {
        marcajeRepository.eliminarMarcaje(id);
    }
}
