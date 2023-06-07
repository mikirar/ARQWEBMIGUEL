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
 * Clase que representa una empresa.
 */
public class Empresa {
    
    private int empresaid;
    private String nombre_empresa;

     /**
     * Obtiene el ID de la empresa.
     * @return el ID de la empresa.
     */
    public int getEmpresaid() {
        return empresaid;
    }

    /**
     * Establece el ID de la empresa.
     * @param empresaid el ID de la empresa a establecer.
     */
    public void setEmpresaid(int empresaid) {
        this.empresaid = empresaid;
    }
    
    /**
     * Obtiene el nombre de la empresa.
     * @return el nombre de la empresa.
     */
    public String getNombre_empresa() {
        return nombre_empresa;
    }

    /**
     * Establece el nombre de la empresa.
     * @param nombre_empresa el nombre de la empresa a establecer.
     */
    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    /**
     * Devuelve una representación en cadena de la empresa.
     * @return una cadena que representa la empresa.
     */
    @Override
    public String toString() {
        return "Empresa{" + "empresaid=" + empresaid + ", nombre_empresa=" + nombre_empresa + '}';
    }
    
}
