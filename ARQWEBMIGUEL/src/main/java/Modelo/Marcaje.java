/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author miki
 */

/**
 * Clase que representa un marcaje.
 */
public class Marcaje {
    
    int id;
    Timestamp fecha;
    Enum tipo_marcaje;
    int usuarioid;

    /**
     * Obtiene el ID del marcaje.
     * @return el ID del marcaje.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del marcaje.
     * @param id el ID del marcaje a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha del marcaje.
     * @return la fecha del marcaje.
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del marcaje.
     * @param fecha la fecha del marcaje a establecer.
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el tipo de marcaje.
     * @return el tipo de marcaje.
     */
    public Enum getTipo_marcaje() {
        return tipo_marcaje;
    }

    /**
     * Establece el tipo de marcaje.
     * @param tipo_marcaje el tipo de marcaje a establecer.
     */
    public void setTipo_marcaje(Enum tipo_marcaje) {
        this.tipo_marcaje = tipo_marcaje;
    }

    /**
     * Obtiene el ID del usuario asociado al marcaje.
     * @return el ID del usuario.
     */
    public int getUsuarioid() {
        return usuarioid;
    }

    /**
     * Establece el ID del usuario asociado al marcaje.
     * @param usuarioid el ID del usuario a establecer.
     */
    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    /**
     * Devuelve una representación en cadena del marcaje.
     * @return una cadena que representa el marcaje.
     */
    @Override
    public String toString() {
        return "Marcaje{" + "id=" + id + ", fecha=" + fecha + ", tipo_marcaje=" + tipo_marcaje + ", usuarioid=" + usuarioid + '}';
    }
    
    
}
