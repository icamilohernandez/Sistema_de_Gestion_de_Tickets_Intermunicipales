/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Vehiculo;

public interface VehiculoDAO {
    void guardar(Vehiculo vehiculo);
    List<Vehiculo> listarTodos();
    Vehiculo buscarPorPlaca(String placa);
    void actualizar(Vehiculo vehiculo);
    void eliminar(String placa);
}
