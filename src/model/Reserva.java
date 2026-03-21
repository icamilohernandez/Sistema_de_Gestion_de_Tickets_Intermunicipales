package model;

import java.time.LocalDate;

public class Reserva implements Imprimible {

    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCreacion;
    private LocalDate fechaViaje;
    private String estado;

    public Reserva() {}

    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo,
                   LocalDate fechaCreacion, LocalDate fechaViaje, String estado) {
        this.codigo        = codigo;
        this.pasajero      = pasajero;
        this.vehiculo      = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje    = fechaViaje;
        this.estado        = estado;
    }

    public String getCodigo()                          { return codigo; }
    public void setCodigo(String codigo)               { this.codigo = codigo; }
    public Pasajero getPasajero()                      { return pasajero; }
    public void setPasajero(Pasajero pasajero)         { this.pasajero = pasajero; }
    public Vehiculo getVehiculo()                      { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo)         { this.vehiculo = vehiculo; }
    public LocalDate getFechaCreacion()                { return fechaCreacion; }
    public void setFechaCreacion(LocalDate f)          { this.fechaCreacion = f; }
    public LocalDate getFechaViaje()                   { return fechaViaje; }
    public void setFechaViaje(LocalDate f)             { this.fechaViaje = f; }
    public String getEstado()                          { return estado; }
    public void setEstado(String estado)               { this.estado = estado; }

    @Override
    public String toString() {
        return "Codigo: " + codigo +
               " | Pasajero: " + (pasajero != null ? pasajero.getNombre() : "N/A") +
               " | Vehiculo: " + (vehiculo != null ? vehiculo.getPlaca() : "N/A") +
               " | Fecha Creacion: " + fechaCreacion +
               " | Fecha Viaje: " + fechaViaje +
               " | Estado: " + estado;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== RESERVA ===");
        System.out.println("Codigo        : " + codigo);
        System.out.println("Pasajero      : " + (pasajero != null ? pasajero.getNombre() : "N/A"));
        System.out.println("Vehiculo      : " + (vehiculo != null ? vehiculo.getPlaca() : "N/A"));
        System.out.println("Fecha Creacion: " + fechaCreacion);
        System.out.println("Fecha Viaje   : " + fechaViaje);
        System.out.println("Estado        : " + estado);
    }
}