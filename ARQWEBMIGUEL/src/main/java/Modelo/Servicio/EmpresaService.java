/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Empresa;
import Modelo.DAO.EmpresaDAO;
import java.sql.SQLException;

/**
 *
 * @author miki
 */

/**
 * Clase que representa el servicio de una empresa.
 */
public class EmpresaService {
    
    private EmpresaDAO empresaRepository;
    
    /**
     * Constructor de la clase EmpresaService.
     * @throws SQLException si ocurre un error al inicializar el repositorio de empresa.
     */
    public EmpresaService() throws SQLException {
        empresaRepository = new EmpresaDAO();
    }
    
    /**
     * Crea una nueva empresa.
     * @param empresa objeto Empresa a crear.
     */
    public void crearEmpresa(Empresa empresa) {
        empresaRepository.crearEmpresa(empresa);
    }

    /**
     * Obtiene una empresa por su ID.
     * @param idEmpresa ID de la empresa a obtener.
     * @return objeto Empresa correspondiente al ID proporcionado.
     */
    public Empresa obtenerEmpresaPorId(int idEmpresa) {
        return empresaRepository.obtenerEmpresaPorId(idEmpresa);
    }

    /**
     * Actualiza los datos de una empresa.
     * @param empresa objeto Empresa con los datos actualizados.
     */
    public void actualizarEmpresa(Empresa empresa) {
        empresaRepository.actualizarEmpresa(empresa);
    }

    /**
     * Elimina una empresa por su ID.
     * @param id ID de la empresa a eliminar.
     */
    public void eliminarEmpresa(int id) {
        empresaRepository.eliminarEmpresa(id);
    }
}
