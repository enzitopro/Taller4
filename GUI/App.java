package GUI;
//Integrante 1: Elliot Bravo de Rodt - enzitopro

//Integrante 2: Enzo Salvatore Cornieles Medina - justamago
//Link repositorio: https://github.com/enzitopro/Taller3

import javax.swing.*;

@SuppressWarnings("serial")
public class App extends JFrame {
	public App() {
		setTitle("Sutrostian & POOsandon - Coleccion TCG");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JTabbedPane pestanas = new JTabbedPane();

		pestanas.addTab("Administracion", new PanelAdministracion());
		pestanas.addTab("Ver Coleccion", new PanelColeccion());

		add(pestanas);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new App().setVisible(true);
		});
	}
}
