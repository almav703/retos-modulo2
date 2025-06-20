import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Ejecuta la simulación hospitalaria
public class SimulacionHospital {

    public static void main(String[] args) {
        System.out.println("Simulación iniciada: acceso al recurso hospitalario...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Profesionales médicos concurrentes
        Runnable p1 = () -> salaCirugia.usar("Dra. Morales");
        Runnable p2 = () -> salaCirugia.usar("Dr. Ramírez");
        Runnable p3 = () -> salaCirugia.usar("Enf. Tamariz");
        Runnable p4 = () -> salaCirugia.usar("Dra. Soto");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(p1);
        executor.submit(p2);
        executor.submit(p3);
        executor.submit(p4);

        executor.shutdown();
    }
}
