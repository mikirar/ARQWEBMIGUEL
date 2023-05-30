/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.UsuarioProyecto;
import Util.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miki
 */
public class UsuarioProyectoRepository {
    
    private Connection connection;
    
    public UsuarioProyectoRepository() throws SQLException {
        //log de que cogemos conexión
        connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into usuarios_proyectos(id, id_user, id_proyecto, fecha_alta, fecha_baja) values (?, ?, ?, ?, ?)");
// Parameters start with 1 
            preparedStatement.setInt(1, usuarioProyecto.getUserid());
            preparedStatement.setInt(2, usuarioProyecto.getUserid());
            preparedStatement.setInt(3, usuarioProyecto.getProyectoid());
            preparedStatement.setDate(4, (Date) usuarioProyecto.getFecha_alta());
            preparedStatement.setDate(5, (Date) usuarioProyecto.getFecha_baja());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el usuario_proyecto");
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha guardado bien el usuario_proyecto: " + e);
        }
    }
    
    public UsuarioProyecto obtenerUsuarioProyectoPorId(int idUsuarioProyecto) {
        UsuarioProyecto usuarioProyecto = new UsuarioProyecto();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from usuarios_proyectos where id=?");
            preparedStatement.setInt(1, idUsuarioProyecto);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                usuarioProyecto.setId(rs.getInt("id"));
                usuarioProyecto.setUserid(rs.getInt("id_user"));
                usuarioProyecto.setProyectoid(rs.getInt("id_proyecto"));
                usuarioProyecto.setFecha_alta(rs.getDate("fecha_alta"));
                usuarioProyecto.setFecha_baja(rs.getDate("fecha_baja"));
            }
            
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención del usuario_proyecto");
        }
        return usuarioProyecto;
    }
    
    public void actualizarUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update usuarios_proyectos set id=?, id_user=?, id_proyecto=?, fecha_alta=?, fecha_baja=?" + "where id=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, usuarioProyecto.getId());
            preparedStatement.setInt(2, usuarioProyecto.getUserid());
            preparedStatement.setInt(3, usuarioProyecto.getProyectoid());
            preparedStatement.setDate(4, (Date) usuarioProyecto.getFecha_alta());
            preparedStatement.setDate(5, (Date) usuarioProyecto.getFecha_baja());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el usuario_proyecto");
        }
    }
    
    public void eliminarUsuarioProyecto(int id) {
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("delete from usuarios_proyectos where id=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate(); 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien el usuario_proyecto");
        }
        
    }
}
