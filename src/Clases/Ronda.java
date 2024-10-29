package Clases;

import java.util.Scanner;

public class Ronda {
    private Jugador jugador1;
    private Jugador jugador2;
    private Canto ultimoCanto;
    private boolean retrucoCantado;
    private boolean valeCuatroCantado;
    private boolean trucoCantado;
    private boolean envidoCantado;
    private int puntosCantoActual;
    private Scanner scanner;

    // Variables para el conteo de chicos y ganador de la ronda
    private int victoriasJugador1;
    private int victoriasJugador2;
    private boolean primeraRondaGanada;

    public Ronda(Jugador jugador1, Jugador jugador2, Scanner scanner) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.scanner = scanner;
        resetearCantos();
        this.victoriasJugador1 = 0;
        this.victoriasJugador2 = 0;
        this.primeraRondaGanada = false;
    }

    private void resetearCantos() {
        this.ultimoCanto = null;
        this.retrucoCantado = false;
        this.valeCuatroCantado = false;
        this.trucoCantado = false;
        this.envidoCantado = false;
    }

    public void mostrarOpcionesCanto(Jugador jugador) {
        System.out.println("\n" + jugador.getNombre() + ", elige tu acción:");
        System.out.println("1. Envido");
        System.out.println("2. Real Envido");
        System.out.println("3. Falta Envido");
        System.out.println("4. Truco");
        System.out.println("5. Retruco");
        System.out.println("6. Vale Cuatro");
        System.out.println("7. Jugar carta");

        int opcion = scanner.nextInt();
        if (opcion >= 1 && opcion <= 6) {
            manejarCanto(jugador, Canto.values()[opcion - 1]);
        } else if (opcion == 7) {
            jugarCarta(jugador);
        } else {
            System.out.println("Opción no válida. Elige de nuevo.");
            mostrarOpcionesCanto(jugador);
        }
    }

    private boolean validarCanto(Canto canto) {
        if (canto == Canto.ENVIDO && trucoCantado) {
            System.out.println("No se puede cantar Envido después del Truco.");
            return false;
        } else if (canto == Canto.TRUCO && trucoCantado) {
            System.out.println("No se puede cantar Truco dos veces.");
            return false;
        } else if (canto == Canto.RETRUCO && !trucoCantado) {
            System.out.println("No se puede cantar Retruco sin Truco.");
            return false;
        } else if (canto == Canto.VALECUATRO && !trucoCantado) {
            System.out.println("No se puede cantar Vale Cuatro sin Truco.");
            return false;
        }
        return true;
    }

    private void manejarCanto(Jugador jugador, Canto canto) {
        if (!validarCanto(canto)) return;

        if (canto == Canto.TRUCO) trucoCantado = true;
        if (canto == Canto.ENVIDO || canto == Canto.REAL_ENVIDO || canto == Canto.FALTA_ENVIDO) envidoCantado = true;

        realizarCanto(jugador, canto);
    }

    public boolean realizarCanto(Jugador jugador, Canto canto) {
        if (ultimoCanto != null && canto.getPrioridad() <= ultimoCanto.getPrioridad()) {
            System.out.println("Canto no permitido, " + canto + " tiene menor o igual prioridad que " + ultimoCanto);
            return false;
        }
        ultimoCanto = canto;
        puntosCantoActual = canto.getPuntos();
        jugador.cantar(canto);
        return true;
    }

    public void responderCanto(Jugador jugador, boolean acepta) {
        if (ultimoCanto == null) {
            System.out.println("No hay canto al que responder.");
            return;
        }

        if (acepta) {
            System.out.println(jugador.getNombre() + " acepta el canto " + ultimoCanto);
        } else {
            System.out.println(jugador.getNombre() + " rechaza el canto " + ultimoCanto);
            obtenerOtroJugador(jugador).sumarPuntos(puntosCantoActual);
        }
    }

    private Jugador obtenerOtroJugador(Jugador jugador) {
        return jugador == jugador1 ? jugador2 : jugador1;
    }

    private void jugarCarta(Jugador jugador) {
        System.out.println("Elige la carta que quieres jugar:");
        jugador.mostrarCartas();
        int indiceCarta = scanner.nextInt();
        jugador.jugarCarta(indiceCarta);
    }

    public void jugarRondasChicas(Jugador jugador1, int indiceCarta1, Jugador jugador2, int indiceCarta2) {
        Carta cartaJugador1 = jugador1.jugarCarta(indiceCarta1);
        Carta cartaJugador2 = jugador2.jugarCarta(indiceCarta2);
        System.out.println(jugador1.getNombre() + " juega " + cartaJugador1);
        System.out.println(jugador2.getNombre() + " juega " + cartaJugador2);

        if (cartaJugador1.compararCon(cartaJugador2)) {
            registrarVictoria(jugador1, "gana este chico.");
        } else if (cartaJugador2.compararCon(cartaJugador1)) {
            registrarVictoria(jugador2, "gana este chico.");
        } else {
            System.out.println("Empate en este chico.");
        }
    }

    private void registrarVictoria(Jugador ganador, String mensaje) {
        if (ganador == jugador1) {
            victoriasJugador1++;
            if (victoriasJugador1 == 1 && victoriasJugador2 == 0) primeraRondaGanada = true;
        } else {
            victoriasJugador2++;
        }
        System.out.println(ganador.getNombre() + " " + mensaje);
    }

    public Jugador obtenerGanadorRonda() {
        if (victoriasJugador1 >= 2) return jugador1;
        if (victoriasJugador2 >= 2) return jugador2;
        if (victoriasJugador1 == 1 && victoriasJugador2 == 1) return primeraRondaGanada ? jugador1 : jugador2;
        return null;
    }

    public void iniciarTurno() {
        Jugador jugadorActual = jugador1;
        Jugador jugadorOtro = jugador2;

        for (int i = 0; i < 2; i++) {
            mostrarOpcionesCanto(jugadorActual);
            Jugador temp = jugadorActual;
            jugadorActual = jugadorOtro;
            jugadorOtro = temp;
        }
    }
}
