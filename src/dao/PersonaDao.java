/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.util.ArrayList;
import model.Persona;

public class PersonaDao {
    private List<Persona> personas = new ArrayList<>();
    

    public void guardar(Persona persona) {
        personas.add(persona);
        System.out.println("Persona guardada: " + persona.getNombre());
    }

    public Persona buscarPorId(String id) {
        for (Persona p : personas) {
            if (p.getCedula().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    public List<Persona> buscarTodos() {
        return new ArrayList<>(personas);
    }
    
    public void actualizar(Persona persona) {
        Persona existente = buscarPorId(persona.getCedula());
        if (existente != null) {
            existente.setNombre(persona.getNombre());
            System.out.println("Persona actualizada: " + persona.getNombre());
        }
    }
    
    public void eliminar(String id) {
        Persona persona = buscarPorId(id);
        if (persona != null) {
            personas.remove(persona);
            System.out.println("Persona eliminada con ID: " + id);
        }
    }
}
