import controladores.PaisDAO;
import controladores.UsuarioDAO;

public class Main2 {
    public static void main(String[] args) {
        PaisDAO dao = new PaisDAO();
        //System.out.println(dao.getPaises());
        System.out.println(dao.existe("Colombia"));
        
        UsuarioDAO userDao = new UsuarioDAO();
        System.out.println(userDao.getUsuarios());
    }
}