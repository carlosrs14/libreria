package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.Credenciales;
import modelos.Pais;

public class PaisDAO {
    Credenciales cred;
    Connection connection;
    public PaisDAO(){
        this.cred = new Credenciales();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(cred.url,cred.user,cred.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Pais> getPaises(){
        ArrayList<Pais> aux = new ArrayList<>();
        if (connection != null) {
            String consultaSQL = "SELECT * FROM paises";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("idPais");
                    String nombre = resultado.getString("nombre");
                    aux.add(new Pais(id, nombre));
                }
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }            
        }
        
        return aux;
    }
    public boolean existe(String pais){
        String consultaSQL = "SELECT * FROM paises WHERE nombre = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(consultaSQL);
            statement.setString(1, pais);
            ResultSet restultado = statement.executeQuery();
            while (restultado.next()) {
                System.out.println(restultado.getString("nombre"));
                if (restultado.getString("nombre").compareToIgnoreCase(pais) == 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}