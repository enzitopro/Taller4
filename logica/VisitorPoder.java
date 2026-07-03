package logica;

import dominio.*;
// interfaz de visitor para realizar calculos de estadisticas en cada clase hija de carta
public interface VisitorPoder {
    double visit(Pokemon p);
    double visit(Item i);
    double visit(Supporter s);
    double visit(Energy e);
}
