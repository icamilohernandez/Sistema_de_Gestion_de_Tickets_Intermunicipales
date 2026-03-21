package dao;

import java.util.List;
import model.Reserva;

public interface ReservaDAO {
    void guardar(Reserva reserva);
    List<Reserva> listarTodas();
    Reserva buscarPorCodigo(String codigo);
    void actualizar(Reserva reserva);
    void eliminar(String codigo);
    List<Reserva> cargarYVerificarVencidas();
}