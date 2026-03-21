/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.VehiculoDao;
import java.util.ArrayList;
import java.util.List;
import model.Vehiculo;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class VehiculoService {

    private VehiculoDao vehiculoDao;

    public VehiculoService() {
        this.vehiculoDao = new VehiculoDao();
    }

    
    public void registrar(String placa, String marca, String modelo) {
        Vehiculo vehiculo = new Vehiculo(placa, marca, modelo);
        registrarVehiculo(vehiculo);
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
    private List<LocalDate> festivos = Arrays.asList(
    LocalDate.of(2025, 1, 1),   // Año nuevo
    LocalDate.of(2025, 1, 6),   // Reyes Magos
    LocalDate.of(2025, 3, 24),  // Semana Santa
    LocalDate.of(2025, 4, 18),  // Viernes Santo
    LocalDate.of(2025, 5, 1),   // Día del trabajo
    LocalDate.of(2025, 6, 2),   // Ascensión
    LocalDate.of(2025, 6, 23),  // Corpus Christi
    LocalDate.of(2025, 6, 30),  // Sagrado Corazón
    LocalDate.of(2025, 7, 20),  // Independencia
    LocalDate.of(2025, 8, 7),   // Batalla de Boyacá
    LocalDate.of(2025, 8, 18),  // Asunción
    LocalDate.of(2025, 10, 13), // Día de la Raza
    LocalDate.of(2025, 11, 3),  // Todos los Santos
    LocalDate.of(2025, 11, 17), // Independencia Cartagena
    LocalDate.of(2025, 12, 8),  // Inmaculada Concepción
    LocalDate.of(2025, 12, 25)  // Navidad
);

public boolean esFestivo(LocalDate fecha) {
    return festivos.contains(fecha);
}
}