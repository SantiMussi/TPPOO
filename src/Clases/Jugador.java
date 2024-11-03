package Clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Jugador {
    private ArrayList<Carta> mano; //Cartas que tiene en la mano
    private String nombre; // Nombre del jugador
    private int puntos; // Puntos que tiene el jugador
    private boolean aceptaCanto;
    

    public Jugador(String nombre){
        this.nombre = nombre;
        puntos = 0;
        mano = new ArrayList<>();
    }

    public void cantar(Canto canto){
        System.out.println(nombre +  " canta " + canto);
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

    public String getNombre(){
        return nombre;
    }

    public int getPuntaje(){
        return puntos;
    }

    @Override
    public String toString(){
        return nombre + "\n Puntos: " + puntos;
    }

    public void mostrarCartas() {
        System.out.println("Cartas en mano de " + nombre + ":");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ": " + mano.get(i)); // Muestra el índice y la carta
        }
    }


    public int calcularEnvido() {
        int maxPuntaje = 0;
        Map<String, Integer> puntajesPorPalo = new HashMap<>();

        for (Carta carta : mano) {
            int valorCarta = carta.getNumero();

            // Asigna el valor de la carta para el envido (10, 11 y 12 valen 0)
            int valorEnvido = (valorCarta >= 10) ? 0 : valorCarta;

            // Obtiene el palo de la carta y suma su valor al puntaje correspondiente en ese palo
            String palo = carta.getPalo();
            puntajesPorPalo.put(palo, puntajesPorPalo.getOrDefault(palo, 0) + valorEnvido);
        }

        // Calcula el puntaje de envido, sumando 20 solo si hay dos cartas del mismo palo
        for (int puntaje : puntajesPorPalo.values()) {
            if (puntaje > maxPuntaje) {
                maxPuntaje = puntaje;
            }
        }

        // Si hay dos cartas del mismo palo, suma 20 puntos
        if (puntajesPorPalo.size() < mano.size()) { // Menos palos que cartas, indica que hay cartas del mismo palo
            maxPuntaje += 20;
        }

        return maxPuntaje;
    }
}
