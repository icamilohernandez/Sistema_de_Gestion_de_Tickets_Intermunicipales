package model;

public class PasajeroRegular extends Pasajero {
    
    public PasajeroRegular(String nombre, String identificacion) {
        super(nombre, identificacion);
    }
    
    @Override
    public double calcularTarifa(double distancia) {
        return distancia * 100;
    }
    
    @Override
    public String getTipoPasajero() {
        return "Regular";
    }
}