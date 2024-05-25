package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.Credenciales;
import modelos.Pais;
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
            String consultaSQL = "SELECT * FROM usuarios " +
            "INNER JOIN personas ON(personas.idPersona = usuarios.idUsuario) " +
            "INNER JOIN paises ON(personas.idpaisnacionalidad = paises.idpais)";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                Persona persona;
                Pais pais;
                while (resultado.next()) {
                    int idPersona = resultado.getInt("idPersona");
                    int nPrestamos  = resultado.getInt("numeroPrestamos");
                    int cedula = resultado.getInt("cedula");
                    String nombrePerona = resultado.getString(6);
                    String apellido = resultado.getString("apellido");
                    Date fechaNacimiento = resultado.getDate("fechanacimiento");
                    String nombrePais = resultado.getString(11);
                    int idPais = resultado.getInt("idPersona");                    
                    pais = new Pais(idPais, nombrePais);
                    persona = new Persona(idPersona,cedula, nombrePerona, apellido, fechaNacimiento, pais);
                    aux.add(new Usuario(persona,nPrestamos, null));
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