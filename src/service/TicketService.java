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

     public String venderTicket(String cedulaPasajero, String placaVehiculo,
                                String origen, String destino) {
                                    
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

        double tarifaBase = vehiculo.calTarifa();
        double descuento  = pasajero.calDescuento();
        double valorFinal = tarifaBase - (tarifaBase * descuento);
        
    }
}
