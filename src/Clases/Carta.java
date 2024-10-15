package Clases;

public class Carta {
    private String palo;
    private int numero;

    //Método constructor
    public Carta(String palo, int numero){
        this.palo = palo;
        this.numero = numero;
    }

    //Getters y setters

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
