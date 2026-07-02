package sistema;

import java.util.ArrayList;
import java.util.List;

import dominio.Carta;

public class SistemaImpl {
	private static SistemaImpl instancia;
    private List<Carta> coleccion;
    private final String RUTA_ARCHIVO = "Sobres.txt";

    // 1. PATRÓN SINGLETON: Constructor privado
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
}
