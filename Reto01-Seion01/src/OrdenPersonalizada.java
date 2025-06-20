// Orden personalizada con cliente
public class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    @Override
    public void mostrarResumen() {
        System.out.printf("Personalizada - Código: %s - Cantidad: %d - Cliente: %s%n", codigo, cantidad, cliente);
    }
}
