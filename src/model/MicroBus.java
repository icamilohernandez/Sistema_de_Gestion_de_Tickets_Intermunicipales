package model;

public class MicroBus extends Vehiculo implements Imprimible {
    
    public MicroBus() {
        super();
    }

    public MicroBus(String placa, String ruta) {
        super(placa, ruta, 25);
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
