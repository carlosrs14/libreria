package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.Credenciales;
import modelos.Persona;
import modelos.Usuario;

public class UsuarioDAO {
    Credenciales cred;
    Connection connection;
    public UsuarioDAO(){
        this.cred = new Credenciales();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(cred.url, cred.user, cred.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> aux = new ArrayList<>();
        if(connection != null){
            String consultaSQL = "SELECT * FROM usuarios INNER JOIN personas ON idPersona";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    //int idPersona = resultado.getInt("idPersona");
                    //String nombre = resultado.getString("nombre");
                    //String apellido = resultado.getString("apellido");
                    //int edad = resultado.getInt("edad");
                    //int nprestamos  = resultado.getInt("numeroPrestamos");
                    //aux.add(new Usuario(null, null, null));
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return aux;
    }
    public ArrayList<Usuario> filtrarPorPais(){
        ArrayList<Usuario> aux = new ArrayList<>();
        
        return aux;
    }
    public ArrayList<Usuario> getMasPretadores(int n){
        ArrayList<Usuario> aux = new ArrayList<>();
        
        return aux;
    }
}