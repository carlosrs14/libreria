package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import modelos.Persona;

public class PersonaDAO {
    private Connection connection;

    public PersonaDAO(Connection connection) {
        this.connection = connection;
    }

    public Persona getPersona(String cedula) {
        Persona persona = null;
        if (connection != null) {
            String consultaSQL = "SELECT * FROM personas WHERE idPersona = ? ";
            try {
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                statement.setString(0, cedula);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    String apellido = resultado.getString("apellido");
                    Date fechaNacimiento = resultado.getDate("fechaNacimiento");
                    persona = new Persona(0, Integer.parseInt(cedula), nombre, apellido, fechaNacimiento, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return persona;
    }
}
