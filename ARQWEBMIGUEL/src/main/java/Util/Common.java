/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author miki
 */

/**
 * Clase de utilidad con métodos comunes.
 */
public class Common {
    
    /**
     * Convierte una cadena de texto en una fecha.
     * @param fechaStr cadena de texto que representa la fecha.
     * @return objeto Date correspondiente a la fecha proporcionada.
     */
    public static Date parseStringToDate(String fechaStr) {
        if (fechaStr == null || fechaStr.isEmpty()) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Convierte una cadena de texto en una marca de tiempo (Timestamp).
     * @param fechaStr cadena de texto que representa la marca de tiempo.
     * @return objeto Timestamp correspondiente a la marca de tiempo proporcionada.
     */
    public static Timestamp parseStringToTimestamp(String fechaStr) {
        if (fechaStr == null || fechaStr.isEmpty()) {
            return null;
        }
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String pattern = "yyyy-MM-dd'T'HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date fecha = dateFormat.parse(fechaStr);
            return new Timestamp(fecha.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
