import java.util.concurrent.*;

// Ejecuta la simulación de los sistemas
public class MisionEspacial {

    public static void main(String[] args) {
        System.out.println("Inicio de simulación de misión espacial...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            Future<String> nav = executor.submit(new SistemaNavegacion());
            Future<String> vital = executor.submit(new SistemaSoporteVital());
            Future<String> termico = executor.submit(new SistemaControlTermico());
            Future<String> comms = executor.submit(new SistemaComunicaciones());

            System.out.println(comms.get());
            System.out.println(vital.get());
            System.out.println(termico.get());
            System.out.println(nav.get());

            System.out.println("Estado: todos los sistemas operan correctamente.");
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error en sistema: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
