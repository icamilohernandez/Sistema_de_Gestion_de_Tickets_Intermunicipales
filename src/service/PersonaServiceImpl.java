package service;

import dao.PersonaDao;
import dao.PersonaDAOImpl;
import model.Persona;
import java.util.List;

public class PersonaServiceImpl implements PersonaService {
    private PersonaDao personaDAO;
    
    public PersonaServiceImpl() {
        this.personaDAO = new PersonaDAOImpl();
    }
    
    @Override
    public void registrarPersona(Persona persona) {
        // Aquí se pueden agregar validaciones
        if (persona.getNombre() == null || persona.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (persona.getCedula() == null || persona.getCedula().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía");
        }
        personaDAO.guardar(persona);
    }
    
    @Override
    public Persona buscarPersona(String id) {
        return personaDAO.buscarPorId(id);
    }
    
    @Override
    public List<Persona> listarPersonas() {
        return personaDAO.buscarTodos();
    }
    
    @Override
    public void actualizarPersona(Persona persona) {
        personaDAO.actualizar(persona);
    }
    
    @Override
    public void eliminarPersona(String id) {
        personaDAO.eliminar(id);
    }
}