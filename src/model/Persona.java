package model;

public abstract class Persona {
    protected String nombre;
    protected String cedula;
    protected String edad;
    protected String sexo;
    protected String telefono;
    
    public Persona(String nombre, String cedula, int edad, String sexo, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = String.valueOf(edad);
        this.sexo = sexo;
        this.telefono = telefono;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getEdad() {
        return edad;
    }
    
    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public abstract void verEstadisticas();
}