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
		
		panelVista.add(lblImagen, BorderLayout.NORTH);
		panelVista.add(lblDetalles, BorderLayout.CENTER);
		
		add(panelSort, BorderLayout.NORTH);
		add(scrollLista, BorderLayout.CENTER);
		add(panelVista, BorderLayout.EAST);
		
		configurarEventos(btnOrdenar, btnActualizar);
		actualizarListaUI();
	}
}
