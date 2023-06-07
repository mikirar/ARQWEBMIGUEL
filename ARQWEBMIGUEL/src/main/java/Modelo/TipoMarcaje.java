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
 * Enumeración que representa los tipos de marcaje.
 */
public enum TipoMarcaje {
    E, // Tipo de marcaje de entrada
    S; // Tipo de marcaje de salida
    
    /**
     * Obtiene el valor del tipo de marcaje a partir de su nombre.
     * @param nombre el nombre del tipo de marcaje.
     * @return el valor del tipo de marcaje correspondiente, o null si no se encuentra.
     */
    public static TipoMarcaje obtenerValor(String nombre) {
        for (TipoMarcaje tipo : TipoMarcaje.values()) {
            if (tipo.name().equals(nombre)) {
                return tipo;
            }
        }
        return null; // Valor no encontrado
    }
    
}
