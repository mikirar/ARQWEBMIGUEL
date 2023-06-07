/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Proyecto;
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
 * Clase que representa un objeto de acceso a datos (DAO) para la tabla proyectos en la base de datos.
 * Se encarga de realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la tabla proyectos.
 * La clase utiliza una conexión a la base de datos a través de la clase ConexionBD.
 */
public class ProyectoDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/rrhh?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root1234";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    
    private static final String INSERT_PROYECTO_SQL = "INSERT INTO proyectos " + "(id_proyecto, nombre, id_empresa) VALUES" + "(?, ?, ?);";
    private static final String SELECT_PROYECTO_BY_ID_SQL = "SELECT * FROM proyectos WHERE id_proyecto =?;";
    private static final String SELECT_ALL_PROYECTO_SQL = "SELECT * FROM proyectos;";
    private static final String DELETE_PROYECTO_BY_ID_SQL = "DELETE FROM proyectos WHERE id_proyecto =?;";
    private static final String UPDATE_PROYECTO_BY_ID_SQL = "UPDATE proyectos set nombre = ?, id_empresa = ? WHERE id_proyecto =?;";
    private static final String SELECT_ALL_PROYECTO_BY_USER_ID_SQL = "SELECT proyectos.* FROM proyectos JOIN usuarios_proyectos ON proyectos.id_proyecto = usuarios_proyectos.id_proyecto " +
    "WHERE usuarios_proyectos.id_user = ?;";
    
    
    public ProyectoDAO() {

    }

    /**
    * Crea un nuevo proyecto en la base de datos.
    *
    * @param proyecto El objeto Proyecto que se va a crear.
    */
    public void crearProyecto(Proyecto proyecto) {
        System.out.println(INSERT_PROYECTO_SQL);
        Log.insertLog(INSERT_PROYECTO_SQL);
        Connection connection = null;
        try{
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROYECTO_SQL);
            preparedStatement.setInt(1, proyecto.getProyectoid());
            preparedStatement.setString(2, proyecto.getNombre());            
            preparedStatement.setInt(3, proyecto.getEmpresaid());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el proyecto");
            Log.insertLog("Se ha creado el proyecto correctamente\n");
        }
        catch (SQLException e) {
            System.out.println("No se ha guardado bien el proyecto: " + e);
            Log.insertLog(e + "No se ha guardado bien el proyecto\n");
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
    * Obtiene un proyecto de la base de datos por su ID.
    *
    * @param idProyecto El ID del proyecto a buscar.
    * @return El objeto Proyecto correspondiente al ID especificado, o un objeto Proyecto vacío si no se encuentra.
    */
    public Proyecto obtenerProyectoPorId(int idProyecto) {
        Log.insertLog(SELECT_PROYECTO_BY_ID_SQL);
        Connection connection = null;
        Proyecto proyecto = new Proyecto();
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROYECTO_BY_ID_SQL);
            preparedStatement.setInt(1, idProyecto);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                proyecto.setProyectoid(rs.getInt("id_proyecto"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setEmpresaid(rs.getInt("id_empresa"));       
            }
        } catch (SQLException e) {
            System.out.println("Ha fallado la obtención del proyecto: " + e);
            Log.insertLog(e + "Ha fallado la obtención del poryecto\n");
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
        Log.insertLog("Se ha obtenido correctamente el proyecto\n");
        return proyecto;
    }
    
    /**
    * Obtiene todos los proyectos de la base de datos.
    *
    * @return Una lista de objetos Proyecto que representa todos los proyectos en la base de datos.
    */
    public List<Proyecto> obtenerTodosLosProyectos() {
        Log.insertLog(SELECT_ALL_PROYECTO_SQL);
        Connection connection = null;
        List<Proyecto> proyectos = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
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
                System.out.println("Error en la obtención de todos los proyectos: " + e);
                Log.insertLog(e + "Error en la obtención de todos los proyectos\n");
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
        Log.insertLog("Se han obtenido correctamente todos los proyectos\n");
        return proyectos;
    }

    /**
    * Actualiza un proyecto en la base de datos.
    *
    * @param proyecto El objeto Proyecto que se va a actualizar.
    * @return true si el proyecto se actualizó correctamente, false en caso contrario.
    */
    public boolean actualizarProyecto(Proyecto proyecto) {
        Log.insertLog(UPDATE_PROYECTO_BY_ID_SQL);
        Connection connection = null;
        boolean proyectoActualizado = false;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROYECTO_BY_ID_SQL);
            preparedStatement.setString(1, proyecto.getNombre());            
            preparedStatement.setInt(2, proyecto.getEmpresaid());
            preparedStatement.setInt(3, proyecto.getProyectoid());
            proyectoActualizado = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el proyecto: " + e);
            Log.insertLog(e + "No se ha podido actualizar correctamente el proyecto\n");
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
        Log.insertLog("Se ha actualizado correctamente el proyecto\n");
        return proyectoActualizado;
    }

    
    /**
    * Elimina un proyecto de la base de datos por su ID.
    *
    * @param id El ID del proyecto a eliminar.
    * @return true si el proyecto se eliminó correctamente, false en caso contrario.
    */
    public boolean eliminarProyecto(int id) {
        Log.insertLog(DELETE_PROYECTO_BY_ID_SQL);
        Connection connection = null;
        boolean proyectoEliminado = false;
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROYECTO_BY_ID_SQL);
            // Parámetros empiezan en 1 
            preparedStatement.setInt(1, id);
            proyectoEliminado = preparedStatement.executeUpdate() > 0; 
        }
        catch (SQLException e) {
            System.out.println("No se ha eliminado bien el proyecto: " + e);
            Log.insertLog(e + "No se ha eliminado correctamente el proyecto\n");
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
        Log.insertLog("Se ha eliminado correctamente el proyecto\n");
        return proyectoEliminado;
        
    }
    
    /**
    * Obtiene todos los proyectos de la base de datos asociados a un ID de usuario.
    *
    * @param idUser El ID del usuario asociado a los proyectos.
    * @return Una lista de objetos Proyecto que representa todos los proyectos asociados al ID de usuario especificado.
    */
    public List<Proyecto> obtenerTodosLosProyectosPorIdUsuario(int idUser) {
        Log.insertLog(SELECT_ALL_PROYECTO_BY_USER_ID_SQL);
        Connection connection = null;
        List<Proyecto> proyectos = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROYECTO_BY_USER_ID_SQL);
            preparedStatement.setInt(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setProyectoid(rs.getInt("id_proyecto"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setEmpresaid(rs.getInt("id_empresa"));
                proyectos.add(proyecto);
                }
            } catch (SQLException e) {
                System.out.println("Error en la obtención de todos los proyectos por id de usuario: " + e);
                Log.insertLog(e + "Error en la obtención de todos los proyectos por id de usuario\n");
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
        Log.insertLog("Se han obtenido correctamente todos los proyectos por id de usuario\n");
        return proyectos;
    }
    
}
