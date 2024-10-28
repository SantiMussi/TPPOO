package Clases;
import java.util.Optional;
public class Ronda {
    private Jugador jugador1;
    private Jugador jugador2;
    private Optional<Canto> ultimoCanto;
    private int puntosCantoActual;

    public Ronda(Jugador jugador1, Jugador jugador2){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ultimoCanto = Optional.empty();
        this.puntosCantoActual = 0;
    }

    public boolean realizarCanto(){}
}
