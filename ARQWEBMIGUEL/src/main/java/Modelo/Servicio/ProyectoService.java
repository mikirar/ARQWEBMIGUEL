/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Proyecto;
import Modelo.Repository.ProyectoRepository;

/**
 *
 * @author miki
 */
public class ProyectoService {
    private ProyectoRepository proyectoRepository;
    
    public ProyectoService(){
        proyectoRepository = new ProyectoRepository(); //Inicializamos repo
    }
    
    public void crearProyecto(Proyecto proyecto) {
        //Lógica para crear nuevo proyecto
        // Puede incluir validaciones, encriptación de contraseñas, etc.
        proyectoRepository.crearProyecto(proyecto);
    }
    
    public Proyecto obtenerProyecto(int idProyecto) {
        //Logica
        return proyectoRepository.obtenerProyecto(idProyecto);
    }
    
    public void actualizarProyecto(Proyecto proyecto) {
        //Logica
        proyectoRepository.actualizarProyecto(proyecto);
    }
    
    public void eliminarProyecto(int id) {
        //Logica
        proyectoRepository.eliminarProyecto(id);
    }
}
