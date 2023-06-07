/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author miki
 */

/**
 * Clase que representa un proyecto.
 */
public class Proyecto {
    
    int proyectoid;
    String nombre;
    int empresaid;

    /**
     * Obtiene el ID del proyecto.
     * @return el ID del proyecto.
     */
    public int getProyectoid() {
        return proyectoid;
    }

    /**
     * Establece el ID del proyecto.
     * @param proyectoid el ID del proyecto a establecer.
     */
    public void setProyectoid(int proyectoid) {
        this.proyectoid = proyectoid;
    }

    /**
     * Obtiene el nombre del proyecto.
     * @return el nombre del proyecto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proyecto.
     * @param nombre el nombre del proyecto a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID de la empresa asociada al proyecto.
     * @return el ID de la empresa.
     */
    public int getEmpresaid() {
        return empresaid;
    }

    /**
     * Establece el ID de la empresa asociada al proyecto.
     * @param empresaid el ID de la empresa a establecer.
     */
    public void setEmpresaid(int empresaid) {
        this.empresaid = empresaid;
    }

    /**
     * Devuelve una representación en cadena del proyecto.
     * @return una cadena que representa el proyecto.
     */
    @Override
    public String toString() {
        return "Proyecto{" + "proyectoid=" + proyectoid + ", nombre=" + nombre + ", empresaid=" + empresaid + '}';
    }
    
    
}
