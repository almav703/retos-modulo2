import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MovilidadApp {

    // Simula cálculo de ruta con latencia 2-3 seg
    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Centro -> Norte";
        });
    }

    // Simula estimación de tarifa con latencia 1-2 seg
    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1 + ThreadLocalRandom.current().nextInt(2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 85.75;
        });
    }

    // Combina ambas operaciones y muestra resultado
    public void procesarViaje() {
        System.out.println("Calculando ruta...");
        System.out.println("Estimando tarifa...");

        calcularRuta()
                .thenCombine(estimarTarifa(), (ruta, tarifa) ->
                        String.format("Ruta calculada: %s | Tarifa estimada: $%.2f", ruta, tarifa)
                )
                .exceptionally(e -> "Error en cálculo: " + e.getMessage())
                .thenAccept(System.out::println)
                .join();
    }

    public static void main(String[] args) {
        new MovilidadApp().procesarViaje();
    }
}
