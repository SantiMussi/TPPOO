package Clases;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Partida {
    private Mazo mazo;
    private Jugador jugador1;
    private Jugador jugador2;
    private int rondaActual;

    public Partida(Jugador jugador1, Jugador jugador2){
        mazo = new Mazo();
        mazo.barajar();
        rondaActual = 1;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void repartirCartas(){
            for(int i = 0; i< 3; i++){ // Hasta que no tenga 3 cartas le sigue dando
                jugador1.recibirCarta(mazo.repartirCarta());
                jugador2.recibirCarta(mazo.repartirCarta());
            }
        }

    public void iniciarRonda(){
        System.out.println("Ronda "  + rondaActual);
        repartirCartas();
    }
}
