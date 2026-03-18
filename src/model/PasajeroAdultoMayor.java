    package model;

    public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String nombre, String cedula, int edad, String sexo, String telefono) {
        super(nombre, cedula, edad, sexo, telefono, TipoPasajero.Adulto_Mayor);
    }

    @Override
    public double calDescuento() {
        return 0.30;
    }

    @Override
    public void verEstadisticas() {
        System.out.println("Tipo: Adulto Mayor | Descuento: 30%");
    }

    @Override
    public void impriDtlle() {
        System.out.println("=== ADULTO MAYOR ===");
        System.out.println("Cedula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + getEdad());
        System.out.println("Descuento: 30%");
    }

    @Override
    public String toString() {
        return "AdultoMayor | " + super.toString();
    }
}
