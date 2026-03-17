package model;

public class PasajeroEstudiante extends Pasajero {
    
    public PasajeroEstudiante(String nombre, String identificacion) {
        super(nombre, identificacion);
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