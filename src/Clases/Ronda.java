package Clases;
import java.util.Optional;
public class Ronda {
    private Jugador jugador1;
    private Jugador jugador2;
    private Canto ultimoCanto;
    private int puntosCantoActual;

    public Ronda(Jugador jugador1, Jugador jugador2){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ultimoCanto = null;
        this.puntosCantoActual = 0;
    }

    public boolean realizarCanto(Jugador jugador, Canto canto){
        if(ultimoCanto == null && canto.getPrioridad() <= ultimoCanto.getPrioridad()){
            System.out.println("Canto no permitido, " + canto + " tiene menor o igual prioridad que " + ultimoCanto;
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
