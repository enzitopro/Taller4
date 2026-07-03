package GUI;

import dominio.*;
import logica.*;
import sistema.SistemaImpl;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@SuppressWarnings("serial")
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
	// establece objetos de la interfaz grafica, añade los botones, crea las ventanas dentro de la interfaz
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
		panelVista.setPreferredSize(new Dimension(380,0));
		lblImagen = new JLabel("Seleccione una carta", SwingConstants.CENTER);
		lblImagen.setPreferredSize(new Dimension(250,350));
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
	// configura los eventos que ocurren al presionar los botones, como lo que realiza el boton actualizar y ordenar, asi como se muestran las cartas en la interfaz
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
							+ "<b> Tipo:</b> " + cartaSeleccionada.getTipo() + "<br>"
							+ "<b>Rareza:</b> " + cartaSeleccionada.getRareza() + "<br>"
							+ "<b>Poder de Combate:</b> " + String.format("%.2f", poder) + "</html>";
					lblDetalles.setText(detalles);
					cargarImagen(cartaSeleccionada);
				}
			}
		});
	}
	// actualiza la lista en base al orden establecido en la interfaz
	private void actualizarListaUI() {
		modeloLista.clear();
		for (Carta c : SistemaImpl.getInstancia().obtenerColeccion()) {
			modeloLista.addElement(c.getNombreCarta());
		}
	}
	
		// recibe un objeto tipo Carta y busca una imagen que tenga el mismo nombre de esta carta y sea formato png
		private void cargarImagen(Carta carta) {
			String nombre = carta.getNombreCarta();
			String tipo = carta.getTipo();
			
			String ruta = "img/" + nombre + ".png";
			File file = new File(ruta);
			
			if (file.exists()) {
				ImageIcon icon = new ImageIcon(ruta);
				lblImagen.setIcon(new ImageIcon(icon.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH)));
			} else {
				String rutaDefecto = "img/default_" + tipo + ".png"; 
				
				ImageIcon iconDefecto = new ImageIcon(rutaDefecto);
				lblImagen.setIcon(new ImageIcon(iconDefecto.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH)));
			}
			lblImagen.setText("");
		}
}
