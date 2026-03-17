/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;

import model.Persona;

public interface PersonaDao {
     void guardar(Persona persona);
    Persona buscarPorId(String id);
    List<Persona> buscarTodos();
    void actualizar(Persona persona);
    void eliminar(String id);
    
}
