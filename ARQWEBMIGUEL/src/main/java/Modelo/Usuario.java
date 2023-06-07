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
 * Clase que representa un usuario.
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

    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario
     */
    public int getUserid() {
        return id_user;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param userid el ID del usuario
     */
    public void setUserid(int userid) {
        this.id_user = userid;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username el nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password la contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el DNI del usuario.
     *
     * @return el DNI del usuario
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del usuario.
     *
     * @param dni el DNI del usuario
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre el nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return los apellidos del usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del usuario.
     *
     * @param apellidos los apellidos del usuario
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    /**
     * Obtiene la fecha de alta del usuario.
     *
     * @return la fecha de alta del usuario
     */
    public Timestamp getFecha_alta() {
        return fecha_alta;
    }

    /**
     * Establece la fecha de alta del usuario.
     *
     * @param fecha_alta la fecha de alta del usuario
     */
    public void setFecha_alta(Timestamp fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    /**
     * Obtiene la fecha de baja del usuario.
     *
     * @return la fecha de baja del usuario
     */
    public Timestamp getFecha_baja() {
        return fecha_baja;
    }

    /**
     * Establece la fecha de baja del usuario.
     *
     * @param fecha_baja la fecha de baja del usuario
     */
    public void setFecha_baja(Timestamp fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    /**
    * Obtiene el tipo de usuario.
    *
    * @return el tipo de usuario
    */
    public Enum getTipo_usuario() {
        return tipo_usuario;
    }

    /**
    * Establece el tipo de usuario.
    *
    * @param tipo_usuario el tipo de usuario
    */
    public void setTipo_usuario(Enum tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    /**
    * Devuelve una representación en formato de cadena de la clase Usuario.
    *
    * @return una cadena que representa la clase Usuario
    */
    @Override
    public String toString() {
        return "Usuario{" + "userid=" + id_user + ", username=" + username + ", password=" + password + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_alta=" + fecha_alta + ", fecha_baja=" + fecha_baja + ", tipo_usuario=" + tipo_usuario + '}';
    }
    
    
}
