package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelos.*;

public class PrestamoDAO {
    Connection connection;
    public PrestamoDAO(Connection connection) {
        this.connection = connection;
    } 
    public ArrayList<Prestamo> getPrestamos() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        if (connection != null) {
            String consultaSQL = "SELECT * FROM todosLosPrestamos";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                Persona persona;
                Libro libro;
                Usuario usuario;
                
                while (resultado.next()) {
                    int idPrestamo = resultado.getInt("idPrestamo");
                    String cedula = resultado.getString("cedula");
                    Date fechaPrestamo = resultado.getDate("fechaprestamo");
                    String nombre = resultado.getString("nombre");
                    String apellido = resultado.getString("apellido");
                    String titulo = resultado.getString("titulo");
                    boolean vigencia = resultado.getBoolean("vigencia");
                    libro = new Libro(0, titulo, "", 0, null, null, null);
                    persona = new Persona(0, Integer.parseInt(cedula), nombre, apellido, null, null);
                    usuario = new Usuario(persona, 0, "");
                    prestamos.add(new Prestamo(idPrestamo, vigencia, fechaPrestamo, usuario, libro));
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return prestamos;
    }

    public boolean hacerPrestamo(String nombrePersona, String tituloLibro) {
        
        return false;
    }

    public boolean devolverLibro(String nombrePersona, String tituloLibro) {
        
        return false;
    }

    public ArrayList<Prestamo> getPrestamosPorPersona(String nombre) {
        ArrayList<Prestamo> prestamosFiltrados = new ArrayList<>();
        
        return prestamosFiltrados;
    }
    
    public ArrayList<Prestamo> getPrestamosVigentesPorPersona(String nombre) {
        ArrayList<Prestamo> prestamosFiltrados = new ArrayList<>();
        
        return prestamosFiltrados;
    }
}