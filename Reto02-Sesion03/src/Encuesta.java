import java.util.Optional;

// Representa una encuesta de paciente
public class Encuesta {
    private final String paciente;
    private final String comentario;
    private final int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    // Devuelve comentario como Optional
    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }
}
