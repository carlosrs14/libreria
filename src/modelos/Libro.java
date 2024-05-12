package modelos;

public class Libro {
    public int idLibro;
    public String titulo;
    public String sinopsis;
    public Genero genero;
    public Autor autor;
    public Editorial editorial;
    public Libro(int idLibro, String titulo, String sinopsis, Genero genero, Autor autor, Editorial editorial) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.autor = autor;
        this.editorial = editorial;
    }
    public int getIdLibro() {
        return idLibro;
    }
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public Editorial getEditorial() {
        return editorial;
    }
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    @Override
    public String toString() {
        return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", sinopsis=" + sinopsis + ", genero=" + genero
                + ", autor=" + autor + ", editorial=" + editorial + "]";
    }
}