/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Empresa;
import Modelo.Proyecto;
import Modelo.UsuarioProyecto;
import Util.ConexionBD;
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
public class UsuarioProyectoDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/rrhh?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root1234";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    //private Connection connection;
    
    private static final String INSERT_USUARIO_PROYECTO_SQL = "insert into usuarios_proyectos(id, id_user, id_proyecto, fecha_alta, fecha_baja) values (?, ?, ?, ?, ?);";
    private static final String SELECT_USUARIO_PROYECTO_BY_ID_SQL = "select * from usuarios_proyectos where id=?;";
    private static final String SELECT_ALL_USUARIO_PROYECTO_SQL = "SELECT * FROM usuario_proyecto;";
    private static final String SELECT_ALL_USUARIOS_USUARIO_PROYECTO_SQL = "SELECT * FROM usuario_proyecto WHERE id_user = ?;";
    private static final String DELETE_USUARIO_PROYECTO_BY_ID_SQL = "delete from usuarios_proyectos where id=?;";
    private static final String UPDATE_USUARIO_PROYECTO_BY_ID_SQL = "update usuarios_proyectos set id=?, id_user=?, id_proyecto=?, fecha_alta=?, fecha_baja=? where id=?";
    private static final String SELECT_PROYECTOS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL = "SELECT p.* FROM proyectos p JOIN usuarios_proyectos up ON p.id_proyecto = up.id_proyecto WHERE up.id_user = ?;";
    private static final String SELECT_EMPRESAS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL = "SELECT e.* FROM empresa e JOIN proyectos p ON e.id_empresa = p.id_empresa JOIN usuarios_proyectos up ON p.id_proyecto = up.id_proyecto WHERE up.id_user = ?;";
    
    public UsuarioProyectoDAO() throws SQLException {
        //log de que cogemos conexión
        //connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        try{
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_PROYECTO_SQL);
// Parameters start with 1 
            preparedStatement.setInt(1, usuarioProyecto.getId());
            preparedStatement.setInt(2, usuarioProyecto.getUserid());
            preparedStatement.setInt(3, usuarioProyecto.getProyectoid());
            preparedStatement.setTimestamp(4, usuarioProyecto.getFecha_alta());
            preparedStatement.setTimestamp(5, usuarioProyecto.getFecha_baja());
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
            Connection connection = ConexionBD.getConnection();
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
            //Meter en el log el error
            System.out.println("Ha fallado la obtención del usuario_proyecto: " + e);
        }
        return usuarioProyecto;
    }
    
    public List<UsuarioProyecto> obtenerTodosLosUsuarioProyecto() {
        List<UsuarioProyecto> usuariosProyectos = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todos los usuarios_proyectos: " + e);
            }
            return usuariosProyectos;
    }
    
    public List<Proyecto> obtenerTodosLosProyectosUsuarioProyecto() {
        List<Proyecto> proyectos = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROYECTOS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setProyectoid(rs.getInt("id_proyecto"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setEmpresaid(rs.getInt("id_empresa"));
                proyectos.add(proyecto);
                }
            } catch (SQLException e) {
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todos los proyectos de usuario_proyecto: " + e);
            }
            return proyectos;
    }
    
    public List<Empresa> obtenerTodasLasEmpresasUsuarioProyecto() {
        List<Empresa> empresas = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESAS_FROM_USUARIO_PROYECTO_BY_ID_USER_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setEmpresaid(rs.getInt("id_empresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                empresas.add(empresa);
                }
            } catch (SQLException e) {
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todas las empresas de usuario_proyecto: " + e);
            }
            return empresas;
    }
    
    public boolean actualizarUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
        boolean usuarioProyectoActualizado = false;
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_PROYECTO_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, usuarioProyecto.getId());
            preparedStatement.setInt(2, usuarioProyecto.getUserid());
            preparedStatement.setInt(3, usuarioProyecto.getProyectoid());
            preparedStatement.setTimestamp(4, usuarioProyecto.getFecha_alta());
            preparedStatement.setTimestamp(5, usuarioProyecto.getFecha_baja());
            //preparedStatement.executeUpdate();
            usuarioProyectoActualizado = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el usuario_proyecto: " + e);
        }
        
        return usuarioProyectoActualizado;
    }
    
    public boolean eliminarUsuarioProyecto(int id) {
        boolean usuarioProyectoEliminado = false;
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_PROYECTO_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            //preparedStatement.executeUpdate();
            usuarioProyectoEliminado = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien el usuario_proyecto: " + e);
        }
        
        return usuarioProyectoEliminado;
    }
    
    
    
    
    
}