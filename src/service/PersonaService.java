/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Persona;
import java.util.List;

public interface PersonaService {
    void registrarPersona(Persona persona);
    Persona buscarPersona(String id);
    List<Persona> listarPersonas();
    void actualizarPersona(Persona persona);
    void eliminarPersona(String id);
}
