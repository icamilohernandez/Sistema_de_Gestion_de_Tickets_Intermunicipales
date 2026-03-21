package model;

public abstract class Vehiculo {

    private String placa;
    private Ruta ruta;
    private int capacidadMax;
    private int pasajerosActuales;
    private boolean disponible;

    public Vehiculo() {}

    public Vehiculo(String placa, Ruta ruta, int capacidadMax, int pasajerosActuales, boolean disponible) {
        this.placa             = placa;
        this.ruta              = ruta;
        this.capacidadMax      = capacidadMax;
        this.pasajerosActuales = pasajerosActuales;
        this.disponible        = disponible;
    }

    public String getPlaca()              { return placa; }
    public void   setPlaca(String placa)  { this.placa = placa; }

    public Ruta  getRuta()           { return ruta; }
    public void  setRuta(Ruta ruta)  { this.ruta = ruta; }

    public int  getCapacidadMax()             { return capacidadMax; }
    public void setCapacidadMax(int c)        { this.capacidadMax = c; }

    public int  getPasajerosActuales()        { return pasajerosActuales; }
    public void setPasajerosActuales(int p)   { this.pasajerosActuales = p; }

    public boolean isDisponible()             { return disponible; }
    public void    setDisponible(boolean d)   { this.disponible = d; }

    public abstract double calcularTarifa();

    @Override
    public String toString() {
        return "Placa: " + placa +
               " | Ruta: " + (ruta != null ? ruta.getCiudadOrigen() + " -> " + ruta.getCiudadDestino() : "Sin ruta") +
               " | Capacidad: " + capacidadMax +
               " | Pasajeros: " + pasajerosActuales +
               " | Disponible: " + disponible;
    }
}