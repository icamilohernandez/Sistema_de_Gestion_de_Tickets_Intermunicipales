package model;

import java.time.LocalDate;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String nombre, String cedula, int edad, String sexo, String telefono, LocalDate fechaNacimiento) {
        super(nombre, cedula, edad, sexo, telefono, TipoPasajero.Adulto_Mayor, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.30;
    }

    @Override
    public void verEstadisticas() {
        System.out.println("Tipo: Adulto Mayor | Descuento: 30%");
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== ADULTO MAYOR ===");
        System.out.println("Cedula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + calcularEdad());
        System.out.println("Descuento: 30%");
    }

    @Override
    public String toString() {
        return "AdultoMayor | " + super.toString();
    }
}