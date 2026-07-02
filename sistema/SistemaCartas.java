package sistema;

import dominio.Carta;
import factory.CartaFactory;
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

	// Llama a este método cada vez que agregues, borres o modifiques desde la GUI
	public void guardarCartasEnArchivo() {
		// Tu misión: Usar un BufferedWriter para recorrer la lista 'coleccion'
		// y escribirla de vuelta en Sobres.txt respetando los punto y coma.
	}
}
