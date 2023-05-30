/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.TipoUsuario;
import Modelo.Usuario;
import Util.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miki
 */
public class UsuarioDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/rrhh?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root1234";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    //private Connection connection;
    
    private static final String INSERT_USERS_SQL = "INSERT INTO usuarios " + "(username, password, dni, nombre, apellidos, fecha_alta, fecha_baja, tipo_usuario) VALUES"
                                                    + "(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM usuarios WHERE id_user =?;";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM usuarios;";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM usuarios WHERE id_user =?;";
    private static final String UPDATE_USER_BY_ID_SQL = "UPDATE usuarios set username = ?, password = ?, dni = ?, nombre = ?, apellidos = ?, fecha_alta = ?, fecha_baja = ?, tipo_usuario = ? WHERE id_user =?;";
    
    public UsuarioDAO() {
        //log de que cogemos conexión
        //connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }

    
    public void crearUsuario(Usuario usuario) throws SQLException{
        System.out.println(INSERT_USERS_SQL);
        try{
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            // Parameters start with 1 
            //preparedStatement.setInt(1, usuario.getUserid());
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setString(3, usuario.getDni());
            preparedStatement.setString(4, usuario.getNombre());
            preparedStatement.setString(5, usuario.getApellidos());
            preparedStatement.setDate(6, (Date) usuario.getFecha_alta());
            preparedStatement.setDate(7, (Date) usuario.getFecha_baja());
            preparedStatement.setString(8, usuario.getTipo_usuario().name());
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
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL);
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                usuario.setUserid(rs.getInt("id_user"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setDni(rs.getString("dni"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setFecha_alta(rs.getDate("fecha_alta"));
                usuario.setFecha_baja(rs.getDate("fecha_baja"));
                //usuario.setTipo_usuario(usuario.tipo_usuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setTipo_usuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
            }
            
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención del usuario: " + e);
        }
        return usuario;
    }
    
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUserid(rs.getInt("id_user"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setDni(rs.getString("dni"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setFecha_alta(rs.getDate("fecha_alta"));
                usuario.setFecha_baja(rs.getDate("fecha_baja"));
                usuario.setTipo_usuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuarios.add(usuario);
                }
            } catch (SQLException e) {
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todos los usuarios: " + e);
            }
            return usuarios;
    }
       
    
    public boolean actualizarUsuario(Usuario usuario) {
        boolean usuarioActualizado = false;
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL);
            // Parameters start with 1 
            //preparedStatement.setInt(1, usuario.getUserid());
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setString(3, usuario.getDni());
            preparedStatement.setString(4, usuario.getNombre());
            preparedStatement.setString(5, usuario.getApellidos());
            preparedStatement.setDate(6, (Date) usuario.getFecha_alta());
            preparedStatement.setDate(7, (Date) usuario.getFecha_baja());
            preparedStatement.setString(8, usuario.getTipo_usuario().name());
            
            usuarioActualizado = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e);
        }
        
        return usuarioActualizado;

    }
    
    public boolean eliminarUsuario(int id) {
        boolean usuarioEliminado = false;
        try {
            Connection connection = ConexionBD.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            usuarioEliminado = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("Error al eliminar el usuario: " + e);
        }
        return usuarioEliminado;
    }
}
