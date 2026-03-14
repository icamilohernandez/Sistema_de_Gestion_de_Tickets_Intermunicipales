/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ivanc
 */
import dao.VehiculoDAO;
import dao.VehiculoDAOImpl;
import java.util.List;
import model.Vehiculo;

public class VehiculoService {

    private VehiculoDAO vehiculoDAO;

    public VehiculoService() {
        this.vehiculoDAO = new VehiculoDAOImpl();
    }

}
