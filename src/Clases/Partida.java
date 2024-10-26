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

    public void repartirCartas(){
        for(Jugador jugador : jugadores){
            for(int i = 0; i< 3; i++){ // Hasta que no tenga 3 cartas le sigue dando
                jugador.recibirCarta(mazo.repartirCarta());
            }
        }
    }

    public void iniciarRonda(){
        System.out.println("Ronda "  + rondaActual);
        repartirCartas();
    }
}
