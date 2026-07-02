package sistema;

import dominio.Carta;
import logica.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public interface Sistema {
    void agregarCarta(Carta carta);
    boolean eliminarCarta(String nombreCarta);
    boolean modificarCarta(String nombreCarta, int nuevoValor1, int nuevoValor2, String nuevoString);
    List<Carta> obtenerColeccion();
    void ordenarColeccion(EstrategiaOrdenamiento estrategia);
    void guardarDatos();
}
