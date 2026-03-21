/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCreacion, LocalDate fechaViaje, String estado) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje = fechaViaje;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo +
               " | Pasajero: " + pasajero.getNombre() +
               " | Vehiculo: " + vehiculo.getPlaca() +
               " | Fecha Creacion: " + fechaCreacion +
               " | Fecha Viaje: " + fechaViaje +
               " | Estado: " + estado;
    }
    @Override
    public void imprimirDetalle() {
        System.out.println("=== RESERVA ===");
        System.out.println("Codigo       : " + codigo);
        System.out.println("Pasajero     : " + pasajero.getNombre());
        System.out.println("Vehiculo     : " + vehiculo.getPlaca());
        System.out.println("Fecha Creacion: " + fechaCreacion);
        System.out.println("Fecha Viaje  : " + fechaViaje);
        System.out.println("Estado       : " + estado);
    }
}