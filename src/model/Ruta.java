
package model;

public class Ruta {
    private String codigoRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int tiempoEstimadoMin;

    // Constructor
    public Ruta(String codigoRuta, String ciudadOrigen, String ciudadDestino, 
                double distanciaKm, int tiempoEstimadoMin) {
        this.codigoRuta = codigoRuta;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distanciaKm = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    // Getters y Setters
    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTiempoEstimadoMin() {
        return tiempoEstimadoMin;
    }

    public void setTiempoEstimadoMin(int tiempoEstimadoMin) {
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "codigo='" + codigoRuta + '\'' +
                ", origen='" + ciudadOrigen + '\'' +
                ", destino='" + ciudadDestino + '\'' +
                ", distancia=" + distanciaKm + " km" +
                ", tiempo=" + tiempoEstimadoMin + " min" +
                '}';
    }
}

