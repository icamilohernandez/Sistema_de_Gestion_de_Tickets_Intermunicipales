package model;

public class Ticket {
    private String codigo;
    private Pasajero pasajero;
    private double distancia;
    private double tarifa;
    
    public Ticket(String codigo, Pasajero pasajero, double distancia) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.distancia = distancia;
        this.tarifa = pasajero.calcularTarifa(distancia);
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public Pasajero getPasajero() {
        return pasajero;
    }
    
    public double getDistancia() {
        return distancia;
    }
    
    public double getTarifa() {
        return tarifa;
    }
}