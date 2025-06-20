import java.util.*;
import java.util.function.Predicate;

public class PlataformaEducativa {

    // Muestra todos los materiales
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("Lista de materiales del curso:");
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
        System.out.println();
    }

    // Suma la duración total de los videos
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video v : lista) {
            total += v.getDuracion();
        }
        System.out.println("Total duración videos: " + total + " minutos\n");
    }

    // Marca los ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio e) {
                e.marcarRevisado();
                System.out.println("Ejercicio '" + e.titulo + "' revisado.");
            }
        }
        System.out.println();
    }

    // Filtra materiales por autor
    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        System.out.println("Materiales del autor seleccionado:");
        for (MaterialCurso m : lista) {
            if (filtro.test(m)) {
                m.mostrarDetalle();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Creo la lista con materiales variados
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Fundamentos de Java", "Alma", 12));
        materiales.add(new Video("Java Avanzado", "Roberto", 25));
        materiales.add(new Articulo("Introducción a JVM", "Ana", 1500));
        materiales.add(new Articulo("Buenas prácticas Java", "Javier", 900));
        materiales.add(new Ejercicio("Bucles y Condicionales", "Javier"));
        materiales.add(new Ejercicio("Manejo de Excepciones", "Alma"));

        mostrarMateriales(materiales);

        contarDuracionVideos(
                materiales.stream().filter(m -> m instanceof Video).map(v -> (Video) v).toList()
        );

        marcarEjerciciosRevisados(materiales);

        filtrarPorAutor(materiales, m -> m.autor.equals("Alma"));
    }
}
