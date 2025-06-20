import java.util.concurrent.locks.ReentrantLock;

// Recurso médico compartido
public class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    // Controla el acceso exclusivo al recurso
    public void usar(String profesional) {
        lock.lock();
        try {
            System.out.printf("%s ha ingresado a %s%n", profesional, nombre);
            Thread.sleep(1000); // Simula uso del recurso
            System.out.printf("%s ha salido de %s%n", profesional, nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
