package modelos;

public class Editorial {
    private int idEditorial;
    private String nombre;
    private String descripcion;
    public Editorial(int idEditorial, String nombre, String descripcion) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public int getIdEditorial() {
        return idEditorial;
    }
    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return "Editorial [idEditorial=" + idEditorial + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }
    
}