/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Proyecto;
import Util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miki
 */
public class ProyectoDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/rrhh?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root1234";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    //private Connection connection;
    
    private static final String INSERT_PROYECTO_SQL = "INSERT INTO proyectos " + "(id_proyecto, nombre, id_empresa) VALUES" + "(?, ?, ?);";
    private static final String SELECT_PROYECTO_BY_ID_SQL = "SELECT * FROM proyectos WHERE id_proyecto =?;";
    private static final String SELECT_ALL_PROYECTO_SQL = "SELECT * FROM proyectos;";
    private static final String DELETE_PROYECTO_BY_ID_SQL = "DELETE FROM proyectos WHERE id_proyecto =?;";
    private static final String UPDATE_PROYECTO_BY_ID_SQL = "UPDATE proyectos set nombre = ?, id_empresa = ? WHERE id_proyecto =?;";
    
    
    public ProyectoDAO() throws SQLException {
        //log de que cogemos conexión
        //connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }

    public void crearProyecto(Proyecto proyecto) {
        System.out.println(INSERT_PROYECTO_SQL);
        try{
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROYECTO_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, proyecto.getProyectoid());
            preparedStatement.setString(2, proyecto.getNombre());            
            preparedStatement.setInt(3, proyecto.getEmpresaid());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el proyecto");
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha guardado bien el proyecto: " + e);
        }
    }

    public Proyecto obtenerProyectoPorId(int idProyecto) {
        Proyecto proyecto = new Proyecto();
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROYECTO_BY_ID_SQL);
            preparedStatement.setInt(1, idProyecto);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                proyecto.setProyectoid(rs.getInt("id_proyecto"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setEmpresaid(rs.getInt("id_empresa"));       
            }
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención del proyecto: " + e);
        }
        return proyecto;
    }
    
    public List<Proyecto> obtenerTodosLosProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROYECTO_SQL);
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
                System.out.println("Error en la obtención de todas las empresas: " + e);
            }
            return proyectos;
    }

    public boolean actualizarProyecto(Proyecto proyecto) {
        boolean proyectoActualizado = false;
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROYECTO_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setString(1, proyecto.getNombre());            
            preparedStatement.setInt(2, proyecto.getEmpresaid());
            preparedStatement.setInt(3, proyecto.getProyectoid());
            proyectoActualizado = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el proyecto: " + e);
        }
        
        return proyectoActualizado;
    }

    public boolean eliminarProyecto(int id) {
        boolean proyectoEliminado = false;
        
        try {
            Connection connection = ConexionBD.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROYECTO_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            proyectoEliminado = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien el proyecto: " + e);
        }
        
        return proyectoEliminado;
        
    }
    
}
