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
 * Enumeración que representa los tipos de usuario.
 */
public enum TipoUsuario {
    
    A, // Tipo de usuario administrador
    U; // Tipo de usuario normal
    
    /**
     * Obtiene el valor del tipo de usuario a partir de su nombre.
     * @param nombre el nombre del tipo de usuario.
     * @return el valor del tipo de usuario correspondiente, o null si no se encuentra.
     */
    public static TipoUsuario obtenerValor(String nombre) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.name().equals(nombre)) {
                return tipo;
            }
        }
        return null; // Valor no encontrado
    }
    
    
}
