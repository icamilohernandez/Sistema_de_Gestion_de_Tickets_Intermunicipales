/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Ticket;
import java.util.List;

public interface TicketDao {
    void guardar(Ticket ticket);
    Ticket buscarPorCodigo(String codigo);
    List<Ticket> buscarTodos();
    void actualizar(Ticket ticket);
    void eliminar(String codigo);
}
