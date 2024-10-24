package Clases;
import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano; //Cartas que tiene en la mano
    private String nombre; // Nombre del jugador
    private int puntos; // Puntos que tiene el jugador

    public Jugador(String nombre){
        this.nombre = nombre;
        puntos = 0;
        mano = new ArrayList<>();
    }

    public ArrayList<Carta> getMano(){
        return mano;
    }

    public void recibirCarta(Carta carta){
        mano.add(carta);
    }

    public Carta jugarCarta(int indice){
        return mano.remove(indice);
    }

    public void sumarPuntos(int puntos){
        this.puntos += puntos;
    }

    public int getPuntaje(){
        return puntos;
    }

    @Override
    public String toString(){
        return nombre + "\n Puntos: " + puntos;
    }
}
