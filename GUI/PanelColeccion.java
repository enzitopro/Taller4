package GUI;

import dominio.*;
import logica.*;
import sistema.SistemaImpl;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelColeccion extends JPanel {
	private JList<String> listaCartas;
	private DefaultListModel<String> modeloLista;
	private JComboBox<String> comboSort;
	private JLabel lblImagen, lblDetalles;
	
	public PanelColeccion() {
		setLayout(new BorderLayout(10,10));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		JPanel panelSort = new JPanel(new FlowLayout(FlowLayout.LEFT));
		comboSort = new JComboBox<>(new String[]{"Por Rareza", "Por Nombre", "Por Poder"});
		JButton btnOrdenar = new JButton("Ordenar");
		JButton btnActualizar = new JButton("Actualizar lista");
		
		panelSort.add(new JLabel("Ordenar coleccion:"));
		panelSort.add(comboSort);
		panelSort.add(btnOrdenar);
		panelSort.add(btnActualizar);
		
		modeloLista = new DefaultListModel<>();
		listaCartas = new JList<>(modeloLista);
		JScrollPane scrollLista = new JScrollPane(listaCartas);
		
		JPanel panelVista = new JPanel(new BorderLayout(5,5));
		panelVista.setPreferredSize(new Dimension(300,0));
		lblImagen = new JLabel("Seleccione una carta", SwingConstants.CENTER);
		lblImagen.setPreferredSize(new Dimension(200,280));
		lblImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		lblDetalles = new JLabel("<html>Detalles de la carta...</html>");
		lblDetalles.setVerticalAlignment(SwingConstants.TOP);
		panelVista.add(lblImagen, BorderLayout.NORTH);
		panelVista.add(lblDetalles, BorderLayout.CENTER);
		
		add(panelSort, BorderLayout.NORTH);
		add(scrollLista, BorderLayout.CENTER);
		add(panelVista, BorderLayout.EAST);
		
		configurarEventos(btnOrdenar, btnActualizar);
		actualizarListaUI();
	}

	private void configurarEventos(JButton btnOrdenar, JButton btnActualizar) {
		btnActualizar.addActionListener(e -> actualizarListaUI());
		
		btnOrdenar.addActionListener(e -> {
			String seleccion = (String) comboSort.getSelectedItem();
			EstrategiaOrdenamiento estrategia = null;
			
			if (seleccion.equals("Por Rareza")) estrategia = new SortRareza();
			else if (seleccion.equals("Por Nombre")) estrategia = new SortNombre();
			else if (seleccion.equals("Por Poder")) estrategia = new SortPoder();
			
			if (estrategia != null) {
				SistemaImpl.getInstancia().ordenarColeccion(estrategia);
				actualizarListaUI();
			}
		});
		
		listaCartas.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && listaCartas.getSelectedValue() != null) {
				String nombreSeleccionado = listaCartas.getSelectedValue();
				Carta cartaSeleccionada = null;
				
				for (Carta c : SistemaImpl.getInstancia().obtenerColeccion()) {
					if (c.getNombreCarta().equals(nombreSeleccionado)) {
						cartaSeleccionada = c;
						break;
					}
				}
				
				if (cartaSeleccionada != null) {
					CalculadorPoder visitor = new CalculadorPoder();
					double poder = cartaSeleccionada.aceptar(visitor);
					
					String detalles = "<html><h3>" + cartaSeleccionada.getNombreCarta()
							+ "<b>Tipo:</b> " + cartaSeleccionada.getTipo() + "<br>"
							+ "<b>Rareza:</b> " + cartaSeleccionada.getRareza() + "<br>"
							+ "<b>Poder de Combate:</b> " + poder + "</html>";
					lblDetalles.setText(detalles);
					cargarImagen(cartaSeleccionada.getNombreCarta());
				}
			}
		});
	}
	
	private void actualizarListaUI() {
		modeloLista.clear();
		for (Carta c : SistemaImpl.getInstancia().obtenerColeccion()) {
			modeloLista.addElement(c.getNombreCarta());
		}
	}
	
	private void cargarImagen(String nombre) {
		String ruta = "img/" + nombre + ".png";
		File file = new File(ruta);
		if (file.exists()) {
			ImageIcon icon = new ImageIcon(ruta);
			lblImagen.setIcon(new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		} else {
			ImageIcon iconDefecto = new ImageIcon("img/default.png");
			lblImagen.setIcon(new ImageIcon(iconDefecto.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		}
		lblImagen.setText("");
	}
}
