import Clases.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear los jugadores
        System.out.print("Ingrese el nombre del Jugador 1: ");
        String nombreJugador1 = scanner.nextLine();
        Jugador jugador1 = new Jugador(nombreJugador1);

        System.out.print("Ingrese el nombre del Jugador 2: ");
        String nombreJugador2 = scanner.nextLine();
        Jugador jugador2 = new Jugador(nombreJugador2);

        // Iniciar la partida
        System.out.println("\nIniciando partida de Truco entre " + nombreJugador1 + " y " + nombreJugador2 + "...");
        Partida partida = new Partida(jugador1, jugador2);
        partida.jugarPartida();


        String resultadoFinal = "El ganador de la partida es: " + partida.getGanador();
        // Guardar el resultado final en un archivo
        guardarResultado(resultadoFinal);

        scanner.close();
    }

    private static void guardarResultado(String resultado) {
        try (FileWriter fw = new FileWriter("resultados_partida.txt", true);
             PrintWriter out = new PrintWriter(fw)) {
            out.println(resultado);
            System.out.println("Resultado guardado: " + resultado);
        } catch (IOException e) {
            System.err.println("Error al guardar el resultado: " + e.getMessage());
        }
    }
}
