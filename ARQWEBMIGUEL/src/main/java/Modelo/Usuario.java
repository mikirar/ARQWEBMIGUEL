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
public class Usuario {
    
    int userid;
    String username;
    String password;
    String dni;
    String nombre;
    String apellidos;
    Date fecha_alta;
    Date fecha_baja;
    Enum tipo_usuario;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Date fecha_baja) {
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
        return "Usuario{" + "userid=" + userid + ", username=" + username + ", password=" + password + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_alta=" + fecha_alta + ", fecha_baja=" + fecha_baja + ", tipo_usuario=" + tipo_usuario + '}';
    }
    
    
}
