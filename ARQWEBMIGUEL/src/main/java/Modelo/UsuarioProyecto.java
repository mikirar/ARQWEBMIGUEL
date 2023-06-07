/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;
/**
 *
 * @author miki
 */

/**
 * Clase que representa la relación entre un usuario y un proyecto.
 */
public class UsuarioProyecto {
    
    int id;
    int userid;
    int proyectoid;
    Timestamp fecha_alta;
    Timestamp fecha_baja;

    /**
     * Obtiene el ID de la relación usuario-proyecto.
     *
     * @return el ID de la relación
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la relación usuario-proyecto.
     *
     * @param id el ID de la relación
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param userid el ID del usuario
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * Obtiene el ID del proyecto.
     *
     * @return el ID del proyecto
     */
    public int getProyectoid() {
        return proyectoid;
    }

    /**
     * Establece el ID del proyecto.
     *
     * @param proyectoid el ID del proyecto
     */
    public void setProyectoid(int proyectoid) {
        this.proyectoid = proyectoid;
    }

    /**
     * Obtiene la fecha de alta de la relación usuario-proyecto.
     *
     * @return la fecha de alta
     */
    public Timestamp getFecha_alta() {
        return fecha_alta;
    }

    /**
     * Establece la fecha de alta de la relación usuario-proyecto.
     *
     * @param fecha_alta la fecha de alta
     */
    public void setFecha_alta(Timestamp fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    /**
     * Obtiene la fecha de baja de la relación usuario-proyecto.
     *
     * @return la fecha de baja
     */
    public Timestamp getFecha_baja() {
        return fecha_baja;
    }

    /**
     * Establece la fecha de baja de la relación usuario-proyecto.
     *
     * @param fecha_baja la fecha de baja
     */
    public void setFecha_baja(Timestamp fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    /**
     * Devuelve una representación en formato de cadena de la clase UsuarioProyecto.
     *
     * @return una cadena que representa la clase UsuarioProyecto
     */

    @Override
    public String toString() {
        return "UsuarioProyecto{" + "id=" + id + ", userid=" + userid + ", proyectoid=" + proyectoid + ", fecha_alta=" + fecha_alta + ", fecha_baja=" + fecha_baja + '}';
    }
    
    
}
