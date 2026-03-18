package model;

public abstract class Pasajero extends Persona {
    
    public Pasajero(String nombre, String cedula, String edad, String sexo, String telefono) {
        super(nombre, cedula, String.valueOf(edad), sexo, telefono);
    }
    
    public abstract double calcularTarifa(double distancia);
    
    public abstract String getTipoPasajero();
}