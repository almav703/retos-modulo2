public class Articulo extends MaterialCurso {
    private int palabras;

    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }

    @Override
    public void mostrarDetalle() {
        // Muestra información del artículo
        System.out.println("Artículo: " + titulo + ", Autor: " + autor + ", Palabras: " + palabras);
    }
}
