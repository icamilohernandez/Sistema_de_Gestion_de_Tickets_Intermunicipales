/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    private List<Ticket> tickets = new ArrayList<>();
    
    public void guardar(Ticket ticket) {
        tickets.add(ticket);
        System.out.println("Ticket guardado: " + ticket.getCodigo());
    }
    
    public Ticket buscarPorCodigo(String codigo) {
        for (Ticket t : tickets) {
            if (t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }

    public List<Ticket> buscarTodos() {
        return new ArrayList<>(tickets);
    }
    
    public void actualizar(Ticket ticket) {
        Ticket existente = buscarPorCodigo(ticket.getCodigo());
        if (existente != null) {
            tickets.remove(existente);
            tickets.add(ticket);
            System.out.println("Ticket actualizado: " + ticket.getCodigo());
        }
    }
    
    public void eliminar(String codigo) {
        Ticket ticket = buscarPorCodigo(codigo);
        if (ticket != null) {
            tickets.remove(ticket);
            System.out.println("Ticket eliminado: " + codigo);
        }
    }
}
