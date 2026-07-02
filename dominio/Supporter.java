package dominio;

import visitor.VisitorPoder;

public class Supporter extends Carta {
	private int efectosPorTurno;

	public Supporter(String nombreCarta, int rareza, String tipo, int efectosPorTurno) {
		super(nombreCarta, rareza, tipo);
		this.efectosPorTurno = efectosPorTurno;
	}

	@Override
	public double aceptar(VisitorPoder v) {
		return v.visit(this);
	}

	public int getEfectosPorTurno() {
		return efectosPorTurno;
	}

	public void setEfectosPorTurno(int efectosPorTurno) {
		this.efectosPorTurno = efectosPorTurno;
	}
}
