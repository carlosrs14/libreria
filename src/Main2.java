import controladores.PaisDAO;

public class Main2 {
    public static void main(String[] args) {
        PaisDAO dao = new PaisDAO();
        System.out.println(dao.getPaises());
        System.out.println(dao.existe("colombia"));
    }
}