package model;

import java.time.LocalDate;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String nombre, String cedula, int edad,
                               String sexo, String telefono, LocalDate fechaNacimiento) {
        super(nombre, cedula, edad, sexo, telefono, TipoPasajero.Estudiante, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.15;
    }

    @Override
    public void verEstadisticas() {
        System.out.println("Tipo: Estudiante | Descuento: 15%");
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== ESTUDIANTE ===");
        System.out.println("Cedula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + calcularEdad());
        System.out.println("Descuento: 15%");
    }

    @Override
    public String toString() {
        return "Estudiante | " + super.toString();
    }
}