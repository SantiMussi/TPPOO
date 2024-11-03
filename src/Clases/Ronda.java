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
    private int cantRondaChica = 0;

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

        if (!envidoCantado || trucoCantado) { // Permitir cantos de truco tras el envido
            System.out.println("1. Envido");
            System.out.println("2. Real Envido");
            System.out.println("3. Falta Envido");
            System.out.println("4. Truco");
            System.out.println("5. Retruco");
            System.out.println("6. Vale Cuatro");
        }

        System.out.println("7. Jugar carta");
        System.out.println("8. Ver cartas");

        int opcion = scanner.nextInt();
        Canto cantoElegido = null;

        switch (opcion) {
            case 1: case 2: case 3: case 4: case 5: case 6:
                cantoElegido = obtenerCanto(opcion);
                manejarCanto(jugador, cantoElegido);
                break;
            case 7:
                jugador.mostrarCartas();
                int indiceCarta = scanner.nextInt();
                jugador.jugarCarta(indiceCarta);
                break;
            case 8:
                jugador.mostrarCartas();
                mostrarOpcionesCanto(jugador);
                break;
            default:
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
        } else if (canto == Canto.ENVIDO && cantRondaChica != 0) {
            System.out.println("No se puede cantar envido despues de las primeras cartas jugadas");
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

    public void realizarCanto(Jugador jugador, Canto canto) {
        if (ultimoCanto != null && canto.getPrioridad() <= ultimoCanto.getPrioridad()) {
            System.out.println("Canto no permitido, " + canto + " tiene menor o igual prioridad que " + ultimoCanto);
            return;
        }
        ultimoCanto = canto;
        puntosCantoActual = canto.getPuntos();
        jugador.cantar(canto);
    }

    public void responderCanto(Jugador jugador, boolean acepta) {
        if (acepta) {
            System.out.println(jugador.getNombre() + " acepta el canto " + ultimoCanto);
            if (ultimoCanto == Canto.ENVIDO || ultimoCanto == Canto.REAL_ENVIDO || ultimoCanto == Canto.FALTA_ENVIDO) {
                determinarGanadorEnvido(ultimoCanto);
                resetearCantos(); // Restablece el estado de canto tras el envido
            }
        } else {
            System.out.println(jugador.getNombre() + " rechaza el canto " + ultimoCanto);
            obtenerOtroJugador(jugador).sumarPuntos(puntosCantoActual);
        }
    }


    private Jugador obtenerOtroJugador(Jugador jugador) {
        return jugador == jugador1 ? jugador2 : jugador1;
    }

    public Jugador obtenerGanadorRonda() {
        if (victoriasJugador1 >= 2) return jugador1;
        if (victoriasJugador2 >= 2) return jugador2;
        if (victoriasJugador1 == 1 && victoriasJugador2 == 1) return primeraRondaGanada ? jugador1 : jugador2;
        return null;
    }

    public Jugador determinarGanadorEnvido(Canto tipoEnvido) {
        int envidoJugador1 = jugador1.calcularEnvido();
        int envidoJugador2 = jugador2.calcularEnvido();

        System.out.println(jugador1.getNombre() + " tiene " + envidoJugador1 + " puntos de envido.");
        System.out.println(jugador2.getNombre() + " tiene " + envidoJugador2 + " puntos de envido.");

        if (envidoJugador1 > envidoJugador2) {
            jugador1.sumarPuntos(tipoEnvido.getPuntos()); // Suma puntos del envido al jugador 1
            System.out.println(jugador1.getNombre() + " gana el envido.");
            return jugador1;
        } else if (envidoJugador2 > envidoJugador1) {
            jugador2.sumarPuntos(tipoEnvido.getPuntos()); // Suma puntos del envido al jugador 2
            System.out.println(jugador2.getNombre() + " gana el envido.");
            return jugador2;
        } else {
            System.out.println("Empate en el envido, no se asignan puntos.");
            return null; // Empate
        }
    }


    public int getPuntosCantoActual() {
        return puntosCantoActual;
    }

    public Canto getUltimoCanto() {
        return ultimoCanto;
    }

    public boolean isRondaTerminada() {
        return victoriasJugador1 >= 2 || victoriasJugador2 >= 2;
    }

    private Canto obtenerCanto(int opcion) {
        switch (opcion) {
            case 1: return Canto.ENVIDO;
            case 2: return Canto.REAL_ENVIDO;
            case 3: return Canto.FALTA_ENVIDO;
            case 4: return Canto.TRUCO;
            case 5: return Canto.RETRUCO;
            case 6: return Canto.VALECUATRO;
            default: return null;
        }
    }
}
