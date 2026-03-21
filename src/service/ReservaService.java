package service;

import dao.ReservaDAO;
import dao.ReservaDAOImpl;
import model.EstadoReserva;
import model.Pasajero;
import model.Reserva;
import model.Vehiculo;
import java.time.LocalDate;
import java.util.List;


public class ReservaService {

    private ReservaDAO reservaDAO;
    private VehiculoService vehiculoService;
    private PersonaService personaService;
    private TicketService ticketService;

    private static int contador = 1;

    public ReservaService(PersonaService personaService, VehiculoService vehiculoService, TicketService ticketService) {
        this.reservaDAO      = new ReservaDAOImpl();
        this.personaService  = personaService;
        this.vehiculoService = vehiculoService;
        this.ticketService   = ticketService;
    }

    // Commit 2: Validar capacidad del vehiculo
    private boolean validarCapacidad(String placaVehiculo) {
        return vehiculoService.verificarDisponibilidad(placaVehiculo);
    }

    // Commit 3: Validar reserva duplicada por pasajero
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

    // Commit 4: Crear reserva
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

    // Commit 5: Cancelar reserva
    public String cancelarReserva(String codigo) {
        Reserva reserva = reservaDAO.buscarPorCodigo(codigo);
        if (reserva == null) {
            return "Error: no se encontro la reserva con codigo " + codigo;
        }
        if (!reserva.getEstado().equals(EstadoReserva.ACTIVA)) {
            return "Error: la reserva " + codigo + " no esta activa (estado: " + reserva.getEstado() + ")";
        }
        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaDAO.actualizar(reserva);
        return "Reserva " + codigo + " cancelada exitosamente.";
    }

    // Commit 6: Convertir reserva en ticket
    public String convertirEnTicket(String codigoReserva, String origen, String destino) {
        Reserva reserva = reservaDAO.buscarPorCodigo(codigoReserva);
        if (reserva == null) {
            return "Error: no se encontro la reserva con codigo " + codigoReserva;
        }
        if (!reserva.getEstado().equals(EstadoReserva.ACTIVA)) {
            return "Error: la reserva no esta activa (estado: " + reserva.getEstado() + ")";
        }

        String resultado = ticketService.venderTicket(
            reserva.getPasajero().getCedula(),
            reserva.getVehiculo().getPlaca(),
            origen, destino
        );

        reserva.setEstado(EstadoReserva.CONVERTIDA);
        reservaDAO.actualizar(reserva);

        return "Reserva convertida en ticket.\n" + resultado;
    }

    public String verificarReservasVencidas() {
        List<Reserva> lista = reservaDAO.listarTodas();
        int vencidas = 0;
        for (Reserva r : lista) {
            if (!r.getEstado().equals(EstadoReserva.ACTIVA)) continue;
            long horas = java.time.temporal.ChronoUnit.HOURS.between(
                r.getFechaCreacion().atStartOfDay(),
                LocalDate.now().atStartOfDay()
            );
            if (horas > 24) {
                r.setEstado(EstadoReserva.CANCELADA);
                reservaDAO.actualizar(r);
                vencidas++;
            }
        }
        return vencidas > 0
            ? vencidas + " reserva(s) vencidas canceladas automaticamente."
            : "No hay reservas vencidas.";
    }
}