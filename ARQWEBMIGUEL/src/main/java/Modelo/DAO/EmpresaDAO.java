/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Empresa;
import Util.ConexionBD;
import Util.Log;
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
public class EmpresaDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/rrhh?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root1234";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    //private Connection connection;
    
    private static final String INSERT_EMPRESA_SQL = "INSERT INTO empresa " + "(id_empresa, nombre_empresa) VALUES" + "(?, ?);";
    private static final String SELECT_EMPRESA_BY_ID_SQL = "SELECT * FROM empresa WHERE id_empresa =?;";
    private static final String SELECT_ALL_EMPRESA_SQL = "SELECT * FROM empresa;";
    private static final String DELETE_EMPRESA_BY_ID_SQL = "DELETE FROM empresa WHERE id_empresa =?;";
    private static final String UPDATE_EMPRESA_BY_ID_SQL = "UPDATE empresa set nombre_empresa = ? WHERE id_empresa =?;";
    private static final String SELECT_ALL_EMPRESA_BY_USER_ID_SQL = "SELECT DISTINCT empresa.* FROM empresa JOIN proyectos ON empresa.id_empresa = proyectos.id_empresa " +
    "JOIN usuarios_proyectos ON proyectos.id_proyecto = usuarios_proyectos.id_proyecto JOIN usuarios ON usuarios_proyectos.id_user = usuarios.id_user WHERE usuarios.id_user = ?;";
    
    public EmpresaDAO() {
        //log de que cogemos conexión
        //connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearEmpresa(Empresa empresa) {
        System.out.println(INSERT_EMPRESA_SQL);
        try{
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPRESA_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, empresa.getEmpresaid());
            preparedStatement.setString(2, empresa.getNombre_empresa());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado la empresa");
            Log.insertLog("Se ha creado la empresa\n");
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha guardado bien la empresa: " + e);
            Log.insertLog(e + "No se ha guardado la empresa\n");
        }
    }

    public Empresa obtenerEmpresaPorId(int idEmpresa) {
        Empresa empresa = new Empresa();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESA_BY_ID_SQL);
            preparedStatement.setInt(1, idEmpresa);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                empresa.setEmpresaid(rs.getInt("id_empresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));      
            }
        } catch (SQLException e) {
            //Meter en el log el error
            System.out.println("Ha fallado la obtención de la empresa: " + e);
            Log.insertLog(e + "Ha fallado la obtención de la empresa\n");
        }
        Log.insertLog("Se ha obtenido la empresa\n");
        return empresa;
    }
    
    public List<Empresa> obtenerTodasLasEmpresa() {
        List<Empresa> empresas = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPRESA_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setEmpresaid(rs.getInt("id_empresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                empresas.add(empresa);
                }
            } catch (SQLException e) {
                //Log.logdb.error("SQL Exception: " + e + "\n");  
                System.out.println("Error en la obtención de todas las empresas: " + e);
                Log.insertLog(e + "Error en la obtención de todas las empresas\n");
            }
            return empresas;
    }

    public boolean actualizarEmpresa(Empresa empresa) {
        boolean empresaActualizada = false;
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPRESA_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setString(1, empresa.getNombre_empresa());
            preparedStatement.setInt(2, empresa.getEmpresaid());
            empresaActualizada = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar la empresa: " + e);
            Log.insertLog(e + "No se ha podido actualizar la empresa\n");
        }
        
        Log.insertLog("Se ha actualizado la empresa\n");
        return empresaActualizada;
    }

    public boolean eliminarEmpresa(int id) {
        boolean empresaEliminada = false;
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPRESA_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            empresaEliminada = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            //grabar en el log
            System.out.println("No se ha eliminado bien la empresa");
            Log.insertLog(e + "Error al eliminar la empresa\n");
        }
        Log.insertLog("Se ha eliminado la empresa\n");
        return empresaEliminada;
        
    }
    
    public List<Empresa> obtenerEmpresasPorIdUsuario(int idUsuario) {
        List<Empresa> empresas = new ArrayList<>();
        
        try {
            Connection connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPRESA_BY_USER_ID_SQL);
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setEmpresaid(rs.getInt("id_empresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                empresas.add(empresa);
                }
            } catch (SQLException e) {
                System.out.println("Error en la obtención de todas las empresas del usuario: " + e);
                Log.insertLog(e + "Error en la obtención de todas las empresas del usuario\n");
            }
            return empresas;
    }
}
