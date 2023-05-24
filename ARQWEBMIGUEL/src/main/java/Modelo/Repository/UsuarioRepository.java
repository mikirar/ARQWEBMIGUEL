/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Repository;

import Modelo.TipoUsuario;
import Modelo.Usuario;
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
public class UsuarioRepository {
    
    private Connection connection;
    
    public UsuarioRepository() throws SQLException {
        //log de que cogemos conexión
        connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearUsuario(Usuario usuario) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into usuarios(id_user, username, password, dni, nombre, apellidos, fecha_alta, fecha_baja, tipo_usuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
// Parameters start with 1 
            preparedStatement.setInt(1, usuario.getUserid());
            preparedStatement.setString(2, usuario.getUsername());
            preparedStatement.setString(3, usuario.getPassword());
            preparedStatement.setString(4, usuario.getDni());
            preparedStatement.setString(5, usuario.getNombre());
            preparedStatement.setString(6, usuario.getApellidos());
            preparedStatement.setDate(7, (Date) usuario.getFecha_alta());
            preparedStatement.setDate(8, (Date) usuario.getFecha_baja());
            preparedStatement.setString(9, usuario.getTipo_usuario().name());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el usuario");
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha guardado bien el usuario: " + e);
        }
    }
    
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = new Usuario();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from usuarios where id_user=?");
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                usuario.setUserid(rs.getInt("id_user"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setDni(rs.getString("dni"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("appelidos"));
                usuario.setFecha_alta(rs.getDate("fecha_alta"));
                usuario.setFecha_baja(rs.getDate("fecha_baja"));
                //usuario.setTipo_usuario(usuario.tipo_usuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setTipo_usuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
            }
            
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención del usuario");
        }
        return usuario;
    }
    
    public void actualizarUsuario(Usuario usuario) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update usuarios set id_user=?, username=?, password=?, dni=?, nombre=?, apellidos=?, fecha_alta=?, fecha_baja=?, tipo_usuario=?" + "where id_user=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, usuario.getUserid());
            preparedStatement.setString(2, usuario.getUsername());
            preparedStatement.setString(3, usuario.getPassword());
            preparedStatement.setString(4, usuario.getDni());
            preparedStatement.setString(5, usuario.getNombre());
            preparedStatement.setString(6, usuario.getApellidos());
            preparedStatement.setDate(7, (Date) usuario.getFecha_alta());
            preparedStatement.setDate(8, (Date) usuario.getFecha_baja());
            preparedStatement.setString(9, usuario.getTipo_usuario().name());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el usuario");
        }
    }
    
    public void eliminarUsuario(int id) {
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("delete from usuarios where id_user=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate(); 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien el usuario");
        }
        
    }
}
