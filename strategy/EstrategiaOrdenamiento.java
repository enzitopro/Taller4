package strategy;

import java.util.List;

import dominio.Carta;

public abstract interface EstrategiaOrdenamiento {
    void ordenar(List<Carta> cartas);
}
