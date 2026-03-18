/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ivanc
 */
import dao.VehiculoDao;
import java.util.ArrayList;
import java.util.List;
import model.Vehiculo;

public class VehiculoService {

    private VehiculoDao vehiculoDao;

    public VehiculoService() {
        this.vehiculoDao = new VehiculoDao();
    }
    public boolean placaDuplicada(String placa) {
    return vehiculoDao.buscarPorPlaca(placa) != null;
}
    public void registrarVehiculo(Vehiculo vehiculo) {
    if (placaDuplicada(vehiculo.getPlaca())) {
        System.out.println("Ya existe un vehiculo con la placa: " + vehiculo.getPlaca());
        return;
    }
    vehiculoDao.guardar(vehiculo);
    System.out.println("Vehiculo registrado exitosamente.");
}

    public List<Vehiculo> listarVehiculos() {
    return vehiculoDao.listarTodos();
}

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
    Vehiculo v = vehiculoDao.buscarPorPlaca(placa);
    if (v == null) {
        System.out.println("No se encontro ningun vehiculo con la placa: " + placa);
    }
    return v;
}
    public boolean verificarDisponibilidad(String placa) {
    Vehiculo v = vehiculoDao.buscarPorPlaca(placa);
    if (v == null) return false;
    return v.getPasajerosActuales() < v.getCapacidadMax();
}

public void actualizarCupos(String placa) {
    Vehiculo v = vehiculoDao.buscarPorPlaca(placa);
    if (v == null) {
        System.out.println("No se encontro el vehiculo con la placa: " + placa);
        return;
    }
    if (!verificarDisponibilidad(placa)) {
        System.out.println("El vehiculo con placa " + placa + " no tiene cupos disponibles.");
        return;
    }
    v.setPasajerosActuales(v.getPasajerosActuales() + 1);
    if (v.getPasajerosActuales() == v.getCapacidadMax()) {
        v.setDisponible(false);
    }
    vehiculoDao.actualizar(v);
    System.out.println("Cupo actualizado. Pasajeros actuales: " + v.getPasajerosActuales());
}
public List<Vehiculo> obtenerVehiculosDisponibles() {
    List<Vehiculo> disponibles = new ArrayList<>();
    for (Vehiculo v : vehiculoDao.listarTodos()) {
        if (v.isDisponible()) {
            disponibles.add(v);
        }
    }
    return disponibles;
}
}
