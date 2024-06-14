package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelos.Autor;
import modelos.Editorial;
import modelos.Genero;
import modelos.Libro;
import modelos.Persona;

public class LibroDAO {
    Connection connection;
    public LibroDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Libro> filtrarPorAutor(String autorFiltro) {
        ArrayList<Libro> librosFiltrados = new ArrayList<>();
        if (connection != null) {
            try {
                String consultaSQL = "SELECT * FROM librosPorAutor(?);";
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                statement.setString(1, autorFiltro);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {                
                    String titulo = resultado.getString("titulo");
                    String sinopsis = resultado.getString("sinopsis");
                    int stock = resultado.getInt("stock");
                    String generoNombre = resultado.getString("genero");
                    String editorialNombre = resultado.getString("editorial");
                    String nombrePersona = resultado.getString("nombre");
                    String apellidoPersona = resultado.getString("apellido");

                    Genero genero = new Genero(0, generoNombre, null);
                    Editorial editorial = new Editorial(0, editorialNombre, null);
                    Persona persona = new Persona(0, 0, nombrePersona, apellidoPersona, null, null);
                    Autor autor = new Autor(persona, null);                    
                    Libro libro = new Libro(0,titulo , sinopsis, stock, genero, autor, editorial);
                    
                    librosFiltrados.add(libro);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return librosFiltrados;
    }

    public ArrayList<Libro> filtrarPorGenero(String generoFiltro) {
        ArrayList<Libro> librosFiltrados = new ArrayList<>();
        if (connection != null) {
            try {
                String consultaSQL = "SELECT * FROM librosPorGenero(?);";
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                statement.setString(1, generoFiltro);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    String titulo = resultado.getString("titulo");
                    int stock = resultado.getInt("stock");
                    String libroSinopsis = resultado.getString("sinopsis");                  
                    String editorialNombre = resultado.getString("editorial");
                    String personaNombre = resultado.getString("nombre");
                    String personaApellido = resultado.getString("apellido");
                    
                    Editorial editorial = new Editorial(0, editorialNombre, null);
                    Persona persona = new Persona(0, 0, personaNombre, personaApellido, null, null);
                    Autor autor = new Autor(persona, null);
                    Libro libro = new Libro(0,titulo, libroSinopsis, stock, null, autor, editorial);
                    
                    librosFiltrados.add(libro);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return librosFiltrados;
    }

    public int cantidadEnStock(String titulo) {
        if (connection != null) {
            String consultaSQL = "SELECT stock FROM libros WHERE titulo = ?";     
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                statement.setString(1, titulo);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    return resultado.getInt("stock");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
}
