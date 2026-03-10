/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Buseta extends Vehiculo {

    private static final int CAPACIDAD = 19;
    private static final double TARIFA_BASE = 8000;

    public Buseta() {
        super();
    }

    public Buseta(String placa, String ruta) {
        super(placa, ruta, CAPACIDAD);
    }

    @Override
    public double calcularTarifa() {
        return TARIFA_BASE;
    }

}
