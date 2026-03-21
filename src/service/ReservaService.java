package service;

import dao.ReservaDAO;
import dao.ReservaDAOImpl;
import model.EstadoReserva;
import model.Pasajero;
import model.Reserva;
import model.Vehiculo;
import java.time.LocalDate;

public class ReservaService {

    private ReservaDAO reservaDAO;
    private VehiculoService vehiculoService;
    private PersonaService personaService;

    private static int contador = 1;

    public ReservaService(PersonaService personaService, VehiculoService vehiculoService) {
        this.reservaDAO      = new ReservaDAOImpl();
        this.personaService  = personaService;
        this.vehiculoService = vehiculoService;
    }

    
    private boolean validarCapacidad(String placaVehiculo) {
        return vehiculoService.verificarDisponibilidad(placaVehiculo);
    }

    private boolean tieneReservaActiva(String cedula, String placa, LocalDate fechaViaje) {
        for (Reserva r : reservaDAO.listarTodas()) {
            if (r.getPasajero() == null || r.getVehiculo() == null) continue;
            if (r.getPasajero().getCedula().equals(cedula) &&
                r.getVehiculo().getPlaca().equalsIgnoreCase(placa) &&
                r.getFechaViaje().equals(fechaViaje) &&
                r.getEstado().equals(EstadoReserva.ACTIVA)) {
                return true;
            }
        }
        return false;
    }

 
    public String crearReserva(String cedula, String placaVehiculo, LocalDate fechaViaje) {
        Pasajero pasajero = (Pasajero) personaService.buscarPersona(cedula);
        if (pasajero == null) {
            return "Error: no se encontro el pasajero con cedula " + cedula;
        }

        Vehiculo vehiculo = vehiculoService.buscarVehiculoPorPlaca(placaVehiculo);
        if (vehiculo == null) {
            return "Error: no se encontro el vehiculo con placa " + placaVehiculo;
        }

        if (!validarCapacidad(placaVehiculo)) {
            return "Error: el vehiculo " + placaVehiculo + " no tiene cupos disponibles.";
        }

        if (tieneReservaActiva(cedula, placaVehiculo, fechaViaje)) {
            return "Error: el pasajero ya tiene una reserva activa para ese vehiculo y fecha.";
        }

        String codigo = "RSV-" + String.format("%04d", contador++);
        Reserva reserva = new Reserva(codigo, pasajero, vehiculo,
                                      LocalDate.now(), fechaViaje,
                                      EstadoReserva.ACTIVA);
        reservaDAO.guardar(reserva);

        return "Reserva creada exitosamente.\n" +
               "  Codigo:      " + codigo + "\n" +
               "  Pasajero:    " + pasajero.getNombre() + "\n" +
               "  Vehiculo:    " + placaVehiculo + "\n" +
               "  Fecha viaje: " + fechaViaje;
    }
}