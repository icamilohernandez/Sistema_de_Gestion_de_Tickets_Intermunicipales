package service;

import dao.ReservaDAO;
import dao.ReservaDAOImpl;
import dao.VehiculoDao;
import model.Buseta;
import model.Bus;
import model.EstadoReserva;
import model.MicroBus;
import model.Reserva;
import model.Ruta;
import model.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class VehiculoService {

    private VehiculoDao vehiculoDao;
    private ReservaDAO reservaDAO;

    private static final List<Ruta> rutasDisponibles = new ArrayList<>();
    static {
        rutasDisponibles.add(new Ruta("R001", "Bogota",   "Medellin",  420, 480));
        rutasDisponibles.add(new Ruta("R002", "Bogota",   "Cali",      510, 540));
        rutasDisponibles.add(new Ruta("R003", "Medellin", "Cartagena", 650, 720));
    }

    public VehiculoService() {
        this.vehiculoDao = new VehiculoDao();
        this.reservaDAO  = new ReservaDAOImpl();
    }

    public static List<Ruta> getRutasDisponibles() {
        return rutasDisponibles;
    }

    public void registrar(String placa, String codigoRuta, String modelo) {
        Ruta ruta = null;
        for (Ruta r : rutasDisponibles) {
            if (r.getCodigoRuta().equalsIgnoreCase(codigoRuta)) { ruta = r; break; }
        }
        if (ruta == null) {
            System.out.println("Ruta no encontrada. Rutas disponibles:");
            for (Ruta r : rutasDisponibles) System.out.println("  " + r);
            return;
        }
        Vehiculo vehiculo;
        switch (modelo.toLowerCase()) {
            case "buseta":   vehiculo = new Buseta(placa, ruta);   break;
            case "microbus": vehiculo = new MicroBus(placa, ruta); break;
            case "bus":      vehiculo = new Bus(placa, ruta);      break;
            default:
                System.out.println("Modelo no valido. Use: buseta, microbus o bus.");
                return;
        }
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
        if (v == null) System.out.println("No se encontro ningun vehiculo con la placa: " + placa);
        return v;
    }

    public int contarReservasActivas(String placa) {
        int count = 0;
        for (Reserva r : reservaDAO.listarTodas()) {
            if (r.getVehiculo() != null &&
                r.getVehiculo().getPlaca().equalsIgnoreCase(placa) &&
                r.getEstado().equals(EstadoReserva.ACTIVA)) {
                count++;
            }
        }
        return count;
    }

    public boolean verificarDisponibilidad(String placa) {
        Vehiculo v = vehiculoDao.buscarPorPlaca(placa);
        if (v == null) return false;
        int ocupados = v.getPasajerosActuales() + contarReservasActivas(placa);
        return ocupados < v.getCapacidadMax();
    }

    public void actualizarCupos(String placa) {
        Vehiculo v = vehiculoDao.buscarPorPlaca(placa);
        if (v == null) { System.out.println("No se encontro el vehiculo: " + placa); return; }
        if (!verificarDisponibilidad(placa)) {
            System.out.println("El vehiculo no tiene cupos disponibles."); return;
        }
        v.setPasajerosActuales(v.getPasajerosActuales() + 1);
        if (v.getPasajerosActuales() == v.getCapacidadMax()) v.setDisponible(false);
        vehiculoDao.actualizar(v);
        System.out.println("Cupo actualizado. Pasajeros actuales: " + v.getPasajerosActuales());
    }

    public List<Vehiculo> obtenerVehiculosDisponibles() {
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo v : vehiculoDao.listarTodos()) {
            if (v.isDisponible()) disponibles.add(v);
        }
        return disponibles;
    }
}