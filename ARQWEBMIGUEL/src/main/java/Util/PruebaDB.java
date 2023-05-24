/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Modelo.Empresa;
import Modelo.Proyecto;
import Modelo.Repository.EmpresaRepository;
import Modelo.Repository.ProyectoRepository;
import java.sql.SQLException;

/**
 *
 * @author miki
 */
public class PruebaDB {
    
    public static void main(String[] args) throws SQLException {
        //ConexionBD db = new ConexionBD();
        //db.getConnection();
        
        //Creamos la empresa
        EmpresaRepository empresaRepository = new EmpresaRepository();
        Empresa empresa = new Empresa();
        empresa.setEmpresaid(1);
        empresa.setNombre_empresa("pruebaEmpresa");
        empresaRepository.crearEmpresa(empresa);
        
        //Creamos el proyecto
        /*ProyectoRepository proyectoRepository = new ProyectoRepository();
        Proyecto proyecto = new Proyecto();
        proyecto.setProyectoid(1);
        proyecto.setNombre("pruebaProyecto");
        proyecto.setEmpresaid(1);
        proyectoRepository.crearProyecto(proyecto);*/
        
    }
}
