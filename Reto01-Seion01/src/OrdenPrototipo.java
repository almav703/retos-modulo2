// Orden prototipo con fase
public class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.printf("Prototipo - Código: %s - Cantidad: %d - Fase: %s%n", codigo, cantidad, faseDesarrollo);
    }
}
