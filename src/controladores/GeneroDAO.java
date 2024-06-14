package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelos.Genero;

public class GeneroDAO {
    Connection connection;
    public GeneroDAO(Connection connection) {
        this.connection = connection;
    }   

    public ArrayList<Genero> getGeneros() {
        ArrayList<Genero> generos = new ArrayList<>();
        if (connection != null) {
            String consultaSQL = "SELECT * FROM generos";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("idGenero");
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    generos.add(new Genero(id, nombre, descripcion));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return generos;
    }
}
