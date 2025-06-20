import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    // Probabilidad de éxito en porcentaje
    private static final int PROB_PISTA = 80;
    private static final int PROB_CLIMA = 85;
    private static final int PROB_TRAFICO = 90;
    private static final int PROB_PERSONAL = 95;

    private CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            boolean disponible = porcentajeProbabilidad(PROB_PISTA);
            System.out.println("Pista disponible: " + disponible);
            return disponible;
        });
    }

    private CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            boolean favorable = porcentajeProbabilidad(PROB_CLIMA);
            System.out.println("Clima favorable: " + favorable);
            return favorable;
        });
    }

    private CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            boolean despejado = porcentajeProbabilidad(PROB_TRAFICO);
            System.out.println("Tráfico aéreo despejado: " + despejado);
            return despejado;
        });
    }

    private CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            boolean disponible = porcentajeProbabilidad(PROB_PERSONAL);
            System.out.println("Personal disponible: " + disponible);
            return disponible;
        });
    }

    private void sleepRandom() {
        try {
            TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean porcentajeProbabilidad(int porcentaje) {
        return ThreadLocalRandom.current().nextInt(100) < porcentaje;
    }

    public void verificarAterrizaje() {
        System.out.println("Verificando condiciones para aterrizaje...");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> todas = CompletableFuture.allOf(pista, clima, trafico, personal);

        todas.thenRun(() -> {
            try {
                boolean ok = pista.get() && clima.get() && trafico.get() && personal.get();
                if (ok) {
                    System.out.println("Aterrizaje autorizado: todas las condiciones óptimas.");
                } else {
                    System.out.println("Aterrizaje denegado: condiciones no óptimas.");
                }
            } catch (Exception e) {
                System.out.println("Aterrizaje denegado: error en verificación.");
            }
        }).exceptionally(e -> {
            System.out.println("Aterrizaje denegado: error inesperado.");
            return null;
        }).join();
    }

    public static void main(String[] args) {
        new AeropuertoControl().verificarAterrizaje();
    }
}
