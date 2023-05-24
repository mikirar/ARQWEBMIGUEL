/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author miki
 */
public enum TipoUsuario {
    
    A,
    U;
    
    public static TipoUsuario obtenerValor(String nombre) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.name().equals(nombre)) {
                return tipo;
            }
        }
        return null; // Valor no encontrado
    }
    
    
}
