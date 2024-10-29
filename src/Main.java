import Clases.*;
import java.util.Scanner;

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

        scanner.close();
    }
}
