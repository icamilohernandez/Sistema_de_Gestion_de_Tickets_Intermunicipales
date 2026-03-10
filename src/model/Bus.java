/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Bus extends Vehiculo {

    public Bus() {
        super();
    }

    public Bus(String placa, String ruta) {
        super(placa, ruta, 45);
    }

    @Override
    public double calcularTarifa() {
        return 15000;
    }

}