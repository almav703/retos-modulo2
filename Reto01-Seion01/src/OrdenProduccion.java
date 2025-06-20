// Clase base abstracta
public abstract class OrdenProduccion {
    protected String codigo;
    protected int cantidad;

    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    // Mostrar datos básicos
    public void mostrarResumen() {
        System.out.printf("Código: %s - Cantidad: %d%n", codigo, cantidad);
    }
}
