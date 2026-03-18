package service;

import dao.TicketDao;
import model.Ticket;
import model.Pasajero;
import model.Vehiculo;
import java.util.List;

public class TicketService {

    private static int contaTickets = 1;
    private TicketDao ticketDao;
    private VehiculoService vehiculoService;
    private PersonaService personaService;

    public TicketService() {
        this.ticketDao = new TicketDao();
        this.vehiculoService = new VehiculoService();
        this.personaService = new PersonaService();
    }

     public String venderTicket(String cedulaPasajero, String placaVehiculo, String origen, String destino) {
                                    
    Pasajero pasajero = (Pasajero) personaService.buscarPersona(cedulaPasajero);
        if (pasajero == null) {
            return "Error: no se encontro el pasajero con cedula " + cedulaPasajero;
        }

        Vehiculo vehiculo = vehiculoService.buscarVehiculoPorPlaca(placaVehiculo);
        if (vehiculo == null) {
            return "Error: no se encontro el vehiculo con placa " + placaVehiculo;
        }

        if (!vehiculoService.verificarDisponibilidad(placaVehiculo)) {
            return "Error: el vehiculo con placa " + placaVehiculo + " no tiene cupos disponibles";
        }

        String codigo = "TCK" + contaTickets++;
        double tarifaBase = vehiculo.calcularTarifa();
        double descuento  = pasajero.calcularDescuento();
        double valorFinal = tarifaBase - (tarifaBase * descuento);
        
        Ticket ticket = new Ticket(codigo, pasajero, vehiculo, origen, destino);
        ticketDao.guardar(ticket);

        vehiculoService.actualizarCupos(placaVehiculo);

        return "Ticket vendido exitosamente. Valor: $" + valorFinal;
    }

    public List<Ticket> listarTickets() {
        return ticketDao.buscarTodos();
    }

    public Ticket buscarTicket(String id) {
        Ticket t = ticketDao.buscarPorCodigo(id);
        if (t == null) {
            System.out.println("No se encontro el ticket con id: " + id);
        }
        return t;
    }

    public double calcularTotalRecaudado() {
        double total = 0;
        for (Ticket t : ticketDao.buscarTodos()) {
            total += t.getValorFinal();
        }
        return total;
    }

    public void mostrarEstadisticas() {
        List<Ticket> tickets = ticketDao.buscarTodos();

        System.out.println("Total recaudado: $" + calcularTotalRecaudado());

        int regular = 0, estudiante = 0, adultoMayor = 0;
        for (Ticket t : tickets) {
            switch (t.getPasajero().getTipo()) {
                case Regular:      regular++;      break;
                case Estudiante:   estudiante++;   break;
                case Adulto_Mayor:  adultoMayor++;  break;
            }
        }
        System.out.println("Pasajeros regulares:     " + regular);
        System.out.println("Pasajeros estudiantes:   " + estudiante);
        System.out.println("Pasajeros adulto mayor:  " + adultoMayor);

        Vehiculo vehiculoTop = null;
        int maxTickets = 0;
        for (Ticket t : tickets) {
            int count = 0;
            for (Ticket t2 : tickets) {
                if (t2.getVehiculo().getPlaca().equals(t.getVehiculo().getPlaca())) {
                    count++;
                }
            }
            if (count > maxTickets) {
                maxTickets = count;
                vehiculoTop = t.getVehiculo();
            }
        }
        if (vehiculoTop != null) {
            System.out.println("Vehiculo con mas tickets: " + vehiculoTop.getPlaca() 
                             + " con " + maxTickets + " tickets");
        }
    }
}
