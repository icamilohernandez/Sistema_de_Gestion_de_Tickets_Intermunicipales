/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Persona;
import java.util.List;

import dao.PersonaDao;

public class PersonaService {
    private PersonaDao personaDao;
    
    public PersonaService() {
        this.personaDao = new PersonaDao();
    }
    
    public void registrarPersona(Persona persona) {

        if (persona.getNombre() == null || persona.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (persona.getCedula() == null || persona.getCedula().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía");
        }
        personaDao.guardar(persona);
    }
    
    public Persona buscarPersona(String id) {
        return personaDao.buscarPorId(id);
    }
    

    public List<Persona> listarPersonas() {
        return personaDao.buscarTodos();
    }
    

    public void actualizarPersona(Persona persona) {
        personaDao.actualizar(persona);
    }
    
    public void eliminarPersona(String id) {
        personaDao.eliminar(id);
    }
}
