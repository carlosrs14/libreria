package modelos;

import java.sql.Date;

public class Prestamo {
    private int idPrestamo;
    private boolean vigente;
    private Date fechaPrestamo;
    private Usuario usuario;
    private Libro libro;
    
    public Prestamo(int idPrestamo, boolean vigente, Date fechaPrestamo, Usuario usuario, Libro libro) {
        this.idPrestamo = idPrestamo;
        this.vigente = vigente;
        this.fechaPrestamo = fechaPrestamo;
        this.usuario = usuario;
        this.libro = libro;
    }
    public int getIdPrestamo() {
        return idPrestamo;
    }
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    public boolean isVigente() {
        return vigente;
    }
    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }
    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    @Override
    public String toString() {
        return "Prestamo [idPrestamo=" + idPrestamo + ", vigente=" + vigente + ", fechaPrestamo=" + fechaPrestamo
                + ", usuario=" + usuario + ", libro=" + libro + "]";
    }
}
