import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import controladores.LibroDAO;
import controladores.PersonaDAO;
import datos.Credenciales;
import modelos.Libro;
import modelos.Persona;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Credenciales credenciales = new Credenciales();
        Scanner scanner = new Scanner(System.in);
        String menu =   "Menú de opciones\n" +
                        "1. ver todos los prestamos\n" +
                        "2. hacer un prestamo\n" +
                        "3. devolver un libro\n" +
                        "4. información de un usuario\n" +
                        "5. disponibilidad de un libro\n" +
                        "6. consultar libros por género\n" +
                        "7. consultar libros por autor\n" +
                        "0. salir\n" +
                        "ingrese su opción: ";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/libreria", credenciales.user, credenciales.password);
            if(connection != null){
                LibroDAO libroDAO = new LibroDAO(connection);
                PersonaDAO  personaDAO = new PersonaDAO(connection);
                int op = 0;
                System.out.println("Bienvenido a la libreria");
                do { 
                    System.out.print(menu);
                    op = Integer.parseInt(scanner.nextLine());

                    switch (op) {
                        case 1:
                        
                        break;
                        case 2:
                        
                        break;
                        case 3:

                        break; 
                        case 4:
                            System.out.print("Ingrese el documento del usuario: ");
                            String cedula = scanner.nextLine();
                            Persona persona = personaDAO.getPersona(cedula);
                            System.out.println(persona);
                        break; 
                        case 5:
                            System.out.print("Ingrese el nombre del libro: ");
                            String libroNombre = scanner.nextLine();
                            int stock = libroDAO.cantidadEnStock(libroNombre);
                            System.out.println("La cantidad en stock de " + libroNombre + " es " + stock);
                        break; 
                        case 6:
                            System.out.print("ingrese el nombre del género que desea consultar: ");
                            String generoNombre = scanner.nextLine();
                            ArrayList<Libro> librosPorGenero = libroDAO.filtrarPorGenero(generoNombre);
                            for (Libro libro : librosPorGenero) {
                                System.out.println(libro.toString());
                            } 
                        break; 
                        case 7:
                            System.out.print("ingrese el nombre del autor que desea consultar: ");
                            String autorNombre = scanner.nextLine();
                            ArrayList<Libro> librosPorAutor = libroDAO.filtrarPorAutor(autorNombre);
                            for (Libro libro : librosPorAutor) {
                                System.out.println(libro.toString());
                            } 
                        break;
                        case 0:
                            System.out.println("Saliendo del programa...");
                            scanner.close();
                            System.exit(0);
                        break;
                        default:
                            System.out.println("La opción ingresada es invalida");
                        break;
                    }                    
                } while(op != 0);
            }
        }catch(SQLException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
