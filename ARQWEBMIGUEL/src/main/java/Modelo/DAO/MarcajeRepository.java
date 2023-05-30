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

/**
 *
 * @author miki
 */
public class MarcajeRepository {
    
    private Connection connection;
    
    public MarcajeRepository() throws SQLException {
        //log de que cogemos conexión
        connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearMarcaje(Marcaje marcaje) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into marcajes(id, fecha, tipo_marcaje, id_usuario) values (?, ?, ?, ?)");
// Parameters start with 1 
            preparedStatement.setInt(1, marcaje.getId());
            preparedStatement.setDate(2, (Date) marcaje.getFecha());
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from marcajes where id=?");
            preparedStatement.setInt(1, idMarcaje);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                marcaje.setId(rs.getInt("id"));
                marcaje.setFecha(rs.getDate("fecha")); 
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
    
    public void actualizarMarcaje(Marcaje marcaje) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update marcajes set id=?, fecha=?, tipo_marcaje=?, id_usuario=?" + "where id=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, marcaje.getId());
            preparedStatement.setDate(2, (Date) marcaje.getFecha());
            preparedStatement.setString(3, marcaje.getTipo_marcaje().name());
            preparedStatement.setInt(4, marcaje.getUsuarioid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el marcaje");
        }
    }
    
    public void eliminarMarcaje(int id) {
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("delete from marcajes where id=?");
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
