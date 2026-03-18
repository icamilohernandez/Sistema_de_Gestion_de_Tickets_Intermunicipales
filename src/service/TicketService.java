package service;

import dao.TicketDao;
import model.Ticket;
import model.Pasajero;
import model.Vehiculo;
import java.util.List;
import java.util.ArrayList;

public class TicketService {

    private TicketDao ticketDao;
    private VehiculoService vehiculoService;
    private PersonaService personaService;

    public TicketService() {
        this.ticketDao = new TicketDao();
        this.vehiculoService = new VehiculoService();
        this.personaService = new PersonaService();
    }
    
}
