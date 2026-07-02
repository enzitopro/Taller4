package logica;

import java.util.List;

import dominio.*;

public class SortNombre implements EstrategiaOrdenamiento {
	@Override
	public void ordenar(List<Carta> cartas) {
		cartas.sort((c1, c2) -> c1.getNombreCarta().compareToIgnoreCase(c2.getNombreCarta()));
	}
}