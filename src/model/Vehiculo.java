/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public abstract class Vehiculo {

    private String placa;
    private String ruta;
    private int capacidadMax;
    private int pasajerosActuales;
    private boolean disponible;

    public Vehiculo (){}
    
    public Vehiculo(String placa, String ruta, int capacidadMax, int pasajerosActuales, boolean disponible) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMax = capacidadMax;
        this.pasajerosActuales = pasajerosActuales;
        this.disponible = disponible;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public int getPasajerosActuales() {
        return pasajerosActuales;
    }

    public void setPasajerosActuales(int pasajerosActuales) {
        this.pasajerosActuales = pasajerosActuales;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public abstract double calcularTarifa();
    
    @Override
    public String toString() {
        return "Placa: " + getPlaca() +
               " | Ruta: " + getRuta() +
               " | Capacidad: " + getCapacidadMax() +
               " | Pasajeros: " + getPasajerosActuales() +
               " | Disponible: " + isDisponible();
    }
}
