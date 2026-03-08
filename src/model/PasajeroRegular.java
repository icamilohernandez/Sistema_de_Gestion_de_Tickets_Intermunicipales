package model;

public class PasajeroRegular extends Pasajero {
    
    public PasajeroRegular(String nombre, String identificacion) {
        super(nombre, identificacion);
    }
    
    @Override
    public double calcularTarifa(double distancia) {
        // Tarifa regular: $100 por km
        return distancia * 100;
    }
    
    @Override
    public String getTipoPasajero() {
        return "Regular";
    }
}