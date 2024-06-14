package controladores;

import java.sql.Connection;
import java.util.ArrayList;

public class PrestamoDAO {
    Connection connection;
    public PrestamoDAO(Connection connection) {
        this.connection = connection;
    } 
    public boolean hacerPrestamo(String nombrePersona, String tituloLibro) {
        
        return false;
    }

    public boolean devolverLibro(String nombrePersona, String tituloLibro) {
        
        return false;
    }

    public ArrayList<PrestamoDAO> getPrestamosPorPersona(String nombre) {
        ArrayList<PrestamoDAO> prestamosFiltrados = new ArrayList<>();
        
        return prestamosFiltrados;
    }
    
    public ArrayList<PrestamoDAO> getPrestamosVigentesPorPersona(String nombre) {
        ArrayList<PrestamoDAO> prestamosFiltrados = new ArrayList<>();
        
        return prestamosFiltrados;
    }
}