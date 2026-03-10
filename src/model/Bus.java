/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Bus extends Vehiculo implements Imprimible {

    public Bus() {
        super();
    }

    public Bus(String placa, String ruta) {
        super(placa, ruta, 45);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== BUS ===");
        System.out.println("Placa      : " + getPlaca());
        System.out.println("Ruta       : " + getRuta());
        System.out.println("Capacidad  : " + getCapacidadMax());
        System.out.println("Pasajeros  : " + getPasajerosActuales());
        System.out.println("Tarifa     : $" + calcularTarifa());
        System.out.println("Disponible : " + isDisponible());
    }

    @Override
    public String toString() {
        return "Bus | " + super.toString() + " | Tarifa: $" + calcularTarifa();
    }
}