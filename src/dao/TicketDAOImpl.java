package dao;

import model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDao {
    private List<Ticket> tickets = new ArrayList<>();
    
    @Override
    public void guardar(Ticket ticket) {
        tickets.add(ticket);
        System.out.println("Ticket guardado: " + ticket.getCodigo());
    }
    
    @Override
    public Ticket buscarPorCodigo(String codigo) {
        for (Ticket t : tickets) {
            if (t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }
    
    @Override
    public List<Ticket> buscarTodos() {
        return new ArrayList<>(tickets);
    }
    
    @Override
    public void actualizar(Ticket ticket) {
        Ticket existente = buscarPorCodigo(ticket.getCodigo());
        if (existente != null) {
            tickets.remove(existente);
            tickets.add(ticket);
            System.out.println("Ticket actualizado: " + ticket.getCodigo());
        }
    }
    
    @Override
    public void eliminar(String codigo) {
        Ticket ticket = buscarPorCodigo(codigo);
        if (ticket != null) {
            tickets.remove(ticket);
            System.out.println("Ticket eliminado: " + codigo);
        }
    }
}