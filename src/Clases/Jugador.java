package Clases;
import java.util.ArrayList;

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
    
    public boolean responder(boolean acepta){
        this.aceptaCanto = acepta;
        System.out.println(nombre + (acepta ? " acepta el canto" : " rechaza el canto"));
        return acepta;
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


}
