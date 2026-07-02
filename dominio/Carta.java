package dominio;

public abstract class Carta {
    protected String nombreCarta;
    protected int rareza;
    protected String tipo;

    public Carta(String nombreCarta, int rareza, String tipo) {
        this.nombreCarta = nombreCarta;
        this.rareza = rareza;
        this.tipo = tipo;
    }