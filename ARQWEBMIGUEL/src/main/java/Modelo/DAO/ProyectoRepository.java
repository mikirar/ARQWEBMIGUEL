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

/**
 *
 * @author miki
 */
public class ProyectoRepository {
    
    private Connection connection;
    
    public ProyectoRepository() throws SQLException {
        //log de que cogemos conexión
        connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }

    public void crearProyecto(Proyecto proyecto) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into proyectos(id_proyecto,nombre,id_empresa) values (?, ?, ? )");
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from proyectos where id_proyecto=?");
            preparedStatement.setInt(1, idProyecto);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                proyecto.setProyectoid(rs.getInt("id_proyecto"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setEmpresaid(rs.getInt("id_empresa"));       
            }
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención del proyecto");
        }
        return proyecto;
    }

    public void actualizarProyecto(Proyecto proyecto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update proyectos set id_proyecto=?, nombre=?, id_empresa=?" + "where userid=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, proyecto.getProyectoid());
            preparedStatement.setString(2, proyecto.getNombre());            
            preparedStatement.setInt(3, proyecto.getEmpresaid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el proyecto");
        }
    }

    public void eliminarProyecto(int id) {
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("delete from proyectos where id_proyecto=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate(); 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien el proyecto");
        }
        
    }
    
}
