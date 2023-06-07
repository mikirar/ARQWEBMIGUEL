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

/**
 * Clase de utilidad para establecer y cerrar la conexión con la base de datos.
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
    
    /**
     * Obtiene una conexión a la base de datos.
     * @return objeto Connection que representa la conexión establecida.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    public static Connection getConnection() throws SQLException{
        
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection=DriverManager.getConnection(URL, USUARIO, PASSWORD);
            Log.insertLog("Conexión exitosa\n");

        } catch (SQLException e){
            System.out.println("Error SQL: " + e);
            Log.insertLog(e + "Conexión incorrecta\n");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error clase no encontrada: " + e);
            Log.insertLog(e + "Conexión incorrecta\n");
        }
        
        return connection;
    }
    
    /**
     * Cierra una conexión con la base de datos.
     * @param con objeto Connection que representa la conexión a cerrar.
     * @throws SQLException si ocurre un error al cerrar la conexión.
     */
    public static void closeConnection(Connection con) throws SQLException {
        
        try {
            con.close();
        }
        catch (Exception e){
            Log.insertLog(e + "Conexión cerrada correctamente\n");
        }
    }
}
