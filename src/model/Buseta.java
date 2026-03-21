package model;

public class Buseta extends Vehiculo implements Imprimible {

    private static final int CAPACIDAD = 19;
    private static final double TARIFA_BASE = 8000;

    public Buseta() { super(); }

    public Buseta(String placa, Ruta ruta) {
        super(placa, ruta, CAPACIDAD, 0, true);
    }

    @Override
    public double calcularTarifa() { return TARIFA_BASE; }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== BUSETA ===");
        System.out.println("Placa     : " + getPlaca());
        System.out.println("Ruta      : " + (getRuta() != null ? getRuta() : "Sin ruta"));
        System.out.println("Capacidad : " + getCapacidadMax());
        System.out.println("Pasajeros : " + getPasajerosActuales());
        System.out.println("Tarifa    : $" + calcularTarifa());
        System.out.println("Disponible: " + isDisponible());
    }

    @Override
    public String toString() {
        return "Buseta | " + super.toString() + " | Tarifa: $" + calcularTarifa();
    }
}