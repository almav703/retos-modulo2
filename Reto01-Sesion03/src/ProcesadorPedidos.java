import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Procesa los pedidos y genera confirmaciones
public class ProcesadorPedidos {

    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Carlos", "domicilio", "555-1010"),
                new Pedido("Lucía", "local", null),
                new Pedido("Raúl", "domicilio", null),
                new Pedido("Marta", "domicilio", "555-2020"),
                new Pedido("Sandra", "local", "555-3030")
        );

        List<String> mensajes = pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
                .map(Pedido::getTelefono)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(tel -> "Confirmación enviada al número: " + tel)
                .collect(Collectors.toList());

        mensajes.forEach(System.out::println);
    }
}
