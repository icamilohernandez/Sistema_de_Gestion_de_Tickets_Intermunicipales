package model;

public class Bus extends Vehiculo implements Imprimible {

    public Bus() {
        super();
    }

    public Bus(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMax = 45;
    }

    @Override
    public double calcularTarifa() {
        return 2500.0;
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
