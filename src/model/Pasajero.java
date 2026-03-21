package model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pasajero extends Persona {

    public enum TipoPasajero { Regular, Estudiante, Adulto_Mayor }

    private TipoPasajero tipo;
    private LocalDate fechaNacimiento;

    public Pasajero(String nombre, String cedula, int edad, String sexo, String telefono,
                    TipoPasajero tipo, LocalDate fechaNacimiento) {
        super(nombre, cedula, edad, sexo, telefono);
        this.tipo = tipo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int calcularEdad() {
        if (fechaNacimiento == null) return getEdad();
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public boolean esAdultoMayor() {
        return calcularEdad() >= 60;
    }

    public TipoPasajero getTipo() { return tipo; }
    public void setTipo(TipoPasajero tipo) { this.tipo = tipo; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public abstract double calcularDescuento();

    public double calcularTarifa(double tarifaBase) {
        return tarifaBase * (1 - calcularDescuento());
    }

    public abstract void imprimirDetalle();

    @Override
    public void verEstadisticas() {}

    @Override
    public String toString() {
        return "Cedula: " + getCedula() + " | Nombre: " + getNombre() + " | Tipo: " + tipo;
    }
}