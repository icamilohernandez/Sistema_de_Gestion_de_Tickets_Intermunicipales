package model;

public class Ruta {
    private String codigoRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int tiempoEstimadoMin;

    public Ruta(String codigoRuta, String ciudadOrigen, String ciudadDestino,
                double distanciaKm, int tiempoEstimadoMin) {
        this.codigoRuta       = codigoRuta;
        this.ciudadOrigen     = ciudadOrigen;
        this.ciudadDestino    = ciudadDestino;
        this.distanciaKm      = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    public String getCodigoRuta()       { return codigoRuta; }
    public String getCiudadOrigen()     { return ciudadOrigen; }
    public String getCiudadDestino()    { return ciudadDestino; }
    public double getDistanciaKm()      { return distanciaKm; }
    public int    getTiempoEstimadoMin(){ return tiempoEstimadoMin; }

    public void setCodigoRuta(String codigoRuta)             { this.codigoRuta = codigoRuta; }
    public void setCiudadOrigen(String ciudadOrigen)         { this.ciudadOrigen = ciudadOrigen; }
    public void setCiudadDestino(String ciudadDestino)       { this.ciudadDestino = ciudadDestino; }
    public void setDistanciaKm(double distanciaKm)           { this.distanciaKm = distanciaKm; }
    public void setTiempoEstimadoMin(int tiempoEstimadoMin)  { this.tiempoEstimadoMin = tiempoEstimadoMin; }

    @Override
    public String toString() {
        return codigoRuta + " | " + ciudadOrigen + " -> " + ciudadDestino +
               " | " + distanciaKm + " km | " + tiempoEstimadoMin + " min";
    }
}