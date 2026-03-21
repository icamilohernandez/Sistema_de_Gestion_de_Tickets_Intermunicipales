package service;

import model.Pasajero;
import model.Persona;
import model.Ticket;
import model.Vehiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    private List<Ticket> tickets = new ArrayList<>();
    private int contador = 1;
    private VehiculoService vehiculoService = new VehiculoService();
    private PersonaService personaService = new PersonaServiceImpl();

    private static final int LIMITE_TICKETS_POR_DIA = 3;


    private int contarTicketsHoy(String cedula) {
        LocalDate hoy = LocalDate.now();
        int count = 0;
        for (Ticket t : tickets) {
            if (t.getPasajero().getCedula().equals(cedula) &&
                t.getFecha().equals(hoy)) {
                count++;
            }
        }
        return count;
    }

 
    @Override
    public void crearTicket(Ticket ticket) {
        tickets.add(ticket);
        System.out.println("Ticket creado: " + ticket.getCodigo());
    }

    @Override
    public Ticket consultarTicket(String codigo) {
        for (Ticket t : tickets) {
            if (t.getCodigo().equals(codigo)) return t;
        }
        return null;
    }

    @Override
    public List<Ticket> listarTickets() {
        return new ArrayList<>(tickets);
    }

    @Override
    public void actualizarTicket(Ticket ticket) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getCodigo().equals(ticket.getCodigo())) {
                tickets.set(i, ticket);
                System.out.println("Ticket actualizado.");
                return;
            }
        }
        System.out.println("Ticket no encontrado: " + ticket.getCodigo());
    }

    @Override
    public void cancelarTicket(String codigo) {
        tickets.removeIf(t -> t.getCodigo().equals(codigo));
        System.out.println("Ticket cancelado: " + codigo);
    }

    @Override
    public double calcularTarifaFinal(Ticket ticket) {
        return ticket.getTarifa();
    }

    @Override
    public String venderTicket(String cedulaPasajero, String placaVehiculo,
                               String origen, String destino) {

       
        Pasajero pasajero = null;
        for (Persona p : personaService.listarPersonas()) {
            if (p instanceof Pasajero && p.getCedula().equals(cedulaPasajero)) {
                pasajero = (Pasajero) p;
                break;
            }
        }
        if (pasajero == null) {
            return "Error: No se encontro un pasajero con cedula " + cedulaPasajero;
        }

        int ticketsHoy = contarTicketsHoy(cedulaPasajero);
        if (ticketsHoy >= LIMITE_TICKETS_POR_DIA) {
            return "Error: El pasajero " + pasajero.getNombre() +
                   " ya tiene " + ticketsHoy + " ticket(s) comprados hoy." +
                   " No puede comprar mas de " + LIMITE_TICKETS_POR_DIA + " tickets por dia.";
        }

        
        Vehiculo vehiculo = vehiculoService.buscarVehiculoPorPlaca(placaVehiculo);
        if (vehiculo == null) {
            return "Error: No se encontro un vehiculo con placa " + placaVehiculo;
        }

 
        if (!vehiculoService.verificarDisponibilidad(placaVehiculo)) {
            return "Error: El vehiculo con placa " + placaVehiculo + " no tiene cupos disponibles.";
        }

        
        double distancia = vehiculo.getRuta().getDistanciaKm();
        String codigo = "TK-" + String.format("%04d", contador++);
        Ticket ticket = new Ticket(codigo, pasajero, distancia);
        crearTicket(ticket);

     
        vehiculoService.actualizarCupos(placaVehiculo);

        return String.format(
            "Ticket vendido exitosamente.\n" +
            "  Codigo:    %s\n" +
            "  Pasajero:  %s\n" +
            "  Vehiculo:  %s\n" +
            "  Distancia: %.1f km\n" +
            "  Tarifa:    $%.0f (descuento %.0f%%)\n" +
            "  Tickets comprados hoy: %d/%d",
            codigo, pasajero.getNombre(), placaVehiculo,
            distancia, ticket.getTarifa(),
            pasajero.calcularDescuento() * 100,
            ticketsHoy + 1, LIMITE_TICKETS_POR_DIA
        );
    }
}