// ---------------------------
// Archivo: UciMonitor.java
// ---------------------------

package meridianprime;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class UciMonitor {

    public static void main(String[] args) throws InterruptedException {
        UciMonitor monitor = new UciMonitor();
        monitor.iniciarMonitoreo();

        // Mantener la app corriendo para ver salida
        Thread.sleep(20000);
    }

    private final Random random = new Random();

    public void iniciarMonitoreo() {
        Flux<String> paciente1 = generarDatosPaciente(1);
        Flux<String> paciente2 = generarDatosPaciente(2);
        Flux<String> paciente3 = generarDatosPaciente(3);

        Flux.merge(paciente1, paciente2, paciente3)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);
    }

    private Flux<String> generarDatosPaciente(int id) {
        return Flux.interval(Duration.ofMillis(300))
                .map(tick -> {
                    int fc = 40 + random.nextInt(100);    // 40-139 bpm
                    int paSist = 50 + random.nextInt(100); // 50-149 mmHg
                    int paDiast = 40 + random.nextInt(80); // 40-119 mmHg
                    int spo2 = 80 + random.nextInt(21);   // 80-100%

                    StringBuilder alertas = new StringBuilder();

                    if (fc < 50 || fc > 120) {
                        alertas.append("Paciente ").append(id).append(" - FC crítica: ").append(fc).append(" bpm\n");
                    }
                    if (paSist < 90 || paSist > 140 || paDiast < 60 || paDiast > 90) {
                        alertas.append("Paciente ").append(id).append(" - PA crítica: ")
                                .append(paSist).append("/").append(paDiast).append(" mmHg\n");
                    }
                    if (spo2 < 90) {
                        alertas.append("Paciente ").append(id).append(" - SpO2 baja: ").append(spo2).append("%\n");
                    }

                    return alertas.toString().trim();
                })
                .filter(s -> !s.isEmpty());
    }
}
