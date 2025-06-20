package org.example.uci;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Random;

public class UciMonitor {

    public static void main(String[] args) throws InterruptedException {
        // Ejecuta y fusiona los flujos de los pacientes
        Flux.merge(crearFlujoPaciente(1), crearFlujoPaciente(2), crearFlujoPaciente(3))
                .delayElements(Duration.ofSeconds(1)) // Aplica backpressure
                .subscribe(System.out::println);

        // Ejecuta la simulación durante 30 segundos
        Thread.sleep(30000);
    }

    // Simula los eventos de un paciente
    private static Flux<String> crearFlujoPaciente(int pacienteId) {
        Random random = new Random();

        return Flux.generate(sink -> sink.next(generarEventoCritico(pacienteId, random)))
                .cast(String.class)
                .delayElements(Duration.ofMillis(300)) // Frecuencia de emisión
                .filter(evento -> !evento.isEmpty()); // Filtra eventos no críticos
    }

    // Genera eventos con condiciones críticas
    private static String generarEventoCritico(int id, Random random) {
        int fc = 40 + random.nextInt(101); // 40-140 bpm
        int sistolica = 80 + random.nextInt(81); // 80-160 mmHg
        int diastolica = 50 + random.nextInt(51); // 50-100 mmHg
        int spo2 = 85 + random.nextInt(16); // 85-100%

        if (fc < 50 || fc > 120)
            return "Paciente " + id + " - FC critica: " + fc + " bpm";

        if (sistolica < 90 || sistolica > 140 || diastolica < 60 || diastolica > 90)
            return "Paciente " + id + " - PA critica: " + sistolica + "/" + diastolica + " mmHg";

        if (spo2 < 90)
            return "Paciente " + id + " - SpO2 baja: " + spo2 + "%";

        return ""; // No crítico
    }
}
