import java.util.concurrent.Callable;

// Simula el sistema de soporte vital
public class SistemaSoporteVital implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(950);
        return "Soporte vital: condiciones internas estables.";
    }
}
