import java.util.concurrent.Callable;

// Simula el sistema de comunicaciones
public class SistemaComunicaciones implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(800);
        return "Comunicaciones: señal estable con centro de control.";
    }
}
