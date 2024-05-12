package modelos;

public class Pais {
    public int idPais;
    public String nombre;
    public Pais(int idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }
    public int getIdPais() {
        return idPais;
    }
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Pais [idPais=" + idPais + ", nombre=" + nombre + "]";
    }
}