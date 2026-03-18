package model;

public abstract class Pasajero extends Persona {
    
    public Pasajero(String nombre, String cedula, int edad, String sexo, String telefono) {
        super(nombre, cedula, edad, sexo, telefono);
    }
    
    public abstract double calcularTarifa(double distancia);
    
    public abstract String getTipoPasajero();
}