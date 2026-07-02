package logica;

import dominio.*;

public class CalculadorPoder implements VisitorPoder {
    @Override
    public double visit(Pokemon p) {
        return ((double) p.getDano() / p.getCantEnergias()) * 100;
    }
    @Override
    public double visit(Item i) {
        return i.getBonificacion() * 20;
    }
    @Override
    public double visit(Supporter s) {
        return s.getEfectosPorTurno() * 50;
    }
    @Override
    public double visit(Energy e) {
        return 1.0; // Por defecto
    }
}
