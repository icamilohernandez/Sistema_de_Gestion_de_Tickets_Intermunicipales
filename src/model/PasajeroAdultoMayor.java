package model;

public class PasajeroAdultoMayor extends Pasajero {
    
    public PasajeroAdultoMayor(String nombre, String identificacion) {
        super(nombre, identificacion);
    }
    
    @Override
    public double calcularTarifa(double distancia) {
        // Adulto mayor: 40% descuento
        return distancia * 60;
    }
    
    @Override
    public String getTipoPasajero() {
        return "Adulto Mayor";
    }
}