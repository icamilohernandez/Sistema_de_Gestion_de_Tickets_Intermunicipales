package model;

import java.util.Date;

public class Ticket implements Imprimible, Calculable {

    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private Date fechaCompra;
    private String origen;
    private String destino;
    private double valorFinal;

    public Ticket(String codigo, Pasajero pasajero, Vehiculo vehiculo, String origen, String destino) {
        this.codigo      = codigo;
        this.pasajero    = pasajero;
        this.vehiculo    = vehiculo;
        this.fechaCompra = new Date();
        this.origen      = origen;
        this.destino     = destino;
        
    double tarifa    = vehiculo.calcularTarifa();
    double descuento = pasajero.calDescuento();
        this.valorFinal  = tarifa - (tarifa * descuento);
    }

    @Override
    public double calcularTotal() {
        return valorFinal;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== TICKET ===");
        System.out.println("Codigo    : " + codigo);
        System.out.println("Pasajero  : " + pasajero.getNombre());
        System.out.println("Vehiculo  : " + vehiculo.getPlaca());
        System.out.println("Origen    : " + origen);
        System.out.println("Destino   : " + destino);
        System.out.println("Fecha     : " + fechaCompra);
        System.out.println("Valor     : $" + valorFinal);
    }

    @Override
    public String toString() {
        return "Ticket | " + codigo + " | " + pasajero.getNombre() + 
               " | " + vehiculo.getPlaca() + " | $" + valorFinal;
    }

    public String getCodigo() {
        return codigo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getValorFinal() {
        return valorFinal;
    }
}