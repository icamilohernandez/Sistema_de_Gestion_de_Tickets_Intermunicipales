package model;


public abstract class Vehiculo {

    private String placa;
    private Ruta ruta;  
    private int capacidadMax;
    private int pasajerosActuales;
    private boolean disponible;

    public Vehiculo() {}

   
    public Vehiculo(String placa, Ruta ruta, int capacidadMax, int pasajerosActuales, boolean disponible) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMax = capacidadMax;
        this.pasajerosActuales = pasajerosActuales;
        this.disponible = disponible;
    }


    public Vehiculo(String placa, Ruta ruta, String modelo) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMax = calcularCapacidadPorModelo(modelo);
        this.pasajerosActuales = 0;
        this.disponible = true;
    }

    private int calcularCapacidadPorModelo(String modelo) {
        switch (modelo.toLowerCase()) {
            case "buseta": return 20;
            case "microbus": return 25;
            case "bus": return 40;
            default: return 20;
        }
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public int getPasajerosActuales() {
        return pasajerosActuales;
    }

    public void setPasajerosActuales(int pasajerosActuales) {
        this.pasajerosActuales = pasajerosActuales;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public abstract double calcularTarifa();

    @Override
    public String toString() {
        return "Placa: " + getPlaca() +
               " | Ruta: " + (ruta != null ? ruta.getCiudadOrigen() + " → " + ruta.getCiudadDestino() : "Sin ruta") +
               " | Capacidad: " + getCapacidadMax() +
               " | Pasajeros: " + getPasajerosActuales() +
               " | Disponible: " + isDisponible();
    }
}