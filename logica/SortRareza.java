package logica;

import java.util.List;

import dominio.Carta;
// ordena las cartas en base a su rareza (mayor a menor)
public class SortRareza implements EstrategiaOrdenamiento {
	@Override
	public void ordenar(List<Carta> cartas) {
        cartas.sort((c1, c2) -> Integer.compare(c2.getRareza(), c1.getRareza()));
    }
}
