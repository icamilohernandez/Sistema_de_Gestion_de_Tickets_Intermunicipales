package model;

public class Conductor extends Persona {
    private String Nlicencia;
    private String categoria;

    public Conductor(String cedula, String nombre, String sexo, String Nlicencia, String categoria, String telefono, int edad) {
        super(cedula, nombre, edad, sexo, telefono);
        this.Nlicencia = Nlicencia;
        this.categoria = categoria;
    }

    public String getNlicencia() {
        return Nlicencia;
    }

    public void setNlicencia(String Nlicencia) {
        this.Nlicencia = Nlicencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {	
        this.categoria = categoria;
    }

    public boolean validarLicencia() {
        return Nlicencia != null && !Nlicencia.trim().isEmpty();
    }

    @Override
    public void verEstadisticas() {
        System.out.println("Estadísticas del conductor:");
        System.out.println("- Cédula: " + getCedula());
        System.out.println("- Nombre: " + getNombre());
        System.out.println("- Edad: " + getEdad());
        System.out.println("- Sexo: " + getSexo());
        System.out.println("- Teléfono: " + getTelefono());
        System.out.println("- Número de licencia: " + getNlicencia());
        System.out.println("- Categoría: " + getCategoria());

    }
}