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

public interface ReservaDAO {
    void guardar(Reserva reserva);
    List<Reserva> listarTodas();
    Reserva buscarPorCodigo(String codigo);
    void actualizar(Reserva reserva);
    void eliminar(String codigo);
}
