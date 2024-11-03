package Clases;

import java.util.Scanner;

public class Partida {
    private Mazo mazo;
    private Jugador jugador1;
    private Jugador jugador2;
    private int rondaActual;
    private final int PUNTAJE_OBJETIVO = 4;
    private Scanner scanner;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.mazo = new Mazo();
        this.mazo.barajar();
        this.rondaActual = 1;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.scanner = new Scanner(System.in);
    }

    public void repartirCartas() {
        jugador1.reiniciarMano();
        jugador2.reiniciarMano();
        for (int i = 0; i < 3; i++) {
            jugador1.recibirCarta(mazo.repartirCarta());
            jugador2.recibirCarta(mazo.repartirCarta());
        }
    }

    public void jugarPartida() {
        while (jugador1.getPuntaje() < PUNTAJE_OBJETIVO && jugador2.getPuntaje() < PUNTAJE_OBJETIVO) {
            iniciarRonda();
            rondaActual++;
        }
        verificarGanador();
        scanner.close();
    }

    private void iniciarRonda() {
        System.out.println("\n--- Ronda " + rondaActual + " ---");
        System.out.println("Puntajes: ");
        System.out.println(jugador1.getNombre() + ": " + jugador1.getPuntaje());
        System.out.println(jugador2.getNombre() + ": " + jugador2.getPuntaje());

        repartirCartas();
        Ronda ronda = new Ronda(jugador1, jugador2, scanner);

        jugarCantos(ronda);
        sumarPuntosGanadorRonda(ronda);
    }


    private void jugarCantos(Ronda ronda) {
        boolean turnoJugador1 = true;
        while (!ronda.isRondaTerminada()) {
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            ronda.mostrarOpcionesCanto(jugadorActual);

            if (ronda.getUltimoCanto() != null) {
                Jugador otroJugador = obtenerOtroJugador(jugadorActual);
                System.out.println(otroJugador.getNombre() + ", ¿aceptas el canto? (true para sí, false para no):");
                boolean acepta = obtenerRespuestaBoolean();

                ronda.responderCanto(otroJugador, acepta);

                if (!acepta) {
                    // Sumar puntos al jugador que realizó el canto rechazado
                    // Por simplicidad, vamos a hacer que si se rechaza un canto directamente termina la ronda
                    jugadorActual.sumarPuntos(ronda.getPuntosCantoActual());
                    System.out.println(jugadorActual.getNombre() + " gana la ronda por rechazo de canto.");
                    return;
                }
            }

            turnoJugador1 = !turnoJugador1; // Alterna el turno
        }
    }

    private void sumarPuntosGanadorRonda(Ronda ronda) {
        Jugador ganadorRonda = ronda.obtenerGanadorRonda();
        if (ganadorRonda != null) {
            ganadorRonda.sumarPuntos(2); // Sumar puntos de truco al ganador de la ronda
            System.out.println(ganadorRonda.getNombre() + " gana la ronda!");
        }
    }

    private void verificarGanador() {
        if (jugador1.getPuntaje() >= PUNTAJE_OBJETIVO) {
            System.out.println(jugador1.getNombre() + " ha ganado la partida!");
        } else if (jugador2.getPuntaje() >= PUNTAJE_OBJETIVO) {
            System.out.println(jugador2.getNombre() + " ha ganado la partida!");
        }
    }

    private Jugador obtenerOtroJugador(Jugador jugador) {
        return jugador == jugador1 ? jugador2 : jugador1;
    }

    // Método para obtener respuesta booleana con manejo de errores
    private boolean obtenerRespuestaBoolean() {
        while (true) {
            try {
                return scanner.nextBoolean();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingresa true o false:");
                scanner.next(); // Limpia el scanner
            }
        }
    }

    public String getGanador() {
        if (jugador1.getPuntaje() >= PUNTAJE_OBJETIVO) {
            return jugador1.getNombre();
        }
        return jugador2.getNombre();
    }
}
