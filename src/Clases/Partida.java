package Clases;
import java.util.Scanner;

public class Partida {
    private Mazo mazo;
    private Jugador jugador1;
    private Jugador jugador2;
    private int rondaActual;
    private final int PUNTAJE_OBJETIVO = 30;
    private Scanner scanner;


    public Partida(Jugador jugador1, Jugador jugador2) {
        this.mazo = new Mazo();
        this.mazo.barajar();
        this.rondaActual = 1;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.scanner = new Scanner(System.in); // Inicializamos el scanner
    }

    public void repartirCartas(){
            for(int i = 0; i< 3; i++){ //Hasta que no tenga 3 cartas le sigue dando
                jugador1.recibirCarta(mazo.repartirCarta());
                jugador2.recibirCarta(mazo.repartirCarta());
            }
        }

    public void jugarPartida() {
        while (jugador1.getPuntaje() < PUNTAJE_OBJETIVO && jugador2.getPuntaje() < PUNTAJE_OBJETIVO) {
            jugarRonda();
            rondaActual++;
        }
        verificarGanador();
        scanner.close();
    }

    public void iniciarRonda(){
        System.out.println("Ronda "  + rondaActual);
        repartirCartas();
    }

    private void jugarRonda() {
        System.out.println("\n--- Ronda " + rondaActual + " ---");
        Ronda ronda = new Ronda(jugador1, jugador2, scanner);

        // Los jugadores alternan turnos para cantar y responder
        boolean turnoJugador1 = true;
        while (!ronda.isRondaTerminada()) {
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            ronda.mostrarOpcionesCanto(jugadorActual);

            // Cambia el turno entre jugadores después de cada canto
            turnoJugador1 = !turnoJugador1;
        }

        // Al finalizar los cantos, los jugadores juegan sus cartas
        for (int i = 0; i < 3; i++) { // Cada jugador juega 3 cartas en una ronda
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;

            // Mostrar las cartas del jugador antes de que elija
            jugadorActual.mostrarCartas();

            System.out.println(jugadorActual.getNombre() + ", elige una carta para jugar (0, 1 o 2):");
            int indiceCarta = scanner.nextInt();

            ronda.jugarCarta(jugadorActual, indiceCarta);

            // Cambia el turno para que el otro jugador juegue su carta
            turnoJugador1 = !turnoJugador1;
        }

        // Sumar puntos al ganador de la ronda
        ronda.sumarPuntosGanador();

        // Verificar si alguien ganó la partida
        verificarGanador();
    }

    private void verificarGanador() {
        if (jugador1.getPuntaje() >= PUNTAJE_OBJETIVO) {
            System.out.println(jugador1.getNombre() + " ha ganado la partida!");
        } else if (jugador2.getPuntaje() >= PUNTAJE_OBJETIVO) {
            System.out.println(jugador2.getNombre() + " ha ganado la partida!");
        }
    }

}
