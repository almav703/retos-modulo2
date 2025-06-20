public class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false;
    }

    public void marcarRevisado() {
        // Marca el ejercicio como revisado
        this.revisado = true;
    }

    @Override
    public void mostrarDetalle() {
        // Muestra estado del ejercicio
        System.out.println("Ejercicio: " + titulo + ", Autor: " + autor + ", Revisado: " + revisado);
    }
}
