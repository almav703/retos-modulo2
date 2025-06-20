// Clase base para materiales del curso
public abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // Muestra detalles del material
    public abstract void mostrarDetalle();
}
