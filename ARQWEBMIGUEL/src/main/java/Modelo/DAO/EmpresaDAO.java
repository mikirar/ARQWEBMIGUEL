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

/**
 * Clase que representa un objeto de acceso a datos (DAO) para la tabla empresa en la base de datos.
 * Se encarga de realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la tabla empresa.
 * La clase utiliza una conexión a la base de datos a través de la clase ConexionBD.
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
    
    /**
     * Constructor por defecto de la clase EmpresaDAO.
     */
    
    public EmpresaDAO() {

    }
    
    /**
     * Crea una nueva empresa en la base de datos.
     * 
     * @param empresa La empresa a crear.
     */
    public void crearEmpresa(Empresa empresa) {
        System.out.println(INSERT_EMPRESA_SQL);
        Log.insertLog(INSERT_EMPRESA_SQL);
        Connection connection = null;
        try{
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPRESA_SQL);
            // Los parámetros comienzan en 1
            preparedStatement.setInt(1, empresa.getEmpresaid());
            preparedStatement.setString(2, empresa.getNombre_empresa());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado la empresa");
            Log.insertLog("Se ha creado la empresa\n");
        }
        catch (SQLException e) {
            System.out.println("No se ha guardado bien la empresa: " + e);
            Log.insertLog(e + "No se ha guardado la empresa\n");
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
     * Obtiene una empresa de la base de datos por su ID.
     * 
     * @param idEmpresa El ID de la empresa a obtener.
     * @return La empresa encontrada o una instancia vacía si no se encuentra ninguna empresa con el ID especificado.
     */
    public Empresa obtenerEmpresaPorId(int idEmpresa) {
        Log.insertLog(SELECT_EMPRESA_BY_ID_SQL);
        Empresa empresa = new Empresa();
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESA_BY_ID_SQL);
            preparedStatement.setInt(1, idEmpresa);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                empresa.setEmpresaid(rs.getInt("id_empresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));      
            }
        } catch (SQLException e) {
            System.out.println("Ha fallado la obtención de la empresa: " + e);
            Log.insertLog(e + "Ha fallado la obtención de la empresa\n");
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
        Log.insertLog("Se ha obtenido la empresa\n");
        return empresa;
    }
    
    /**
    * Obtiene todas las empresas de la base de datos.
    * 
    * @return Una lista de todas las empresas encontradas.
    */
    public List<Empresa> obtenerTodasLasEmpresa() {
        Log.insertLog(SELECT_ALL_EMPRESA_SQL);
        List<Empresa> empresas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPRESA_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setEmpresaid(rs.getInt("id_empresa"));
                empresa.setNombre_empresa(rs.getString("nombre_empresa"));
                empresas.add(empresa);
                }
            } catch (SQLException e) {
                System.out.println("Error en la obtención de todas las empresas: " + e);
                Log.insertLog(e + "Error en la obtención de todas las empresas\n");
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
            return empresas;
    }

    /**
    * Actualiza una empresa en la base de datos.
    * 
    * @param empresa La empresa actualizada.
    * @return true si la actualización fue exitosa, false en caso contrario.
    */
    public boolean actualizarEmpresa(Empresa empresa) {
        Log.insertLog(UPDATE_EMPRESA_BY_ID_SQL);
        boolean empresaActualizada = false;
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPRESA_BY_ID_SQL);
            // Los parámetros comienzan en 1
            preparedStatement.setString(1, empresa.getNombre_empresa());
            preparedStatement.setInt(2, empresa.getEmpresaid());
            empresaActualizada = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar la empresa: " + e);
            Log.insertLog(e + "No se ha podido actualizar la empresa\n");
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
        
        Log.insertLog("Se ha actualizado la empresa\n");
        return empresaActualizada;
    }

    /**
    * Elimina una empresa según su ID.
    *
    * @param id el ID de la empresa a eliminar
    * @return true si la empresa se eliminó correctamente, false de lo contrario
    */
    public boolean eliminarEmpresa(int id) {
        Log.insertLog(DELETE_EMPRESA_BY_ID_SQL);
        boolean empresaEliminada = false;
        Connection connection = null;
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPRESA_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            empresaEliminada = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            System.out.println("No se ha eliminado bien la empresa");
            Log.insertLog(e + "Error al eliminar la empresa\n");
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
        Log.insertLog("Se ha eliminado la empresa\n");
        return empresaEliminada;
        
    }
    
    /**
    * Obtiene todas las empresas asociadas a un ID de usuario.
    *
    * @param idUsuario el ID del usuario
    * @return una lista de objetos Empresa que pertenecen al usuario dado
    */
    public List<Empresa> obtenerEmpresasPorIdUsuario(int idUsuario) {
        Log.insertLog(SELECT_ALL_EMPRESA_BY_USER_ID_SQL);
        List<Empresa> empresas = new ArrayList<>();
        Connection connection = null;
        
        try {
            connection = ConexionBD.getConnection();
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
            return empresas;
    }
}
