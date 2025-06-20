import java.util.Optional;

// Representa un pedido en la pizzería
public class Pedido {
    private final String cliente;
    private final String tipoEntrega;
    private final String telefono;

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    // Devuelve teléfono como Optional
    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }
}
