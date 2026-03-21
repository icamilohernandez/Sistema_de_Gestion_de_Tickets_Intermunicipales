/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

/**
 *
 * @author Csamu
 */

import java.util.List;
import model.Reserva;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public interface ReservaDAO {
    void guardar(Reserva reserva);
    List<Reserva> listarTodas();
    Reserva buscarPorCodigo(String codigo);
    void actualizar(Reserva reserva);
    void eliminar(String codigo);
    public List<Reserva> cargarYVerificarVencidas() {
    List<Reserva> lista = listarTodas();
    int vencidas = 0;
    for (Reserva r : lista) {
        if (r.getEstado().equals(EstadoReserva.ACTIVA)) {
            long horas = java.time.temporal.ChronoUnit.HOURS.between(
                r.getFechaCreacion().atStartOfDay(),
                LocalDate.now().atStartOfDay()
            );
            if (horas > 24) {
                r.setEstado(EstadoReserva.CANCELADA);
                actualizar(r);
                vencidas++;
            }
        }
    }
    if (vencidas > 0) {
        System.out.println(vencidas + " reserva(s) vencidas canceladas automaticamente.");
    }
    return lista;
}
}
