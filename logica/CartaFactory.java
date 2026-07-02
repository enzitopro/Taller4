package logica;

import dominio.*;

public class CartaFactory {
    public static Carta crearCarta(String linea) {
        String[] datos = linea.split(";");
        String nombre = datos[0];
        int rareza = Integer.parseInt(datos[1]);
        String tipo = datos[2];

        try {
            if (tipo.equals("Pokemon")) {
                int daño = Integer.parseInt(datos[3]);
                int cantEnergias = Integer.parseInt(datos[4]);
                return new Pokemon(nombre, rareza, tipo, daño, cantEnergias);
            } else if (tipo.equals("Item")) {
                int bonificacion = Integer.parseInt(datos[3]);
                return new Item(nombre, rareza, tipo, bonificacion);
            } else if (tipo.equals("Supporter")) {
                int efectos = Integer.parseInt(datos[3]);
                return new Supporter(nombre, rareza, tipo, efectos);
            } else if (tipo.equals("Energy")) {
                String elemento = datos[3];
                return new Energy(nombre, rareza, tipo, elemento);
            }
        } catch (Exception e) {
            System.err.println("Error parseando la carta: " + nombre);
        }
        return null;
    }
}
