package model;

public abstract class Pasajero extends Persona {

    public enum TipoPasajero { Regular, Estudiante, Adulto_Mayor }

    private TipoPasajero tipo;

    public Pasajero(String nombre, String cedula, int edad, String sexo, String telefono, TipoPasajero tipo) {
        super(nombre, cedula, edad, sexo, telefono);
        this.tipo = tipo;
    }

    public TipoPasajero getTipo() {
        return tipo;
    }

    public abstract double calcularDescuento();

    public abstract void imprimirDetalle();

    @Override
    public void verEstadisticas() {
    }

    @Override
    public String toString() {
        return "Cedula: " + getCedula() + " | Nombre: " + getNombre() + " | Tipo: " + tipo;
    }
}