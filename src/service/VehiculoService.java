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
    public boolean placaDuplicada(String placa) {
    return vehiculoDAO.buscarPorPlaca(placa) != null;
}
    public void registrarVehiculo(Vehiculo vehiculo) {
    if (placaDuplicada(vehiculo.getPlaca())) {
        System.out.println("Ya existe un vehiculo con la placa: " + vehiculo.getPlaca());
        return;
    }
    vehiculoDAO.guardar(vehiculo);
    System.out.println("Vehiculo registrado exitosamente.");
}

    public List<Vehiculo> listarVehiculos() {
    return vehiculoDAO.listarTodos();
}

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
    Vehiculo v = vehiculoDAO.buscarPorPlaca(placa);
    if (v == null) {
        System.out.println("No se encontro ningun vehiculo con la placa: " + placa);
    }
    return v;
}
}
