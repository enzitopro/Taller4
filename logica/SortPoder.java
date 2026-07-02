package logica;

import java.util.List;

import dominio.Carta;

public class SortPoder implements EstrategiaOrdenamiento {

	@Override
	public void ordenar(List<Carta> cartas) {
        CalculadorPoder calculador = new CalculadorPoder();
        cartas.sort((c1, c2) -> Double.compare(c2.aceptar(calculador), c1.aceptar(calculador)));
    }

}
