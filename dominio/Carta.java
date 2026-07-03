package dominio;

import logica.VisitorPoder;
// clase padre de las cartas
public abstract class Carta {
	protected String nombreCarta;
	protected int rareza;
	protected String tipo;

	public Carta(String nombreCarta, int rareza, String tipo) {
		this.nombreCarta = nombreCarta;
		this.rareza = rareza;
		this.tipo = tipo;
	}

	public abstract double aceptar(VisitorPoder v);

	public String getNombreCarta() {
		return nombreCarta;
	}

	public int getRareza() {
		return rareza;
	}

	public String getTipo() {
		return tipo;
	}
}
