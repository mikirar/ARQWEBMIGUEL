/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Modelo.Empresa;
import Modelo.Proyecto;
import Modelo.DAO.EmpresaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.Usuario;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author miki
 */
public class PruebaDB {
    
    public static void main(String[] args) throws SQLException {
        //ConexionBD db = new ConexionBD();
        //db.getConnection();
        
        //Creamos la empresa
        /*EmpresaDAO empresaRepository = new EmpresaDAO();
        Empresa empresa = empresaRepository.obtenerEmpresaPorId(1);
        System.out.println(empresa);*/
        /*Empresa empresa = new Empresa();
        empresa.setEmpresaid(2);
        empresa.setNombre_empresa("pruebaEmpresa2");
        empresaRepository.crearEmpresa(empresa);*/
        
        //Creamos el proyecto
        /*ProyectoDAO proyectoRepository = new ProyectoDAO();
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoid(1);
        proyecto.setNombre("pruebaProyecto");
        proyecto.setEmpresaid(1);
        proyectoRepository.crearProyecto(proyecto);*/
        
        //UsuarioDAO usuarioRepository = new UsuarioDAO();
        //Usuario usuario = usuarioRepository.obtenerUsuarioPorId(1);
        //System.out.println(usuario);
        //List usuarios = usuarioRepository.obtenerTodosLosUsuarios();
        //System.out.println(usuarios.get(0));
        /*
        Usuario usuario = new Usuario();
        usuario.setApellidos("prueba");
        usuario.setDni("pruebadni");
        usuario.setFecha_alta(Timestamp.valueOf(s));
        */
    }
}
