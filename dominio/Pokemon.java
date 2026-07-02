package dominio;

import visitor.VisitorPoder;

public class Pokemon extends Carta {
	private int dano;
	private int cantEnergias;

	public Pokemon(String nombreCarta, int rareza, String tipo, int dano, int cantEnergias) {
		super(nombreCarta, rareza, tipo);
		this.dano = dano;
		this.cantEnergias = cantEnergias;
	}

	@Override
	public double aceptar(VisitorPoder v) {
		return v.visit(this);
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getCantEnergias() {
		return cantEnergias;
	}

	public void setCantEnergias(int cantEnergias) {
		this.cantEnergias = cantEnergias;
	}
}
