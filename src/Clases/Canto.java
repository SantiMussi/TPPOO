package Clases;

public enum Canto {
    ENVIDO(2, 1),
    REAL_ENVIDO(3, 2),
    FALTA_ENVIDO(30, 3),
    TRUCO(2, 4),
    RETRUCO(3, 5),
    VALECUATRO(4, 6);

    private final int puntos;
    private final int prioridad;
    Canto(int puntos, int prioridad) {
        this.puntos = puntos;
        this.prioridad = prioridad;
    }

    public int getPuntos() {
        return puntos;
    }
}
