package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelos.Pais;

public class PaisDAO {
    Connection connection;
    public PaisDAO(Connection connection) {
        this.connection = connection;
    }
    public ArrayList<Pais> getPaises() {
        ArrayList<Pais> paises = new ArrayList<>();
        if (connection != null) {
            String consultaSQL = "SELECT * FROM paises";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("idPais");
                    String nombre = resultado.getString("nombre");
                    paises.add(new Pais(id, nombre));
                }
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }            
        }
        return paises;
    }
    public boolean existe(String pais) {
        String consultaSQL = "SELECT * FROM paises WHERE nombre = ?";
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                statement.setString(1, pais);
                ResultSet restultado = statement.executeQuery();
                while (restultado.next()) {
                    if (restultado.getString("nombre").compareToIgnoreCase(pais) == 0) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}