package modelos;

public class Libro {
    private int idLibro;
    private String titulo;
    private String sinopsis;
    private int stock;
    private Genero genero;
    private Autor autor;
    private Editorial editorial;

    public Libro(int idLibro, String titulo, String sinopsis, int stock, Genero genero, Autor autor, Editorial editorial) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.stock = stock;
        this.genero = genero;
        this.autor = autor;
        this.editorial = editorial;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
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
                + ", autor=" + autor.getpersonaAutor().getNombre() + ", editorial=" + editorial.getNombre() + "]";
    }
}