import java.util.List;

public class PlantaIndustrial {

    // Mostrar lista de órdenes (cualquier subtipo)
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("Órdenes registradas:");
        for (OrdenProduccion o : lista) {
            o.mostrarResumen();
        }
        System.out.println();
    }

    // Procesar órdenes personalizadas, agregar costo
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costo) {
        System.out.println("Procesando órdenes personalizadas con costo adicional...");
        for (Object o : lista) {
            if (o instanceof OrdenPersonalizada) {
                OrdenPersonalizada op = (OrdenPersonalizada) o;
                System.out.printf("Orden %s ajustada con costo adicional de $%d%n", op.codigo, costo);
            }
        }
        System.out.println();
    }

    // Resumen total por tipo
    public static void resumenTotal(List<? extends OrdenProduccion> masa,
                                    List<? extends OrdenProduccion> personalizada,
                                    List<? extends OrdenProduccion> prototipo) {
        System.out.println("Resumen total de órdenes:");
        System.out.printf("Producción en masa: %d%n", masa.size());
        System.out.printf("Personalizadas: %d%n", personalizada.size());
        System.out.printf("Prototipos: %d%n", prototipo.size());
    }

    public static void main(String[] args) {
        List<OrdenMasa> masa = List.of(
                new OrdenMasa("M101", 1200),
                new OrdenMasa("M102", 800)
        );

        List<OrdenPersonalizada> personalizada = List.of(
                new OrdenPersonalizada("P201", 300, "ClienteA"),
                new OrdenPersonalizada("P202", 450, "ClienteB")
        );

        List<OrdenPrototipo> prototipo = List.of(
                new OrdenPrototipo("T301", 15, "Concepto"),
                new OrdenPrototipo("T302", 7, "Validación")
        );

        mostrarOrdenes(masa);
        mostrarOrdenes(personalizada);
        mostrarOrdenes(prototipo);

        procesarPersonalizadas(new java.util.ArrayList<>(personalizada), 350);

        resumenTotal(masa, personalizada, prototipo);
    }
}
