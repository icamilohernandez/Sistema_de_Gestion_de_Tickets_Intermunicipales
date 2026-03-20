package model;

public class Conductor extends Persona {
    private String numeroLicencia;
    private String categoria;

    public Conductor(String cedula, String nombre, String sexo, String numeroLicencia, 
                     String categoria, String telefono, int edad) {
        super(cedula, nombre, edad, sexo, telefono);
        this.numeroLicencia = numeroLicencia;
        this.categoria = categoria;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean validarLicencia() {
        return numeroLicencia != null && !numeroLicencia.trim().isEmpty();
    }

    @Override
    public void verEstadisticas() {
        System.out.println("=== CONDUCTOR ===");
        System.out.println("Cédula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + getEdad());
        System.out.println("Sexo     : " + getSexo());
        System.out.println("Teléfono : " + getTelefono());
        System.out.println("Licencia : " + numeroLicencia);
        System.out.println("Categoría: " + categoria);
    }
}