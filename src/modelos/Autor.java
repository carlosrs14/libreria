package modelos;

public class Autor {
    public Persona personaAutor;
    public Genero generoPrincipal;
    public Autor(Persona personaAutor, Genero generoPrincipal) {
        this.personaAutor = personaAutor;
        this.generoPrincipal = generoPrincipal;
    }    
    public Persona getpersonaAutor() {
        return personaAutor;
    }
    public void setpersonaAutor(Persona personaAutor) {
        this.personaAutor = personaAutor;
    }
    public Genero getgeneroPrincipal() {
        return generoPrincipal;
    }
    public void setgeneroPrincipal(Genero generoPrincipal) {
        this.generoPrincipal = generoPrincipal;
    }
    @Override
    public String toString() {
        return "Autor [personaAutor=" + personaAutor + ", generoPrincipal=" + generoPrincipal + "]";
    }
}