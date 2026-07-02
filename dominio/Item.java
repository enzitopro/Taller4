package dominio;

import logica.VisitorPoder;

public class Item extends Carta {
	private int bonificacion;

	public Item(String nombreCarta, int rareza, String tipo, int bonificacion) {
		super(nombreCarta, rareza, tipo);
		this.bonificacion = bonificacion;
	}

	@Override
	public double aceptar(VisitorPoder v) {
		return v.visit(this);
	}

	public int getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}
}
