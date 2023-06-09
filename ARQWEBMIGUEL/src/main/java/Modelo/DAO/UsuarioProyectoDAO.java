/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Empresa;
import Modelo.Proyecto;
import Modelo.UsuarioProyecto;
import Util.ConexionBD;
import Util.Log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miki
 */

/**
 * Clase que representa un objeto de acceso a datos (DAO) para la tabla usuarios_proyecto en la base de datos.
 * Se encarga de realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la tabla usuarios_proyecto.
 * La clase utiliza una conexión a la base de datos a través de la clase ConexionBD.
 */
public class UsuarioProyectoDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/rrhh?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root1234";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    //private Connection connection;
    
    private static final String INSERT_USUARIO_PROYECTO_SQL = "insert into usuarios_proyectos(id, id_user, id_proyecto, fecha_alta, fecha_baja) values (?, ?, ?, ?, ?);";
    private static final String SELECT_USUARIO_PROYECTO_BY_ID_SQL = "select * from usuarios_proyectos where id=?;";
    private static final String SELECT_ALL_USUARIO_PROYECTO_SQL = "SELECT * FROM usuarios_proyectos;";
    private static final String SELECT_ALL_USUARIOS_USUARIO_PROYECTO_SQL = "SELECT * FROM usuarios_proyectos WHERE id_user = ?;";
    private static final String DELETE_USUARIO_PROYECTO_BY_ID_SQL = "delete from usuarios_proyectos where id=?;";
    private static final String UPDATE_USUARIO_PROYECTO_BY_ID_SQL = "update usuarios_proyectos set id_user=?, id_proyecto=?, fecha_alta=?, fecha_baja=? where id=?";
    private static final String SELECT_PROYECTOS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL = "SELECT p.* FROM proyectos p JOIN usuarios_proyectos up ON p.id_proyecto = up.id_proyecto WHERE up.id_user = ?;";
    private static final String SELECT_EMPRESAS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL = "SELECT e.* FROM empresa e JOIN proyectos p ON e.id_empresa = p.id_empresa JOIN usuarios_proyectos up ON p.id_proyecto = up.id_proyecto WHERE up.id_user = ?;";
    
    public UsuarioProyectoDAO() throws SQLException {

    }
    
    /**
    * Crea un nuevo registro de UsuarioProyecto en la base de datos.
    * @param usuarioProyecto El objeto UsuarioProyecto a crear.
    */
    public void crearUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        System.out.println(INSERT_USUARIO_PROYECTO_SQL);
        Log.insertLog(INSERT_USUARIO_PROYECTO_SQL);
        Connection connection = null;
        try{
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_PROYECTO_SQL);
            // Parámetros comienzan con 1
            preparedStatement.setInt(1, usuarioProyecto.getId());
            preparedStatement.setInt(2, usuarioProyecto.getUserid());
            preparedStatement.setInt(3, usuarioProyecto.getProyectoid());
            preparedStatement.setTimestamp(4, usuarioProyecto.getFecha_alta());
            preparedStatement.setTimestamp(5, usuarioProyecto.getFecha_baja());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el usuario_proyecto");
            Log.insertLog("Se ha creado el usuario_proyecto correctamente\n");
        }
        catch (SQLException e) {
            System.out.println("No se ha guardado bien el usuario_proyecto: " + e);
            Log.insertLog(e + "No se ha guardado correctamente el usuario_proyecto\n");
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
    
    /**
    * Obtiene un UsuarioProyecto de la base de datos por su ID.
    * @param idUsuarioProyecto El ID del UsuarioProyecto a obtener.
    * @return El UsuarioProyecto encontrado o un UsuarioProyecto vacío si no se encuentra.
    */
    public UsuarioProyecto obtenerUsuarioProyectoPorId(int idUsuarioProyecto) {
        System.out.println(SELECT_USUARIO_PROYECTO_BY_ID_SQL);
        Log.insertLog(SELECT_USUARIO_PROYECTO_BY_ID_SQL);
        Connection connection = null;
        UsuarioProyecto usuarioProyecto = new UsuarioProyecto();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USUARIO_PROYECTO_BY_ID_SQL);
            preparedStatement.setInt(1, idUsuarioProyecto);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                usuarioProyecto.setId(rs.getInt("id"));
                usuarioProyecto.setUserid(rs.getInt("id_user"));
                usuarioProyecto.setProyectoid(rs.getInt("id_proyecto"));
                usuarioProyecto.setFecha_alta(rs.getTimestamp("fecha_alta"));
                usuarioProyecto.setFecha_baja(rs.getTimestamp("fecha_baja"));
            }
            
        } catch (SQLException e) {
            System.out.println("Ha fallado la obtención del usuario_proyecto: " + e);
            Log.insertLog(e + "no se ha obtenido correctamente el usuario_proyecto\n");
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
        Log.insertLog("Se ha obtenido correctamente el usuario_proyecto\n");
        return usuarioProyecto;
    }
    
    /**
    * Obtiene todos los registros de UsuarioProyecto de la base de datos.
    * @return Una lista de todos los UsuarioProyecto encontrados.
    */
    public List<UsuarioProyecto> obtenerTodosLosUsuarioProyecto() {
        System.out.println(SELECT_ALL_USUARIO_PROYECTO_SQL);
        Log.insertLog(SELECT_ALL_USUARIO_PROYECTO_SQL);
        Connection connection = null;
        List<UsuarioProyecto> usuariosProyectos = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USUARIO_PROYECTO_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                UsuarioProyecto usuarioProyecto = new UsuarioProyecto();
                usuarioProyecto.setId(rs.getInt("id"));
                usuarioProyecto.setUserid(rs.getInt("id_user"));
                usuarioProyecto.setProyectoid(rs.getInt("id_proyecto"));
                usuarioProyecto.setFecha_alta(rs.getTimestamp("fecha_alta"));
                usuarioProyecto.setFecha_baja(rs.getTimestamp("fecha_baja"));
                usuariosProyectos.add(usuarioProyecto);
                }
            } catch (SQLException e) {
                System.out.println("Error en la obtención de todos los usuarios_proyectos: " + e);
                Log.insertLog(e + "No se han obtenido correctamente todos los usuarios_proyectos\n");
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
        Log.insertLog("Se han obtenido correctamente todos los usuarios_proyectos\n");
        return usuariosProyectos;
    }
    
    /**
    * Obtiene todos los Proyecto asociados a los registros de UsuarioProyecto en la base de datos.
    * @return Una lista de todos los Proyecto asociados a los UsuarioProyecto encontrados.
    */
    public List<Proyecto> obtenerTodosLosProyectosUsuarioProyecto() {
        System.out.println(SELECT_PROYECTOS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
        Log.insertLog(SELECT_PROYECTOS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
        Connection connection = null;
        List<Proyecto> proyectos = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROYECTOS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setProyectoid(rs.getInt("id_proyecto"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setEmpresaid(rs.getInt("id_empresa"));
                proyectos.add(proyecto);
                }
            Log.insertLog("Se han obtenido correctamente todos los proyectos de los usuario_rpoyecto\n");
            } catch (SQLException e) {
                System.out.println("Error en la obtención de todos los proyectos de usuario_proyecto: " + e);
                Log.insertLog(e + "No se han obtenido correctamente todos los proyectos de usuario_rpoyecto\n");
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
            return proyectos;
    }
    
    /**
    * Obtiene todas las empresas asociadas a los registros de UsuarioProyecto en la base de datos.
    * @return Una lista de todas las empresas asociadas a los UsuarioProyecto encontrados.
    */
    public List<Empresa> obtenerTodasLasEmpresasUsuarioProyecto() {
        System.out.println(SELECT_EMPRESAS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
        Log.insertLog(SELECT_EMPRESAS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
        Connection connection = null;
        List<Empresa> empresas = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESAS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setEmpresaid(rs.getInt("id_empresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                empresas.add(empresa);
                }
            } catch (SQLException e) {
                System.out.println("Error en la obtención de todas las empresas de usuario_proyecto: " + e);
                Log.insertLog(e + "No se han obtenido correctamente todas las empresas de usuario_proyecto\n");
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
        Log.insertLog("Se han obtenido correctamente las empresas de usuario_proyecto\n");
        return empresas;
    }
    
    /**
    * Actualiza un registro de UsuarioProyecto en la base de datos.
    * @param usuarioProyecto El objeto UsuarioProyecto actualizado.
    * @return true si la actualización fue exitosa, false en caso contrario.
    */
    public boolean actualizarUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        System.out.println(UPDATE_USUARIO_PROYECTO_BY_ID_SQL);
        Log.insertLog(UPDATE_USUARIO_PROYECTO_BY_ID_SQL);
        Connection connection = null;
        boolean usuarioProyectoActualizado = false;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_PROYECTO_BY_ID_SQL);
            // Parámetros comienzan en 1
            preparedStatement.setInt(1, usuarioProyecto.getUserid());
            preparedStatement.setInt(2, usuarioProyecto.getProyectoid());
            preparedStatement.setTimestamp(3, usuarioProyecto.getFecha_alta());
            preparedStatement.setTimestamp(4, usuarioProyecto.getFecha_baja());
            preparedStatement.setInt(5, usuarioProyecto.getId());
            usuarioProyectoActualizado = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el usuario_proyecto: " + e);
            Log.insertLog("No se ha podido actualizar correctamente el usuario_proyecto\n");
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
        
        Log.insertLog("Se ha actualizado correctamente el usuario_proyecto\n");
        return usuarioProyectoActualizado;
    }
    
    public boolean eliminarUsuarioProyecto(int id) {
        System.out.println(DELETE_USUARIO_PROYECTO_BY_ID_SQL);
        Log.insertLog(DELETE_USUARIO_PROYECTO_BY_ID_SQL);
        Connection connection = null;
        boolean usuarioProyectoEliminado = false;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_PROYECTO_BY_ID_SQL);
            // Parámetros comienzan en 1
            preparedStatement.setInt(1, id);
            //preparedStatement.executeUpdate();
            usuarioProyectoEliminado = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            System.out.println("No se ha eliminado bien el usuario_proyecto: " + e);
            Log.insertLog(e + "No se ha eliminado correctamente el usuario_proyecto\n");
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
        Log.insertLog("Se ha eliminado correctamente el usuario_proyecto\n");
        return usuarioProyectoEliminado;
    }
    
}
