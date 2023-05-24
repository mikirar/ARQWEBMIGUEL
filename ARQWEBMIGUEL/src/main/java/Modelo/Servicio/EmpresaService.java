/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Empresa;
import Modelo.Repository.EmpresaRepository;
import java.sql.SQLException;

/**
 *
 * @author miki
 */
public class EmpresaService {
    
    private EmpresaRepository empresaRepository;
    
    public EmpresaService() throws SQLException {
        empresaRepository = new EmpresaRepository();
    }
    
    public void crearEmpresa(Empresa empresa) {
        empresaRepository.crearEmpresa(empresa);
    }

    public Empresa obtenerEmpresaPorId(int idEmpresa) {
        return empresaRepository.obtenerEmpresaPorId(idEmpresa);
    }

    public void actualizarEmpresa(Empresa empresa) {
        empresaRepository.actualizarEmpresa(empresa);
    }

    public void eliminarEmpresa(int id) {
        empresaRepository.eliminarEmpresa(id);
    }
}
