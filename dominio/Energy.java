package dominio;

import logica.VisitorPoder;
// clase de cartas de energia
public class Energy extends Carta {
	private String elemento;

	public Energy(String nombreCarta, int rareza, String tipo, String elemento) {
		super(nombreCarta, rareza, tipo);
		this.elemento = elemento;
	}

	@Override
	public double aceptar(VisitorPoder v) {
		return v.visit(this);
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}
}
