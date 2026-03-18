package model;

public class PasajeroEstudiante extends Pasajero {
    
    public PasajeroEstudiante(String nombre, String cedula, int edad, String sexo, String telefono) {
        super(nombre, cedula, String.valueOf(edad), sexo, telefono);
    }
    
    @Override
    public double calcularTarifa(double distancia) {
        // Estudiante: 50% descuento
        return distancia * 50;
    }
    
    @Override
    public String getTipoPasajero() {
        return "Estudiante";
    }
}