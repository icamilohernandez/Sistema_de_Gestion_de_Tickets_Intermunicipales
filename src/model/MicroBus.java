package model;

public class MicroBus extends Vehiculo implements Imprimible {

     private static final int CAPACIDAD = 25;
    private static final double TARIFA_BASE = 10000;
    
    public MicroBus() {
        super();
    }

    public MicroBus(String placa, String ruta) {
        super(placa, ruta, CAPACIDAD, 0, true);
    }

    @Override
    public double calcularTarifa() {
        return TARIFA_BASE;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== MICROBUS ===");
        System.out.println("Placa      : " + getPlaca());
        System.out.println("Ruta       : " + getRuta());
        System.out.println("Capacidad  : " + getCapacidadMax());
        System.out.println("Pasajeros  : " + getPasajerosActuales());
        System.out.println("Tarifa     : $" + calcularTarifa());
        System.out.println("Disponible : " + isDisponible());
    }

    @Override
    public String toString() {
        return "MicroBus | " + super.toString() + " | Tarifa: $" + calcularTarifa();
    }
}
