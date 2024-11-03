package Clases;
import java.util.Map;
import java.util.HashMap;

public class Carta {
    private String palo;
    private int numero;

    //Mapa de jerarquía
    private static final Map<String, Integer> jerarquia = new HashMap<>();

    //Solo se ejecuta una vez, cuando se carga la clase, no depende de la instanciacion
    static {
        jerarquia.put("1 Espada", 14);
        jerarquia.put("1 Basto", 13);
        jerarquia.put("7 Espada", 12);
        jerarquia.put("7 Oro", 11);
        jerarquia.put("3", 10);
        jerarquia.put("2", 9);
        jerarquia.put("1", 8);
        jerarquia.put("12", 7);
        jerarquia.put("11", 6);
        jerarquia.put("10", 5);
        jerarquia.put("7", 4);
        jerarquia.put("6", 3);
        jerarquia.put("5", 2);
        jerarquia.put("4", 1);
    }

    //Método constructor
    public Carta(String palo, int numero){
        this.palo = palo;
        this.numero = numero;
    }

    //Método para obtener la clave de jerarquía
    private String getClave() {
        if ((numero == 1 && (palo.equals("Espada") || palo.equals("Basto"))) ||
                (numero == 7 && (palo.equals("Espada") || palo.equals("Oro")))) {
            return numero + " " + palo;
        }
        return String.valueOf(numero);
    }

    public String getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    // Método para comparar cartas
    public boolean compararCon(Carta otraCarta) {
        int valorActual = jerarquia.getOrDefault(this.getClave(), 0);
        int valorOtra = jerarquia.getOrDefault(otraCarta.getClave(), 0);
        return valorActual > valorOtra;
    }



    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}
