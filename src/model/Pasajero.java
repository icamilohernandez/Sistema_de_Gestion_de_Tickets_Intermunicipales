package model;

public abstract class Pasajero extends Persona {
    
    public Pasajero(String nombre, String identificacion) {
        super(nombre, identificacion);
    }
    
    public abstract double calcularTarifa(double distancia);
    
    public abstract String getTipoPasajero();
}