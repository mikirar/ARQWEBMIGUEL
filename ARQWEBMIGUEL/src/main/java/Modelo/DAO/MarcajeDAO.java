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
        //log de que cogemos conexión
        //connection = ConexionBD.getConnection();
        //log de que tenemos conexión

    }
    
    public void crearMarcaje(Marcaje marcaje) {
        System.out.println(INSERT_MARCAJE_SQL);
        Connection connection = null;
        
        try{
            connection = ConexionBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MARCAJE_SQL);
            // Parameters start with 1 
            preparedStatement.setInt(1, marcaje.getId());
            preparedStatement.setTimestamp(2, marcaje.getFecha());
            preparedStatement.setString(3, marcaje.getTipo_marcaje().name());
            preparedStatement.setInt(4, marcaje.getUsuarioid());
            preparedStatement.executeUpdate();
            System.out.println("Se ha creado el marcaje");
            Log.insertLog("Se ha creado el marcaje\n");
        }
        catch (SQLException e) {
            //grabar en el log
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
    
    public Marcaje obtenerMarcajePorId(int idMarcaje) {
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
                //marcaje.setTipo_marcaje(tipoMarcaje.valueOf(rs.getString("tipo_marcaje")));
                marcaje.setTipo_marcaje(TipoMarcaje.valueOf(rs.getString("tipo_marcaje")));
                marcaje.setUsuarioid(rs.getInt("id_usuario"));
            }
        } catch (SQLException e) {
            //Meter en el log el error
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
    
    public List<Marcaje> obtenerTodasLosMarcajes() {
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
    
    public List<Marcaje> obtenerTodasLosMarcajesPorIdUsuario(int idUsuario) {
        System.out.println(SELECT_ALL_MARCAJE__USUARIO_SQL);
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de usuario\n");
            return marcajes;
    }
    
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de empresa\n");
            System.out.println(marcajes);
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
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
                //Log.logdb.error("SQL Exception: " + e + "\n");  
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
            //System.out.println(marcajes);
            Log.insertLog("Se han obtenido todos los marcajes por id de proyecto\n");
            return marcajes;
    }
    
    
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
    
    public void eliminarMarcaje(int id) {
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
            //grabar en el log
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
