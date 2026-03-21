package service;

import dao.TicketDao;
import model.Pasajero;
import model.Ticket;
import model.Vehiculo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TicketService {

    private static int contaTickets = 1;
    private static final int LIMITE_POR_DIA = 3;

    private TicketDao ticketDao;
    private VehiculoService vehiculoService;
    private PersonaService personaService;

    public TicketService(PersonaService personaService, VehiculoService vehiculoService) {
        this.ticketDao       = new TicketDao();
        this.personaService  = personaService;
        this.vehiculoService = vehiculoService;
    }

    private int contarTicketsHoy(String cedula) {
        Calendar hoy = Calendar.getInstance();
        int count = 0;
        for (Ticket t : ticketDao.buscarTodos()) {
            if (!t.getPasajero().getCedula().equals(cedula)) continue;
            Calendar fechaTicket = Calendar.getInstance();
            fechaTicket.setTime(t.getFechaCompra());
            if (fechaTicket.get(Calendar.YEAR)        == hoy.get(Calendar.YEAR) &&
                fechaTicket.get(Calendar.DAY_OF_YEAR) == hoy.get(Calendar.DAY_OF_YEAR)) {
                count++;
            }
        }
        return count;
    }

    public String venderTicket(String cedulaPasajero, String placaVehiculo,
                               String origen, String destino) {

        Pasajero pasajero = (Pasajero) personaService.buscarPersona(cedulaPasajero);
        if (pasajero == null) {
            return "Error: no se encontro el pasajero con cedula " + cedulaPasajero;
        }

        int ticketsHoy = contarTicketsHoy(cedulaPasajero);
        if (ticketsHoy >= LIMITE_POR_DIA) {
            return "Error: " + pasajero.getNombre() + " ya tiene " + ticketsHoy +
                   " ticket(s) hoy. No puede comprar mas de " + LIMITE_POR_DIA + " tickets por dia.";
        }

        Vehiculo vehiculo = vehiculoService.buscarVehiculoPorPlaca(placaVehiculo);
        if (vehiculo == null) {
            return "Error: no se encontro el vehiculo con placa " + placaVehiculo;
        }

        if (!vehiculoService.verificarDisponibilidad(placaVehiculo)) {
            return "Error: el vehiculo con placa " + placaVehiculo + " no tiene cupos disponibles";
        }

        String codigo = "TCK" + contaTickets++;
        Ticket ticket = new Ticket(codigo, pasajero, vehiculo, origen, destino);
        ticketDao.guardar(ticket);
        vehiculoService.actualizarCupos(placaVehiculo);

        return String.format(
            "Ticket vendido exitosamente.\n" +
            "  Codigo:   %s\n" +
            "  Pasajero: %s\n" +
            "  Vehiculo: %s\n" +
            "  Valor:    $%.0f (descuento %.0f%%)\n" +
            "  Tickets comprados hoy: %d/%d",
            codigo, pasajero.getNombre(), placaVehiculo,
            ticket.getValorFinal(), pasajero.calcularDescuento() * 100,
            ticketsHoy + 1, LIMITE_POR_DIA
        );
    }

    public List<Ticket> listarTickets() { return ticketDao.buscarTodos(); }

    public Ticket buscarTicket(String id) {
        Ticket t = ticketDao.buscarPorCodigo(id);
        if (t == null) System.out.println("No se encontro el ticket con id: " + id);
        return t;
    }

    public double calcularTotalRecaudado() {
        double total = 0;
        for (Ticket t : ticketDao.buscarTodos()) total += t.getValorFinal();
        return total;
    }

    public void mostrarEstadisticas() {
        List<Ticket> tickets = ticketDao.buscarTodos();
        System.out.println("Total de tickets vendidos: " + tickets.size());
        System.out.println("Total recaudado: $" + calcularTotalRecaudado());
        int regular = 0, estudiante = 0, adultoMayor = 0;
        for (Ticket t : tickets) {
            switch (t.getPasajero().getTipo()) {
                case Regular:      regular++;      break;
                case Estudiante:   estudiante++;   break;
                case Adulto_Mayor: adultoMayor++;  break;
            }
        }
        System.out.println("Pasajeros regulares:    " + regular);
        System.out.println("Pasajeros estudiantes:  " + estudiante);
        System.out.println("Pasajeros adulto mayor: " + adultoMayor);
    }

    public void reporteFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean encontrado = false;
        for (Ticket t : ticketDao.buscarTodos()) {
            if (sdf.format(t.getFechaCompra()).equals(fecha)) {
                System.out.println(t); encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay tickets para la fecha: " + fecha);
    }

    public void reporteTipoVehiculo(int tipo) {
        String[] tipos = {"Buseta", "MicroBus", "Bus"};
        String tipoNombre = (tipo >= 1 && tipo <= 3) ? tipos[tipo - 1] : "";
        boolean encontrado = false;
        for (Ticket t : ticketDao.buscarTodos()) {
            if (t.getVehiculo().getClass().getSimpleName().equals(tipoNombre)) {
                System.out.println(t); encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay tickets para ese tipo de vehiculo.");
    }

    public void reporteTipoPasajero(int tipo) {
        boolean encontrado = false;
        for (Ticket t : ticketDao.buscarTodos()) {
            boolean coincide = false;
            switch (tipo) {
                case 1: coincide = t.getPasajero().getTipo() == model.Pasajero.TipoPasajero.Regular;      break;
                case 2: coincide = t.getPasajero().getTipo() == model.Pasajero.TipoPasajero.Estudiante;   break;
                case 3: coincide = t.getPasajero().getTipo() == model.Pasajero.TipoPasajero.Adulto_Mayor; break;
            }
            if (coincide) { System.out.println(t); encontrado = true; }
        }
        if (!encontrado) System.out.println("No hay tickets para ese tipo de pasajero.");
    }

    public void resumenDiaActual() {
        Calendar hoy = Calendar.getInstance();
        double total = 0;
        int count = 0;
        for (Ticket t : ticketDao.buscarTodos()) {
            Calendar fechaTicket = Calendar.getInstance();
            fechaTicket.setTime(t.getFechaCompra());
            if (fechaTicket.get(Calendar.YEAR)        == hoy.get(Calendar.YEAR) &&
                fechaTicket.get(Calendar.DAY_OF_YEAR) == hoy.get(Calendar.DAY_OF_YEAR)) {
                System.out.println(t); total += t.getValorFinal(); count++;
            }
        }
        System.out.println("Total tickets hoy: " + count);
        System.out.println("Total recaudado hoy: $" + total);
    }
}