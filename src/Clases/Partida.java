package Clases;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Partida {
    private Mazo mazo;
    private ArrayList<Jugador> jugadores;
    private int rondaActual;

    public Partida(ArrayList<Jugador> jugadores){
        mazo = new Mazo();
        mazo.barajar();
        this.jugadores = jugadores;
        rondaActual = 1;
    }
}
