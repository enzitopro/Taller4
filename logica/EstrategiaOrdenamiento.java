package logica;

import java.util.List;

import dominio.Carta;
// interfaz con metodo para ordenar una lista de cartas
public abstract interface EstrategiaOrdenamiento {
    void ordenar(List<Carta> cartas);
}
