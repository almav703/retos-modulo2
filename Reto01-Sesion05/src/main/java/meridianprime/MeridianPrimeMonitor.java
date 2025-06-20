
package meridianprime;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class MeridianPrimeMonitor {

    public static void main(String[] args) throws InterruptedException {
        MeridianPrimeMonitor monitor = new MeridianPrimeMonitor();
        monitor.iniciarMonitoreo();

        // Mantener la app corriendo para ver salida
        Thread.sleep(20000);
    }

    public void iniciarMonitoreo() {
        Flux<Integer> sensoresTrafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> (int)(Math.random() * 101))
                .onBackpressureBuffer()
                .filter(nivel -> nivel > 70)
                .doOnNext(n -> System.out.println("Alerta: Congestión del " + n + "% en avenidas principales"));

        Flux<Integer> contaminacionAire = Flux.interval(Duration.ofMillis(600))
                .map(i -> (int)(Math.random() * 100))
                .filter(pm -> pm > 50)
                .doOnNext(pm -> System.out.println("Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)"));

        Flux<String> accidentesViales = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    int val = (int)(Math.random() * 3);
                    return switch (val) {
                        case 0 -> "Baja";
                        case 1 -> "Media";
                        default -> "Alta";
                    };
                })
                .filter(prioridad -> prioridad.equals("Alta"))
                .doOnNext(p -> System.out.println("Emergencia vial: Accidente con prioridad Alta"));

        Flux<Integer> trenesMaglev = Flux.interval(Duration.ofMillis(700))
                .map(i -> (int)(Math.random() * 11))
                .filter(retraso -> retraso > 5)
                .doOnNext(r -> System.out.println("Tren maglev con retraso crítico: " + r + " minutos"));

        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[(int)(Math.random() * 3)];
                })
                // Detectar rojo 3 veces seguidas
                .buffer(3, 1)
                .filter(buffer -> buffer.size() == 3 && buffer.stream().allMatch(e -> e.equals("Rojo")))
                .map(buffer -> "Semáforo en Rojo detectado 3 veces seguidas")
                .doOnNext(System.out::println);

        // Suscripciones separadas
        sensoresTrafico.subscribe();
        contaminacionAire.subscribe();
        accidentesViales.subscribe();
        trenesMaglev.subscribe();
        semaforos.subscribe();

        // Alerta global por múltiples eventos críticos simultáneos
        Flux.merge(
                        sensoresTrafico.map(e -> 1),
                        contaminacionAire.map(e -> 1),
                        accidentesViales.map(e -> 1),
                        trenesMaglev.map(e -> 1),
                        semaforos.map(e -> 1)
                )
                .bufferTimeout(5, Duration.ofSeconds(1))
                .filter(list -> list.stream().mapToInt(Integer::intValue).sum() >= 3)
                .doOnNext(x -> System.out.println("Alerta global: Múltiples eventos críticos detectados en Meridian Prime"))
                .subscribe();
    }
}
