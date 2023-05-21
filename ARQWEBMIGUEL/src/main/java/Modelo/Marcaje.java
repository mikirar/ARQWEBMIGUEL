/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author miki
 */
public class Marcaje {
    
    int id;
    Date fecha;
    Enum tipo_marcaje;
    int usuarioid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Enum getTipo_marcaje() {
        return tipo_marcaje;
    }

    public void setTipo_marcaje(Enum tipo_marcaje) {
        this.tipo_marcaje = tipo_marcaje;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    @Override
    public String toString() {
        return "Marcaje{" + "id=" + id + ", fecha=" + fecha + ", tipo_marcaje=" + tipo_marcaje + ", usuarioid=" + usuarioid + '}';
    }
    
    
}
