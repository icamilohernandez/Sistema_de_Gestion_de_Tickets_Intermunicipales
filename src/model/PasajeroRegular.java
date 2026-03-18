package model;

public class PasajeroRegular extends Pasajero {
    
    public PasajeroRegular(String nombre, String cedula, int edad, String sexo, String telefono) {
        super(nombre, cedula, String.valueOf(edad), sexo, telefono);
    }
    
    @Override
    public double calcularTarifa(double distancia) {
        return distancia * 100;
    }
    
    @Override
    public String getTipoPasajero() {
        return "Regular";
    }
    
    @Override
    public void verEstadisticas() {
        System.out.println("Estadísticas del Pasajero Regular: " + nombre);
    }
}