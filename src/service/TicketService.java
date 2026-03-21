package service;
 
import model.Ticket;
import java.util.List;
 
public interface TicketService {
    void crearTicket(Ticket ticket);
    Ticket consultarTicket(String codigo);
    List<Ticket> listarTickets();
    void actualizarTicket(Ticket ticket);
    void cancelarTicket(String codigo);
    double calcularTarifaFinal(Ticket ticket);
    String venderTicket(String cedulaPasajero, String placaVehiculo,
                        String origen, String destino);
}
 