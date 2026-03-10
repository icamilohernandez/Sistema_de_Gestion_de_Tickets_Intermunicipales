/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class MicroBus extends Vehiculo {

    public MicroBus() {
        super();
    }

    public MicroBus(String placa, String ruta) {
        super(placa, ruta, 25);
    }

    @Override
    public double calcularTarifa() {
        return 10000;
    }

}
