package modelos;

import java.sql.Date;

public class Persona {
    public int idPersona;
    public int cedula;
    public String nombre;
    public String apellido;
    public Date fechaNacimiento;
    public Pais paisNacionalidad;
    public Persona(int idPersona, int cedula, String nombre, String apellido, Date fechaNacimiento,
            Pais paisNacionalidad) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.paisNacionalidad = paisNacionalidad;
    }
    public int getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    public int getCedula() {
        return cedula;
    }
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Pais getPaisNacionalidad() {
        return paisNacionalidad;
    }
    public void setPaisNacionalidad(Pais paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }
    @Override
    public String toString() {
        return "Persona [idPersona=" + idPersona + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido="
                + apellido + ", fechaNacimiento=" + fechaNacimiento + ", paisNacionalidad=" + paisNacionalidad + "]";
    }
}
