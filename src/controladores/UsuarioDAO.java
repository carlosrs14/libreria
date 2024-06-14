package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelos.Pais;
import modelos.Persona;
import modelos.Usuario;

public class UsuarioDAO {
    Connection connection;
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
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
                    persona = new Persona(idPersona, cedula, nombrePerona, apellido, fechaNacimiento, pais);
                    usuarios.add(new Usuario(persona,nPrestamos, null));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }
    public ArrayList<Usuario> filtrarPorPais() {
        ArrayList<Usuario> usuariosFiltrados = new ArrayList<>();
        
        return usuariosFiltrados;
    }
    public ArrayList<Usuario> getMasPretadores(int n) {
        ArrayList<Usuario> usuariosFiltrados = new ArrayList<>();
        
        return usuariosFiltrados;
    }
}