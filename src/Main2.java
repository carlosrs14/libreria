import java.sql.Connection;
import java.sql.DriverManager;

import controladores.GeneroDAO;
import controladores.LibroDAO;
import controladores.PaisDAO;
import datos.Credenciales;

public class Main2 {
    public static void main(String[] args) {
        Connection connection;
        Credenciales cred = new Credenciales();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(cred.url, cred.user, cred.password);
 
            GeneroDAO generoDAO = new GeneroDAO(connection);
            System.out.println(generoDAO.getGeneros());

            LibroDAO libroDao = new LibroDAO(connection);
            System.out.println(libroDao.cantidadEnStock("it"));
            PaisDAO dao = new PaisDAO(connection);
            //System.out.println(dao.getPaises());
            System.out.println(dao.existe("Colombia"));
            
            //UsuarioDAO userDao = new UsuarioDAO(connection);
           // System.out.println(userDao.getUsuarios());
            
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}