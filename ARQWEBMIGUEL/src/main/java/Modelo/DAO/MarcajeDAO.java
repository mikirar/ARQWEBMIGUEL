/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Marcaje;
import Modelo.TipoMarcaje;
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
public class MarcajeDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/rrhh?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root1234";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    //private Connection connection;
    
    private static final String INSERT_MARCAJE_SQL = "INSERT INTO marcajes " + "(id, fecha, tipo_marcaje, id_usuario) VALUES" + "(?, ?, ?, ?);";
    private static final String SELECT_MARCAJE_BY_ID_SQL = "SELECT * FROM marcajes WHERE id =?;";
    private static final String SELECT_ALL_MARCAJE_SQL = "SELECT * FROM marcajes;";
    private static final String SELECT_ALL_MARCAJE__USUARIO_SQL = "SELECT * FROM marcajes WHERE id_usuario = ?;";
    private static final String DELETE_MARCAJE_BY_ID_SQL = "DELETE FROM marcajes WHERE id =?;";
    private static final String UPDATE_MARCAJE_BY_ID_SQL = "UPDATE marcajes set fecha = ?, tipo_marcaje = ?, id_usuario = ? WHERE id =?;";
    
    public MarcajeDAO() {
        //log de que cogemos conexión
        //connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearMarcaje(Marcaje marcaje) {
        System.out.println(INSERT_MARCAJE_SQL);

        try{
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MARCAJE_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, marcaje.getId());
            preparedStatement.setTimestamp(2, marcaje.getFecha());
            preparedStatement.setString(3, marcaje.getTipo_marcaje().name());
            preparedStatement.setInt(4, marcaje.getUsuarioid());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el marcaje");
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha guardado bien el marcaje: " + e);
        }
    }
    
    public Marcaje obtenerMarcajePorId(int idMarcaje) {
        Marcaje marcaje = new Marcaje();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MARCAJE_BY_ID_SQL);
            preparedStatement.setInt(1, idMarcaje);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                marcaje.setId(rs.getInt("id"));
                marcaje.setFecha(rs.getTimestamp("fecha")); 
                //marcaje.setTipo_marcaje(tipoMarcaje.valueOf(rs.getString("tipo_marcaje")));
                marcaje.setTipo_marcaje(TipoMarcaje.valueOf(rs.getString("tipo_marcaje")));
                marcaje.setUsuarioid(rs.getInt("id_usuario"));
            }
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención de la empresa");
        }
        return marcaje;
    }
    
    public List<Marcaje> obtenerTodasLosMarcajes() {
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Marcaje marcaje = new Marcaje();
                marcaje.setId(rs.getInt("id"));
                marcaje.setFecha(rs.getTimestamp("fecha"));
                marcaje.setTipo_marcaje(TipoMarcaje.valueOf(rs.getString("tipo_marcaje")));
                marcaje.setUsuarioid(rs.getInt("id_usuario"));
                marcajes.add(marcaje);
                }
            } catch (SQLException e) {
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todos los marcajes: " + e);
            }
            return marcajes;
    }
    
    public List<Marcaje> obtenerTodasLosMarcajesPorIdUsuario(int idUsuario) {
        System.out.println(SELECT_ALL_MARCAJE__USUARIO_SQL);
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE__USUARIO_SQL);
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Marcaje marcaje = new Marcaje();
                marcaje.setId(rs.getInt("id"));
                marcaje.setFecha(rs.getTimestamp("fecha"));
                marcaje.setTipo_marcaje(TipoMarcaje.valueOf(rs.getString("tipo_marcaje")));
                marcaje.setUsuarioid(rs.getInt("id_usuario"));
                marcajes.add(marcaje);
                }
            } catch (SQLException e) {
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todos los marcajes de los usuarios: " + e);
            }
            System.out.println(marcajes);
            return marcajes;
    }
    
    
    public void actualizarMarcaje(Marcaje marcaje) {
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MARCAJE_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setTimestamp(1, marcaje.getFecha());
            preparedStatement.setString(2, marcaje.getTipo_marcaje().name());
            preparedStatement.setInt(3, marcaje.getUsuarioid());
            preparedStatement.setInt(4, marcaje.getId());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el marcaje:" + e);
        }
    }
    
    public void eliminarMarcaje(int id) {
        boolean marcajeEliminado = false;
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MARCAJE_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate(); 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien el marcaje");
        }
        
    }
}
