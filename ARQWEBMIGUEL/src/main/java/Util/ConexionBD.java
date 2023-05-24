/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author miki
 */
public class ConexionBD {
    
    private static String DRIVER="com.mysql.cj.jdbc.Driver";
    private static String USUARIO="root";
    private static String PASSWORD="root1234";
    private static String URL="jdbc:mysql://localhost:3306/rrhh";
    
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN EL DRIVER: " + e);
        }
    }
    
    public static Connection getConnection() throws SQLException{
        
        Connection con = null;
        try {
            con=DriverManager.getConnection(URL, USUARIO, PASSWORD);
            JOptionPane.showMessageDialog(null, "CONEXIÓN EXITOSA");
            //Aquí hacemos uso de la conexión
            //Guardar en log que hemos realizado correctamente la conexión

        } finally {
            
            try {
                //con.close();
                System.out.println("Todo bien en ConexionDB");
            }
        
            catch (Throwable e) {
                JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXIÓN: " + e);
            }
        }
        
        return con;
    }
    
    public static void closeConnection(Connection con) throws SQLException {
        
        try {
            con.close();
        }
        catch (Throwable e){
            //Guardar en el log que se cierra la conexión correctamente
            JOptionPane.showMessageDialog(null, "CONEXIÓN FINALIZADA");
        }
    }
}
