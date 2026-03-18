package model;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String nombre, String cedula, int edad,
                            String sexo, String telefono) {
        super(nombre, cedula, edad, sexo, telefono, TipoPasajero.Regular);
    }

    @Override
    public double calDescuento() {
        return 0.0;
    }

    @Override
    public void verEstadisticas() {
        System.out.println("Tipo: Regular | Sin descuento");
    }

    @Override
    public void impriDtlle() {
        System.out.println("=== PASAJERO REGULAR ===");
        System.out.println("Cedula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + getEdad());
        System.out.println("Descuento: 0%");
    }

    @Override
    public String toString() {
        return "Regular | " + super.toString();
    }
}