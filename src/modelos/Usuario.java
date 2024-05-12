package modelos;
public class Usuario {
    public Persona personaUsurio;
    public int nPrestamos;
    public String password;
    public Usuario(Persona personaUsurio, int nPrestamos, String password) {
        this.personaUsurio = personaUsurio;
        this.nPrestamos = nPrestamos;
        this.password = password;
    }
    public Persona getPersonaUsurio() {
        return personaUsurio;
    }
    public void setPersonaUsurio(Persona personaUsurio) {
        this.personaUsurio = personaUsurio;
    }
    public int getnPrestamos() {
        return nPrestamos;
    }
    public void setnPrestamos(int nPrestamos) {
        this.nPrestamos = nPrestamos;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Usuario [personaUsurio=" + personaUsurio + ", nPrestamos=" + nPrestamos + ", password=" + password
                + "]";
    }
}
