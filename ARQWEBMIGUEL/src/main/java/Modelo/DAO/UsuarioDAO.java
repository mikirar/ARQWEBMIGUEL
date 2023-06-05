/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.TipoUsuario;
import Modelo.Usuario;
import Util.ConexionBD;
import Util.Log;
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
    
    private static final String INSERT_USERS_SQL = "INSERT INTO usuarios " + "(id_user, username, password, dni, nombre, apellidos, fecha_alta, fecha_baja, tipo_usuario) VALUES"
                                                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM usuarios WHERE id_user =?;";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM usuarios;";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM usuarios WHERE id_user =?;";
    private static final String UPDATE_USER_BY_ID_SQL = "UPDATE usuarios set username = ?, password = ?, dni = ?, nombre = ?, apellidos = ?, fecha_alta = ?, fecha_baja = ?, tipo_usuario = ? WHERE id_user =?;";
    private static final String SELECT_USER_BY_USERNAME_PASSWORD_SQL = "SELECT * from usuarios where username = ? and password = ?;";
    
    public UsuarioDAO() {
        //log de que cogemos conexión
        //connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }

    
    public void crearUsuario(Usuario usuario) {
        System.out.println(INSERT_USERS_SQL);
        Connection connection = null;
        try{
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, usuario.getUserid());
            preparedStatement.setString(2, usuario.getUsername());
            preparedStatement.setString(3, usuario.getPassword());
            preparedStatement.setString(4, usuario.getDni());
            preparedStatement.setString(5, usuario.getNombre());
            preparedStatement.setString(6, usuario.getApellidos());
            preparedStatement.setTimestamp(7, usuario.getFecha_alta());
            preparedStatement.setTimestamp(8, usuario.getFecha_baja());
            preparedStatement.setString(9, usuario.getTipo_usuario().name());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el usuario");
            Log.insertLog("Se ha creado correctamente el usuario\n");
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha guardado bien el usuario: " + e);
            Log.insertLog(e + "No se ha guardado bien el usuario\n");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                Log.insertLog(e + "Error al cerrar la conexión\n");
            }
        }
    }
    
    public Usuario obtenerUsuarioPorUsernameYPassword(String username, String password) {
        System.out.println(SELECT_USER_BY_USERNAME_PASSWORD_SQL);
        Connection connection = null;
        Usuario usuario = new Usuario();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_PASSWORD_SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                usuario.setUserid(rs.getInt("id_user"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setDni(rs.getString("dni"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setFecha_alta(rs.getTimestamp("fecha_alta"));
                usuario.setFecha_baja(rs.getTimestamp("fecha_baja"));
                //usuario.setTipo_usuario(usuario.tipo_usuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setTipo_usuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
            }
        }
        catch (SQLException e) {
            System.out.println("No se ha encontrado el usuario: " + e);
            Log.insertLog(e + "No se ha encontrado el usuario\n");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                Log.insertLog(e + "Error al cerrar la conexión\n");
            }
        }
        //System.out.println(usuario.toString());
        //System.out.println(usuario.getTipo_usuario());
        Log.insertLog("Se ha encontrado correctamente el usuario\n");
        return usuario;
    }
    
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Connection connection = null;
        Usuario usuario = new Usuario();
        
        try {
            connection = ConexionBD.getConnection();
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
                usuario.setFecha_alta(rs.getTimestamp("fecha_alta"));
                usuario.setFecha_baja(rs.getTimestamp("fecha_baja"));
                //usuario.setTipo_usuario(usuario.tipo_usuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setTipo_usuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
            }
            
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención del usuario: " + e);
            Log.insertLog(e + "Ha fallado la obtención del usuario\n");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                Log.insertLog(e + "Error al cerrar la conexión\n");
            }
        }
        Log.insertLog("Se ha obtenido correctamente el usuario\n");
        return usuario;
    }
    
    public List<Usuario> obtenerTodosLosUsuarios() {
        Connection connection = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
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
                usuario.setFecha_alta(rs.getTimestamp("fecha_alta"));
                usuario.setFecha_baja(rs.getTimestamp("fecha_baja"));
                usuario.setTipo_usuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuarios.add(usuario);
                }
            } catch (SQLException e) {
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todos los usuarios: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los usuarios\n");
            } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                Log.insertLog(e + "Error al cerrar la conexión\n");
            }
        }
        Log.insertLog("Se han obtenido correctamente todos los usuarios\n");
        return usuarios;
    }
       
    
    public boolean actualizarUsuario(Usuario usuario) {
        Connection connection = null;
        boolean usuarioActualizado = false;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL);
            // Parameters start with 1 
            //preparedStatement.setInt(1, usuario.getUserid());
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setString(3, usuario.getDni());
            preparedStatement.setString(4, usuario.getNombre());
            preparedStatement.setString(5, usuario.getApellidos());
            preparedStatement.setTimestamp(6, usuario.getFecha_alta());
            preparedStatement.setTimestamp(7, usuario.getFecha_baja());
            preparedStatement.setString(8, usuario.getTipo_usuario().name());
            preparedStatement.setInt(9, usuario.getUserid());
            usuarioActualizado = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e);
            Log.insertLog(e + "No se ha actualizado correctamente el usuario\n");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                Log.insertLog(e + "Error al cerrar la conexión\n");
            }
        }
        
        Log.insertLog("Se ha actualizado correctamente el usuario\n");
        return usuarioActualizado;

    }
    
    public boolean eliminarUsuario(int id) {
        Connection connection = null;
        boolean usuarioEliminado = false;
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            usuarioEliminado = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("Error al eliminar el usuario: " + e);
            Log.insertLog(e + "No se ha eliminado correctamente el usuario\n");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                Log.insertLog(e + "Error al cerrar la conexión\n");
            }
        }
        Log.insertLog("Se ha eliminado correctamente el usuario\n");
        return usuarioEliminado;
    }
}
