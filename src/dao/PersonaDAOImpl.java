package dao;

import model.Persona;
import java.util.ArrayList;
import java.util.List;


public class PersonaDAOImpl implements PersonaDao {
    private List<Persona> personas = new ArrayList<>();
    
    @Override
    public void guardar(Persona persona) {
        personas.add(persona);
        System.out.println("Persona guardada: " + persona.getNombre());
    }
    
    @Override
    public Persona buscarPorId(String id) {
        for (Persona p : personas) {
            if (p.getIdentificacion().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    @Override
    public List<Persona> buscarTodos() {
        return new ArrayList<>(personas);
    }
    
    @Override
    public void actualizar(Persona persona) {
        Persona existente = buscarPorId(persona.getIdentificacion());
        if (existente != null) {
            existente.setNombre(persona.getNombre());
            System.out.println("Persona actualizada: " + persona.getNombre());
        }
    }
    
    @Override
    public void eliminar(String id) {
        Persona persona = buscarPorId(id);
        if (persona != null) {
            personas.remove(persona);
            System.out.println("Persona eliminada con ID: " + id);
        }
    }
}