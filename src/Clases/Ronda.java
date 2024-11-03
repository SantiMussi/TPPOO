package Clases;

import java.util.Scanner;

public class Ronda {
    private Jugador jugador1;
    private Jugador jugador2;
    private Canto ultimoCanto;
    private boolean trucoCantado;
    private boolean envidoCantado;
    private int puntosCantoActual;
    private Scanner scanner;
    private Carta cartaJugador1;
    private Carta cartaJugador2;
    private int cartasJugadas;
    private boolean cantoRespondido = true;

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
        this.trucoCantado = false;
        this.envidoCantado = false;
    }

    public void mostrarOpcionesCanto(Jugador jugador) {
        if (!cantoRespondido) {
            responderCantoPendiente(jugador);
            return;
        }

        System.out.println("\n" + jugador.getNombre() + ", elige tu acción:");

        if (!envidoCantado && !trucoCantado) {
            System.out.println("1. Envido");
            System.out.println("2. Real Envido");
            System.out.println("3. Falta Envido");
        }
        if (!trucoCantado) {
            System.out.println("4. Truco");
        } else if (ultimoCanto == Canto.TRUCO) {
            System.out.println("5. Retruco");
        } else if (ultimoCanto == Canto.RETRUCO) {
            System.out.println("6. Vale Cuatro");
        }

        System.out.println("7. Jugar carta");
        System.out.println("8. Ver cartas");

        int opcion = scanner.nextInt();
        Canto cantoElegido;

        switch (opcion) {
            case 1: case 2: case 3: case 4: case 5: case 6:
                cantoElegido = obtenerCanto(opcion);
                manejarCanto(jugador, cantoElegido);
                break;
            case 7:
                jugarCarta(jugador);
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
    private void responderCantoPendiente(Jugador jugador) {
        Jugador otroJugador = (jugador == jugador1) ? jugador2 : jugador1;
        System.out.println(jugador.getNombre() + ", ¿aceptas el canto " + ultimoCanto + "? (true para sí, false para no):");
        boolean acepta = scanner.nextBoolean();
        if (acepta) {
            System.out.println(jugador.getNombre() + " acepta el canto " + ultimoCanto);
            if (ultimoCanto == Canto.ENVIDO || ultimoCanto == Canto.REAL_ENVIDO || ultimoCanto == Canto.FALTA_ENVIDO) {
                determinarGanadorEnvido(ultimoCanto);
            }
        } else {
            System.out.println(jugador.getNombre() + " rechaza el canto " + ultimoCanto);
            otroJugador.sumarPuntos(puntosCantoActual);
        }
        cantoRespondido = true;
        ultimoCanto = null;
    }

    private void manejarCanto(Jugador jugador, Canto canto) {
        System.out.println(jugador.getNombre() + " canta " + canto);
        ultimoCanto = canto;
        cantoRespondido = false;
        if (canto == Canto.ENVIDO || canto == Canto.REAL_ENVIDO || canto == Canto.FALTA_ENVIDO) {
            envidoCantado = true;
        } else if (canto == Canto.TRUCO || canto == Canto.RETRUCO || canto == Canto.VALECUATRO) {
            trucoCantado = true;
        }
    }

    private void jugarCarta(Jugador jugador) {
        jugador.mostrarCartas();
        System.out.println("Elige el índice de la carta a jugar:");
        int indiceCarta = scanner.nextInt();
        Carta cartaJugada = jugador.jugarCarta(indiceCarta);
        manejarCartaJugada(jugador, cartaJugada);
    }

    private void manejarCartaJugada(Jugador jugador, Carta carta) {
        // No debería preguntar por aceptar canto aquí
        if (jugador == jugador1) {
            cartaJugador1 = carta;
        } else {
            cartaJugador2 = carta;
        }
        cartasJugadas++;

        System.out.println(jugador.getNombre() + " jugó: " + carta);

        if (cartasJugadas == 2) {
            determinarGanadorMano();
            cartasJugadas = 0;
            cartaJugador1 = null;
            cartaJugador2 = null;
        }
    }

    private void determinarGanadorMano() {
        if (cartaJugador1 != null && cartaJugador2 != null) {
            boolean ganaJugador1 = cartaJugador1.compararCon(cartaJugador2);

            if (ganaJugador1) {
                System.out.println(jugador1.getNombre() + " gana la mano!");
                victoriasJugador1++;
                if (!primeraRondaGanada && victoriasJugador1 == 1) {
                    primeraRondaGanada = true;
                }
            } else if (cartaJugador2.compararCon(cartaJugador1)) {
                System.out.println(jugador2.getNombre() + " gana la mano!");
                victoriasJugador2++;
                if (!primeraRondaGanada && victoriasJugador2 == 1) {
                    primeraRondaGanada = true;
                }
            } else {
                System.out.println("¡Empate en esta mano!");
            }

            // Verificar si alguien ganó la ronda
            if (victoriasJugador1 >= 2) {
                System.out.println(jugador1.getNombre() + " gana la ronda!");
            } else if (victoriasJugador2 >= 2) {
                System.out.println(jugador2.getNombre() + " gana la ronda!");
            }
        }
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

    public void determinarGanadorEnvido(Canto tipoEnvido) {
        int envidoJugador1 = jugador1.calcularEnvido();
        int envidoJugador2 = jugador2.calcularEnvido();

        System.out.println(jugador1.getNombre() + " tiene " + envidoJugador1 + " puntos de envido.");
        System.out.println(jugador2.getNombre() + " tiene " + envidoJugador2 + " puntos de envido.");

        if (envidoJugador1 > envidoJugador2) {
            jugador1.sumarPuntos(tipoEnvido.getPuntos()); // Suma puntos del envido al jugador 1
            System.out.println(jugador1.getNombre() + " gana el envido.");
        } else if (envidoJugador2 > envidoJugador1) {
            jugador2.sumarPuntos(tipoEnvido.getPuntos()); // Suma puntos del envido al jugador 2
            System.out.println(jugador2.getNombre() + " gana el envido.");
        } else {
            System.out.println("Empate en el envido, no se asignan puntos.");
        }

        ultimoCanto = null;
        cantoRespondido = true;
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
