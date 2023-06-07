/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Marcaje;
import Modelo.TipoMarcaje;
import Util.ConexionBD;
import Util.Log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miki
 */

/**
 * Clase que representa un objeto de acceso a datos (DAO) para la tabla marcajes en la base de datos.
 * Se encarga de realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la tabla marcajes.
 * La clase utiliza una conexión a la base de datos a través de la clase ConexionBD.
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
    private static final String SELECT_ALL_MARCAJE_EMPRESA_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto JOIN empresa e ON p.id_empresa = e.id_empresa WHERE e.id_empresa = ?;";
    private static final String SELECT_ALL_MARCAJE_PROYECTO_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user JOIN usuarios_proyectos up ON u.id_user = up.id_user " +
    "JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE p.id_proyecto = ?;";
    private static final String DELETE_MARCAJE_BY_ID_SQL = "DELETE FROM marcajes WHERE id =?;";
    private static final String UPDATE_MARCAJE_BY_ID_SQL = "UPDATE marcajes set fecha = ?, tipo_marcaje = ?, id_usuario = ? WHERE id =?;";
    private static final String SELECT_ALL_MARCAJE_EMPRESA_SEMANA_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user JOIN usuarios_proyectos up ON u.id_user = up.id_user " +
    "JOIN proyectos p ON up.id_proyecto = p.id_proyecto JOIN empresa e ON p.id_empresa = e.id_empresa WHERE e.id_empresa = ? AND YEARWEEK(m.fecha) = YEARWEEK(?);";
    private static final String SELECT_ALL_MARCAJE_EMPRESA_MENSUAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user JOIN usuarios_proyectos up ON u.id_user = up.id_user " +
    "JOIN proyectos p ON up.id_proyecto = p.id_proyecto JOIN empresa e ON p.id_empresa = e.id_empresa WHERE e.id_empresa = ? AND MONTH(m.fecha) = MONTH(?);";
    private static final String SELECT_ALL_MARCAJE_EMPRESA_ANUAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto JOIN empresa e ON p.id_empresa = e.id_empresa " +
    "WHERE e.id_empresa = ? AND YEAR(m.fecha) = YEAR(?);";
    private static final String SELECT_ALL_MARCAJE_EMPRESA_PERIODO_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto JOIN empresa e ON p.id_empresa = e.id_empresa " +
    "WHERE e.id_empresa = ? AND m.fecha BETWEEN ? AND ?;";
    private static final String SELECT_ALL_MARCAJE_USUARIO_SEMANAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE u.id_user = ? AND YEARWEEK(m.fecha) = YEARWEEK(?);";
    private static final String SELECT_ALL_MARCAJE_USUARIO_MENSUAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE u.id_user = ? AND MONTH(m.fecha) = MONTH(?);";
    private static final String SELECT_ALL_MARCAJE_USUARIO_ANUAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE u.id_user = ? AND YEAR(m.fecha) = YEAR(?);";
    private static final String SELECT_ALL_MARCAJE_USUARIO_PERIODO_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE u.id_user = ? AND m.fecha BETWEEN ? AND ?;";
    private static final String SELECT_ALL_MARCAJE_PROYECTO_SEMANAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE p.id_proyecto = ? AND YEARWEEK(m.fecha) = YEARWEEK(?);";
    private static final String SELECT_ALL_MARCAJE_PROYECTO_MENSUAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE p.id_proyecto = ? AND MONTH(m.fecha) = MONTH(?);";
    private static final String SELECT_ALL_MARCAJE_PROYECTO_ANUAL_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE p.id_proyecto = ? AND YEAR(m.fecha) = YEAR(?);";
    private static final String SELECT_ALL_MARCAJE_PROYECTO_PERIODO_SQL = "SELECT m.* FROM marcajes m JOIN usuarios u ON m.id_usuario = u.id_user " +
    "JOIN usuarios_proyectos up ON u.id_user = up.id_user JOIN proyectos p ON up.id_proyecto = p.id_proyecto WHERE p.id_proyecto = ? " +
    "AND m.fecha >= ? " +
    "AND m.fecha <= ?;";
    
    
    public MarcajeDAO() {

    }
    
    /**
    * Crea un nuevo marcaje y lo guarda en la base de datos.
    * @param marcaje El objeto Marcaje a crear y guardar.
    */
    public void crearMarcaje(Marcaje marcaje) {
        System.out.println(INSERT_MARCAJE_SQL);
        Log.insertLog(INSERT_MARCAJE_SQL);
        Connection connection = null;
        
        try{
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MARCAJE_SQL);
            // Los parámetros comienzan en 1 
            preparedStatement.setInt(1, marcaje.getId());
            preparedStatement.setTimestamp(2, marcaje.getFecha());
            preparedStatement.setString(3, marcaje.getTipo_marcaje().name());
            preparedStatement.setInt(4, marcaje.getUsuarioid());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el marcaje");
            Log.insertLog("Se ha creado el marcaje\n");
        }
        catch (SQLException e) {
            System.out.println("No se ha guardado bien el marcaje: " + e);
            Log.insertLog(e + "No se ha guardado bien el marcaje\n");
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
    * Obtiene un marcaje de la base de datos por su ID.
    * @param idMarcaje El ID del marcaje a obtener.
    * @return El objeto Marcaje correspondiente al ID especificado, o null si no se encuentra.
    */
    public Marcaje obtenerMarcajePorId(int idMarcaje) {
        Log.insertLog(SELECT_MARCAJE_BY_ID_SQL);
        Marcaje marcaje = new Marcaje();
        Connection connection = null;
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MARCAJE_BY_ID_SQL);
            preparedStatement.setInt(1, idMarcaje);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                marcaje.setId(rs.getInt("id"));
                marcaje.setFecha(rs.getTimestamp("fecha")); 
                marcaje.setTipo_marcaje(TipoMarcaje.valueOf(rs.getString("tipo_marcaje")));
                marcaje.setUsuarioid(rs.getInt("id_usuario"));
            }
        } catch (SQLException e) {
            System.out.println("Ha fallado la obtención del marcaje");
            Log.insertLog(e + "Ha fallado la obtención del marcaje\n");
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
        Log.insertLog("Se ha obtenido correctamente el marcaje\n");
        return marcaje;
    }
    
    /**
    * Obtiene todos los marcajes de la base de datos.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos.
    */
    public List<Marcaje> obtenerTodasLosMarcajes() {
        Log.insertLog(SELECT_ALL_MARCAJE_SQL);
        List<Marcaje> marcajes = new ArrayList<>();
        Connection connection = null;
        
        try {
            connection = ConexionBD.getConnection();
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
                System.out.println("Error en la obtención de todos los marcajes: " + e);
                Log.insertLog(e + "Error en la obtención de todos los marcajes\n");
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
        Log.insertLog("Se han obtenido correctamente todos los marcajes\n");
        return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de usuario de la base de datos.
    * @param idUsuario El ID del usuario para el cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de usuario especificado.
    */
    
    public List<Marcaje> obtenerTodasLosMarcajesPorIdUsuario(int idUsuario) {
        System.out.println(SELECT_ALL_MARCAJE__USUARIO_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE__USUARIO_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
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
                System.out.println("Error en la obtención de todos los marcajes de los usuarios: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de usuario\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
    
    /**
    * Obtiene todos los marcajes asociados a un ID de usuario y a partir de una fecha de inicio de la semana, de la base de datos.
    * @param idUsuario El ID del usuario para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio de la semana a partir de la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de usuario y la fecha de inicio especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdUsuarioSemanal(int idUsuario, Timestamp fecha_inicio) {
        System.out.println(SELECT_ALL_MARCAJE_USUARIO_SEMANAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_USUARIO_SEMANAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_USUARIO_SEMANAL_SQL);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setTimestamp(2, fecha_inicio);
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
                System.out.println("Error en la obtención de todos los marcajes de los usuarios: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de usuario\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de usuario y a partir de una fecha de inicio mensual, de la base de datos.
    * @param idUsuario El ID del usuario para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio mensual a partir de la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de usuario y la fecha de inicio especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdUsuarioMensual(int idUsuario, Timestamp fecha_inicio) {
        System.out.println(SELECT_ALL_MARCAJE_USUARIO_MENSUAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_USUARIO_MENSUAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_USUARIO_MENSUAL_SQL);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setTimestamp(2, fecha_inicio);
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
                System.out.println("Error en la obtención de todos los marcajes de los usuarios: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de usuario\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de usuario y a partir de una fecha de inicio anual, de la base de datos.
    * @param idUsuario El ID del usuario para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio anual a partir de la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de usuario y la fecha de inicio especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdUsuarioAnual(int idUsuario, Timestamp fecha_inicio) {
        System.out.println(SELECT_ALL_MARCAJE_USUARIO_ANUAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_USUARIO_ANUAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_USUARIO_ANUAL_SQL);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setTimestamp(2, fecha_inicio);
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
                System.out.println("Error en la obtención de todos los marcajes de los usuarios: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de usuario\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de usuario dentro de un período de tiempo, de la base de datos.
    * @param idUsuario El ID del usuario para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio del período.
    * @param fecha_fin La fecha de fin del período.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de usuario y el período de tiempo especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdUsuarioPeriodo(int idUsuario, Timestamp fecha_inicio, Timestamp fecha_fin) {
        System.out.println(SELECT_ALL_MARCAJE_USUARIO_PERIODO_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_USUARIO_PERIODO_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_USUARIO_PERIODO_SQL);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setTimestamp(2, fecha_inicio);
            preparedStatement.setTimestamp(3, fecha_fin);
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
                System.out.println("Error en la obtención de todos los marcajes de los usuarios: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de usuario\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
    
    /**
    * Obtiene todos los marcajes asociados a un ID de empresa de la base de datos.
    * @param idEmpresa El ID de la empresa para la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de empresa.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdEmpresa(int idEmpresa) {
        System.out.println(SELECT_ALL_MARCAJE_EMPRESA_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_EMPRESA_SQL);
            preparedStatement.setInt(1, idEmpresa);
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
                System.out.println("Error en la obtención de todos los marcajes de la empresa: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de empresa\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de empresa y a partir de una fecha de inicio de la semana, de la base de datos.
    * @param idEmpresa El ID de la empresa para el cual se obtienen los marcajes.
    * @param fecha La fecha de inicio de la semana a partir de la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de empresa y la fecha de inicio especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdEmpresaSemanal(int idEmpresa, Timestamp fecha) {
        System.out.println(SELECT_ALL_MARCAJE_EMPRESA_SEMANA_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_EMPRESA_SEMANA_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_EMPRESA_SEMANA_SQL);
            preparedStatement.setInt(1, idEmpresa);
            preparedStatement.setTimestamp(2, fecha);
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
                System.out.println("Error en la obtención de todos los marcajes de la empresa: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de empresa\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de empresa y a partir de una fecha de inicio del mes, de la base de datos.
    * @param idEmpresa El ID de la empresa para el cual se obtienen los marcajes.
    * @param fecha La fecha de inicio de la semana a partir de la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de empresa y la fecha de inicio especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdEmpresaMensual(int idEmpresa, Timestamp fecha) {
        System.out.println(SELECT_ALL_MARCAJE_EMPRESA_MENSUAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_EMPRESA_MENSUAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_EMPRESA_MENSUAL_SQL);
            preparedStatement.setInt(1, idEmpresa);
            preparedStatement.setTimestamp(2, fecha);
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
                System.out.println("Error en la obtención de todos los marcajes de la empresa: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de empresa\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de empresa y a partir de una fecha de inicio del año, de la base de datos.
    * @param idEmpresa El ID de la empresa para el cual se obtienen los marcajes.
    * @param fecha La fecha de inicio de la semana a partir de la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de empresa y la fecha de inicio especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdEmpresaAnual(int idEmpresa, Timestamp fecha) {
        System.out.println(SELECT_ALL_MARCAJE_EMPRESA_ANUAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_EMPRESA_ANUAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_EMPRESA_ANUAL_SQL);
            preparedStatement.setInt(1, idEmpresa);
            preparedStatement.setTimestamp(2, fecha);
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
                System.out.println("Error en la obtención de todos los marcajes de la empresa: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de empresa\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
    
    /**
    * Obtiene todos los marcajes asociados a un ID de empresa dentro de un período de tiempo, de la base de datos.
    * @param idEmpresa El ID de la empresa para el cual se obtienen los marcajes.
    * @param fecha_inicial La fecha de inicio del período.
    * @param fecha_fin La fecha de fin del período.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de empresa y el período de tiempo especificados.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdEmpresaPeriodo(int idEmpresa, Timestamp fecha_inicial, Timestamp fecha_final) {
        System.out.println(SELECT_ALL_MARCAJE_EMPRESA_PERIODO_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_EMPRESA_PERIODO_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_EMPRESA_PERIODO_SQL);
            preparedStatement.setInt(1, idEmpresa);
            preparedStatement.setTimestamp(2, fecha_inicial);
            preparedStatement.setTimestamp(3, fecha_final);
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
                System.out.println("Error en la obtención de todos los marcajes de la empresa: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de empresa\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de proyecto de la base de datos.
    * @param idProyecto El ID del proyecto para el cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de proyecto.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdProyecto(int idProyecto) {
        System.out.println(SELECT_ALL_MARCAJE_PROYECTO_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_PROYECTO_SQL);
            preparedStatement.setInt(1, idProyecto);
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
                System.out.println("Error en la obtención de todos los marcajes del proyecto: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de proyecto\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de proyecto y una fecha de inicio de la semana en la base de datos.
    * @param idProyecto El ID del proyecto para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio de la semana para la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de proyecto y la fecha de inicio de la semana.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdProyectoSemanal(int idProyecto, Timestamp fecha_inicio) {
        System.out.println(SELECT_ALL_MARCAJE_PROYECTO_SEMANAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_PROYECTO_SEMANAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_PROYECTO_SEMANAL_SQL);
            preparedStatement.setInt(1, idProyecto);
            preparedStatement.setTimestamp(2, fecha_inicio);
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
                System.out.println("Error en la obtención de todos los marcajes de los proyectos: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de proyecto\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de proyecto y una fecha de inicio del mes en la base de datos.
    * @param idProyecto El ID del proyecto para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio del mes para la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de proyecto y la fecha de inicio del mes.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdProyectoMensual(int idProyecto, Timestamp fecha_inicio) {
        System.out.println(SELECT_ALL_MARCAJE_PROYECTO_MENSUAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_PROYECTO_MENSUAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_PROYECTO_MENSUAL_SQL);
            preparedStatement.setInt(1, idProyecto);
            preparedStatement.setTimestamp(2, fecha_inicio);
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
                System.out.println("Error en la obtención de todos los marcajes de los proyectos: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de proyecto\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
    /**
    * Obtiene todos los marcajes asociados a un ID de proyecto y una fecha de inicio del año en la base de datos.
    * @param idProyecto El ID del proyecto para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio del año para la cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de proyecto y la fecha de inicio del año.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdProyectoAnual(int idProyecto, Timestamp fecha_inicio) {
        System.out.println(SELECT_ALL_MARCAJE_PROYECTO_ANUAL_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_PROYECTO_ANUAL_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_PROYECTO_ANUAL_SQL);
            preparedStatement.setInt(1, idProyecto);
            preparedStatement.setTimestamp(2, fecha_inicio);
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
                System.out.println("Error en la obtención de todos los marcajes de los proyectos: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de proyecto\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
    
    /**
    * Obtiene todos los marcajes asociados a un ID de proyecto y un período de fechas en la base de datos.
    * @param idProyecto El ID del proyecto para el cual se obtienen los marcajes.
    * @param fecha_inicio La fecha de inicio del período para el cual se obtienen los marcajes.
    * @param fecha_fin La fecha de fin del período para el cual se obtienen los marcajes.
    * @return Una lista de objetos Marcaje que contiene todos los marcajes obtenidos para el ID de proyecto y el período de fechas.
    */
    public List<Marcaje> obtenerTodasLosMarcajesPorIdProyectoPeriodo(int idProyecto, Timestamp fecha_inicio, Timestamp fecha_fin) {
        System.out.println(SELECT_ALL_MARCAJE_PROYECTO_PERIODO_SQL);
        Log.insertLog(SELECT_ALL_MARCAJE_PROYECTO_PERIODO_SQL);
        Connection connection = null;
        List<Marcaje> marcajes = new ArrayList<>();
        
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAJE_PROYECTO_PERIODO_SQL);
            preparedStatement.setInt(1, idProyecto);
            preparedStatement.setTimestamp(2, fecha_inicio);
            preparedStatement.setTimestamp(3, fecha_fin);
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
                System.out.println("Error en la obtención de todos los marcajes de los proyectos: " + e);
                Log.insertLog(e + "Ha fallado la obtención de todos los marcajes por id de proyecto\n");
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
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
    
    /**
    * Actualiza un marcaje en la base de datos.
    * @param marcaje El objeto Marcaje que se desea actualizar.
    */
    public void actualizarMarcaje(Marcaje marcaje) {
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MARCAJE_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setTimestamp(1, marcaje.getFecha());
            preparedStatement.setString(2, marcaje.getTipo_marcaje().name());
            preparedStatement.setInt(3, marcaje.getUsuarioid());
            preparedStatement.setInt(4, marcaje.getId());
            preparedStatement.executeUpdate();
            Log.insertLog("Marcaje actualizado correctamente\n");
            
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar el marcaje:" + e);
            Log.insertLog(e + "No se ha podido actualizar el marcaje\n");
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
    * Elimina un marcaje de la base de datos dado su ID.
    * @param id El ID del marcaje que se desea eliminar.
    */
    public void eliminarMarcaje(int id) {
        Log.insertLog(DELETE_MARCAJE_BY_ID_SQL);
        Connection connection = null;
        boolean marcajeEliminado = false;
        try {
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MARCAJE_BY_ID_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate(); 
            Log.insertLog("Marcaje eliminado correctamente\n");
        }
        catch (SQLException e) {
            System.out.println("No se ha eliminado bien el marcaje");
            Log.insertLog(e + "No se ha eliminado correctamente el marcaje\n");
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
}
