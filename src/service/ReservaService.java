package service;

import dao.ReservaDAO;
import dao.ReservaDAOImpl;
import model.EstadoReserva;
import model.Reserva;
import java.time.LocalDate;
import java.util.List;

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
}