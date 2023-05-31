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
public class Usuario {
    
    int id_user;
    String username;
    String password;
    String dni;
    String nombre;
    String apellidos;
    Timestamp fecha_alta;
    Timestamp fecha_baja;
    Enum tipo_usuario;

    public int getUserid() {
        return id_user;
    }

    public void setUserid(int userid) {
        this.id_user = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public Enum getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(Enum tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "userid=" + id_user + ", username=" + username + ", password=" + password + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_alta=" + fecha_alta + ", fecha_baja=" + fecha_baja + ", tipo_usuario=" + tipo_usuario + '}';
    }
    
    
}
