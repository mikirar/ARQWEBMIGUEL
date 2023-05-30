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
            System.out.println("Error en el driver: " + e);
        }
    }
    
    public static Connection getConnection() throws SQLException{
        
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection=DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa");
            //Aquí hacemos uso de la conexión
            //Guardar en log que hemos realizado correctamente la conexión

        } catch (SQLException e){
            System.out.println("Error SQL: " + e);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error clase no encontrada: " + e);
        }
        
        return connection;
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
