package sistema;

import dominio.Carta;
import logica.CartaFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaCartas {
	private static SistemaCartas instancia;
	private List<Carta> coleccion;

	private SistemaCartas() {
		coleccion = new ArrayList<>();
		cargarCartasDesdeArchivo();
	}

	public static SistemaCartas getInstancia() {
		if (instancia == null) {
			instancia = new SistemaCartas();
		}
		return instancia;
	}

	public List<Carta> getColeccion() {
		return coleccion;
	}

	private void cargarCartasDesdeArchivo() {
		try (BufferedReader br = new BufferedReader(new FileReader("Sobres.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				Carta nuevaCarta = CartaFactory.crearCarta(linea);
				if (nuevaCarta != null) {
					coleccion.add(nuevaCarta);
				}
			}
		} catch (IOException e) {
			System.err.println("Archivo Sobres.txt no encontrado. Se iniciará colección vacía.");
		}
	}

	public void guardarCartasEnArchivo() {

		
	}
}
