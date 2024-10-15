package Clases;
import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano; //Cartas que tiene en la mano
    private String nombre; // Nombre del jugador
    private int puntos; // Puntos que tiene el jugador

    public Jugador(String nombre){
        this.nombre = nombre;
        this.puntos = 0;
    }
}
