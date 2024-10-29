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
        System.out.println("Ronda " + rondaActual);
        Ronda ronda = new Ronda(jugador1, jugador2, scanner); // Pasamos el scanner a Ronda
        ronda.mostrarOpcionesCanto(jugador1);
        ronda.mostrarOpcionesCanto(jugador2);


    }

    private void verificarGanador() {
        if (jugador1.getPuntaje() >= PUNTAJE_OBJETIVO) {
            System.out.println(jugador1.getNombre() + " ha ganado la partida!");
        } else if (jugador2.getPuntaje() >= PUNTAJE_OBJETIVO) {
            System.out.println(jugador2.getNombre() + " ha ganado la partida!");
        }
    }

}
