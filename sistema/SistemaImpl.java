package sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dominio.*;
import logica.CartaFactory;
import logica.EstrategiaOrdenamiento;

public class SistemaImpl implements Sistema {
	private static SistemaImpl instancia;
    private List<Carta> coleccion;
    private final String RUTA_ARCHIVO = "Sobres.txt";

    private SistemaImpl() {
        coleccion = new ArrayList<>();
        cargarDatos();
    }
    
    public static SistemaImpl getInstancia() {
    	if (instancia == null) {
    		instancia = new SistemaImpl();
    	}
    	return instancia;
    }
    private void cargarDatos() {
    	try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
    		String linea;
    		while ((linea = br.readLine()) != null) {
    			Carta nuevaCarta = CartaFactory.crearCarta(linea);
    			if (nuevaCarta != null) {
    				coleccion.add(nuevaCarta);
    			}
    		}
    	} catch (IOException e) {
    		System.out.println("No se encontró el archivo de datos");
    	}
    }

	@Override
	public void agregarCarta(Carta carta) {
		coleccion.add(carta);
		guardarDatos();
		
	}

	@Override
	public boolean eliminarCarta(String nombreCarta) {
		for (Carta c : coleccion) {
			if (c.getNombreCarta().equalsIgnoreCase(nombreCarta)) {
				coleccion.remove(c);
				guardarDatos();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean modificarCarta(String nombreCarta, int nuevoValor1, int nuevoValor2, String nuevoString) {
		for (Carta c : coleccion) {
			if (c instanceof Pokemon) {
				((Pokemon) c).setDano(nuevoValor1);
				((Pokemon) c).setCantEnergias(nuevoValor2);
			} else if (c instanceof Item) {
				((Item) c).setBonificacion(nuevoValor1);
			} else if (c instanceof Supporter) {
				((Supporter) c).setEfectosPorTurno(nuevoValor1);
			} else if (c instanceof Energy) {
				((Energy) c).setElemento(nuevoString);
			}
			guardarDatos();
			return true;
		}
		return false;
	}

	@Override
	public List<Carta> obtenerColeccion() {
		return coleccion;
	}

	@Override
	public void ordenarColeccion(EstrategiaOrdenamiento estrategia) {
		estrategia.ordenar(coleccion);
		
	}

	@Override
	public void guardarDatos() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
			for (Carta c : coleccion) {
				StringBuilder linea = new StringBuilder();
				linea.append(c.getNombreCarta()).append(";").append(c.getRareza()).append(";").append(c.getTipo());
				if (c instanceof Pokemon) {
					Pokemon p = (Pokemon) c;
					linea.append(";").append(p.getDano()).append(";").append(p.getCantEnergias());
				} else if (c instanceof Item) {
					Item i = (Item) c;
					linea.append(";").append(i.getBonificacion());
				} else if (c instanceof Supporter) {
					Supporter s = (Supporter) c;
					linea.append(";").append(s.getEfectosPorTurno());
				} else if (c instanceof Energy) {
					Energy e = (Energy) c;
					linea.append(";").append(e.getElemento());
				}
				
				bw.write(linea.toString());
				bw.newLine();
			} 
		} catch (IOException e) {
			System.out.println("Error al guardar los datos");
		}
		
	}

}
