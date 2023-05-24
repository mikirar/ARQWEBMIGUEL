/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Repository;

import Modelo.Empresa;
import Util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miki
 */
public class EmpresaRepository {
    
    private Connection connection;
    
    public EmpresaRepository() throws SQLException {
        //log de que cogemos conexión
        connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearEmpresa(Empresa empresa) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into empresa(id_empresa,nombre_empresa) values (?, ?)");
// Parameters start with 1 
            preparedStatement.setInt(1, empresa.getEmpresaid());
            preparedStatement.setString(2, empresa.getNombre_empresa());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado la empresa");
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha guardado bien la empresa: " + e);
        }
    }

    public Empresa obtenerEmpresaPorId(int idEmpresa) {
        Empresa empresa = new Empresa();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from empresa where id_empresa=?");
            preparedStatement.setInt(1, idEmpresa);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                empresa.setEmpresaid(rs.getInt("idEmpresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));      
            }
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención de la empresa");
        }
        return empresa;
    }

    public void actualizarEmpresa(Empresa empresa) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update empresa set id_empresa=?, nombre_empresa=?" + "where id_empresa=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, empresa.getEmpresaid());
            preparedStatement.setString(2, empresa.getNombre_empresa());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar la empresa");
        }
    }

    public void eliminarEmpresa(int id) {
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("delete from empresa where id_empresa=?");
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate(); 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien la empresa");
        }
        
    }
}
