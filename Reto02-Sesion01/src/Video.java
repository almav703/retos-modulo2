public class Video extends MaterialCurso {
    private int duracion;

    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public void mostrarDetalle() {
        // Muestra información del video
        System.out.println("Video: " + titulo + ", Autor: " + autor + ", Duración: " + duracion + " minutos");
    }
}
