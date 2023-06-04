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
public class UsuarioProyecto {
    
    int id;
    int userid;
    int proyectoid;
    Timestamp fecha_alta;
    Timestamp fecha_baja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProyectoid() {
        return proyectoid;
    }

    public void setProyectoid(int proyectoid) {
        this.proyectoid = proyectoid;
    }

    public Timestamp getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Timestamp fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Timestamp getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Timestamp fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    @Override
    public String toString() {
        return "UsuarioProyecto{" + "id=" + id + ", userid=" + userid + ", proyectoid=" + proyectoid + ", fecha_alta=" + fecha_alta + ", fecha_baja=" + fecha_baja + '}';
    }
    
    
}
