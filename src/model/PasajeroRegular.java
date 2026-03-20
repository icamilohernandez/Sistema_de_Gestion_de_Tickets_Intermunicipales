package model;

import java.time.LocalDate;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String nombre, String cedula, int edad,
                           String sexo, String telefono, LocalDate fechaNacimiento) {
        super(nombre, cedula, edad, sexo, telefono, TipoPasajero.Regular, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void verEstadisticas() {
        System.out.println("Tipo: Regular | Sin descuento");
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== PASAJERO REGULAR ===");
        System.out.println("Cedula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + calcularEdad());
        System.out.println("Descuento: 0%");
    }

    @Override
    public String toString() {
        return "Regular | " + super.toString();
    }
}