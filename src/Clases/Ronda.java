package Clases;
import java.util.Optional;
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

    public Ronda(Jugador jugador1, Jugador jugador2, Scanner scanner) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ultimoCanto = null;
        this.retrucoCantado = false;
        this.valeCuatroCantado = false;
        this.trucoCantado = false;
        this.scanner = scanner;
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
        System.out.println("7. Pasar");

        int opcion = scanner.nextInt();
        Canto cantoElegido = null;

        switch (opcion) {
            case 1:
                cantoElegido = Canto.ENVIDO;
                break;
            case 2:
                cantoElegido = Canto.REAL_ENVIDO;
                break;
            case 3:
                cantoElegido = Canto.FALTA_ENVIDO;
                break;
            case 4:
                cantoElegido = Canto.TRUCO;
                break;
            case 5:
                cantoElegido = Canto.RETRUCO;
                break;
            case 6:
                cantoElegido = Canto.VALECUATRO;
                break;
            case 7:
                System.out.println(jugador.getNombre() + " pasa.");
                return;
            default:
                System.out.println("Opción no válida. Elige de nuevo.");
                mostrarOpcionesCanto(jugador);
                return;
        }

        manejarCanto(jugador, cantoElegido);
    }

    // Método de validación
    private boolean validarCanto(Canto canto) {
        switch (canto) {
            case ENVIDO:
                if (trucoCantado) {
                    System.out.println("No se puede cantar Envido después del Truco.");
                    return false;
                }
                break;
            case TRUCO:
                if (trucoCantado) {
                    System.out.println("No se puede cantar Truco dos veces.");
                    return false;
                }
                break;
            case RETRUCO:
                if (!trucoCantado) {
                    System.out.println("No se puede cantar Retruco si no se cantó Truco antes.");
                    return false;
                }
                break;
            case VALECUATRO:
                if (!trucoCantado) {
                    System.out.println("No se puede cantar Vale Cuatro si no se cantó Truco antes.");
                    return false;
                }
                break;
        }
        return true; //Si pasa todas las validaciones, es un canto válido
    }

    private void manejarCanto(Jugador jugador, Canto canto) {
        if (!validarCanto(canto)) {
            return; //Termina si el canto no es válido
        }

        //Marca los cantos para futuras validaciones
        if (canto == Canto.TRUCO) {
            trucoCantado = true;
        } else if (canto == Canto.ENVIDO || canto == Canto.REAL_ENVIDO || canto == Canto.FALTA_ENVIDO) {
            envidoCantado = true;
        }

        realizarCanto(jugador, canto);
    }

    public boolean realizarCanto(Jugador jugador, Canto canto){
        if(ultimoCanto != null && canto.getPrioridad() <= ultimoCanto.getPrioridad()){
            System.out.println("Canto no permitido, " + canto + " tiene menor o igual prioridad que " + ultimoCanto);
            return false;
        }
        ultimoCanto = canto;
        puntosCantoActual = canto.getPuntos();
        jugador.cantar(canto);
        return true;
    }

    public void responderCanto(Jugador jugador, boolean acepta){
        if(ultimoCanto == null){
            System.out.println("No hay canto al que responder");
            return;
        }

        if(acepta){
            System.out.println(jugador.getNombre() + " acepto el canto " + ultimoCanto);
        } else{
            System.out.println(jugador.getNombre() + " rechazó el canto " + ultimoCanto);
            obtenerOtroJugador(jugador).sumarPuntos(puntosCantoActual);
        }
    }

    private Jugador obtenerOtroJugador(Jugador jugador){
        return jugador == jugador1 ? jugador2 : jugador1;
    }
}
