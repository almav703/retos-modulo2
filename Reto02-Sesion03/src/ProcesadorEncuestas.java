import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Procesa encuestas y genera mensajes de seguimiento
public class ProcesadorEncuestas {

    public static void main(String[] args) {
        // Encuestas por sucursal
        Sucursal centro = new Sucursal("Centro", Arrays.asList(
                new Encuesta("Luis", "El tiempo de espera fue largo", 2),
                new Encuesta("Ana", null, 3),
                new Encuesta("Elena", "La atención fue buena", 5)
        ));

        Sucursal norte = new Sucursal("Norte", Arrays.asList(
                new Encuesta("Miguel", "La limpieza puede mejorar", 3),
                new Encuesta("Rosa", null, 4),
                new Encuesta("Carlos", "Demora en la recepción", 2)
        ));

        List<Sucursal> sucursales = Arrays.asList(centro, norte);

        List<String> mensajes = sucursales.stream()
                .flatMap(sucursal -> sucursal.getEncuestas().stream()
                        .filter(enc -> enc.getCalificacion() <= 3)
                        .map(enc -> enc.getComentario()
                                .map(c -> "Sucursal " + sucursal.getNombre() +
                                        ": Seguimiento a paciente con comentario: \"" + c + "\"")
                        )
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                )
                .collect(Collectors.toList());

        mensajes.forEach(System.out::println);
    }
}
