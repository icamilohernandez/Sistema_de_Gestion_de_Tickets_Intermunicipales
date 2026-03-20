package model;

public class Bus extends Vehiculo implements Imprimible {

    private double tarifa = 15000;

    public Bus() {
        super();
    }

    public Bus(String placa, Ruta ruta) {
        super(placa, ruta, 45, 0, true);
    }

    public double getTarifa() {
        return tarifa;
    }

    @Override
    public double calcularTarifa() {
        return tarifa;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== BUS ===");
        System.out.println("Placa      : " + getPlaca());
        System.out.println("Ruta       : " + (getRuta() != null ? getRuta().getCiudadOrigen() + " → " + getRuta().getCiudadDestino() : "Sin ruta"));
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