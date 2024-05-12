package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.Credenciales;
import modelos.Genero;

public class GeneroDAO {
    Credenciales cred;
    Connection connection;
    public GeneroDAO(){
        this.cred = new Credenciales();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(cred.url, cred.user, cred.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   

    public ArrayList<Genero> getGeneros(){
        ArrayList<Genero> aux = new ArrayList<>();
        if(connection != null){
            String consultaSQL = "SELECT * FROM generos";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("idGenero");
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    aux.add(new Genero(id, nombre, descripcion));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return aux;
    }
}
