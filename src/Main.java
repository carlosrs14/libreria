import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.Credenciales;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Credenciales credenciales = new Credenciales();
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/canciones", credenciales.user, credenciales.password);
            if(connection != null){
                
                String consultaSQL = "SELECT * FROM canciones";
                PreparedStatement statement = connection.prepareStatement(consultaSQL);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {

                    int id = resultado.getInt("identificador");
                    
                    String titulo = resultado.getString("título");
                    
                    System.out.println("ID: " + id + " Título: " + titulo);
                
                }
            }
        }catch(SQLException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
