package model;

import java.time.LocalDate;

public class Ticket {
    private String codigo;
    private Pasajero pasajero;
    private double distancia;
    private double tarifa;
    private LocalDate fecha;

    public Ticket(String codigo, Pasajero pasajero, double distancia) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.distancia = distancia;
        this.tarifa = pasajero.calcularTarifa(distancia);
        this.fecha = LocalDate.now();
    }

    public String getCodigo() { return codigo; }
    public Pasajero getPasajero() { return pasajero; }
    public double getDistancia() { return distancia; }
    public double getTarifa() { return tarifa; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        return "Ticket: " + codigo +
               " | Pasajero: " + pasajero.getNombre() +
               " | Fecha: " + fecha +
               " | Tarifa: $" + String.format("%.0f", tarifa);
    }
}