package Clases;
import java.util.ArrayList;
import java.util.Collections;


public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo(){
        cartas = new ArrayList<>();
        inicializarMazo();
    }

    public void inicializarMazo(){
        String[] palos = {"Oro", "Copa", "Espada", "Basto"};
        int[] numeros = {1,2,3,4,5,6,7,10,11,12};
        for (String palo : palos) {
            for (int numero : numeros) {
                Carta carta = new Carta(palo, numero);
                cartas.add(carta);
            }
        }
    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

}
