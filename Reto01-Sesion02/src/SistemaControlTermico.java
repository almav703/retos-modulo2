import java.util.concurrent.Callable;

// Simula el sistema térmico
public class SistemaControlTermico implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1300);
        return "Control térmico: temperatura nominal (21.8°C).";
    }
}
