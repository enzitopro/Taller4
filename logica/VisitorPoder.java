package logica;

import dominio.*;

public interface VisitorPoder {
    double visit(Pokemon p);
    double visit(Item i);
    double visit(Supporter s);
    double visit(Energy e);
}
