package GUI;

import dominio.*;
import logica.*;
import sistema.SistemaImpl;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PanelAdministracion extends JPanel {
	private JTextField txtNombre, txtRareza, txtAtr1, txtAtr2;
	private JComboBox<String> comboTipo;
	private JLabel lblAtr1, lblAtr2;
	
	public PanelAdministracion() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		incializarComponentes();
	}

	private void incializarComponentes() {
		JPanel panelFormulario = new JPanel(new GridLayout(6,2,10,10));
		
		panelFormulario.add(new JLabel("Nombre de la Carta:"));
		txtNombre = new JTextField();
		panelFormulario.add(txtNombre);
		
		panelFormulario.add(new JLabel("Rareza (Numero);"));
		txtRareza = new JTextField();
		panelFormulario.add(txtRareza);
		
		panelFormulario.add(new JLabel("Tipo de Carta:"));
		comboTipo = new JComboBox<>(new String[]{"Pokemon", "Item","Supporter","Energy"});
		panelFormulario.add(comboTipo);
		
		lblAtr1 = new JLabel("Daño:");
		txtAtr1 = new JTextField();
		panelFormulario.add(lblAtr1);
		panelFormulario.add(txtAtr1);
		
		lblAtr2 = new JLabel("Cant. Energías:");
		txtAtr2 = new JTextField();
		panelFormulario.add(lblAtr2);
		panelFormulario.add(txtAtr2);
		
		comboTipo.addActionListener(e -> actualizarFormulario());
		
		JPanel panelBotones = new JPanel(new FlowLayout());
		JButton btnAgregar = new JButton("Agregar Carta");
		JButton btnModificar = new JButton("Modificar Carta");
		JButton btnEliminar = new JButton("Eliminar Carta");
		
		panelBotones.add(btnAgregar);
		panelBotones.add(btnModificar);
		panelBotones.add(btnEliminar);
		
		add(panelFormulario, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		configurarEventos(btnAgregar, btnModificar, btnEliminar);
		}

	private void actualizarFormulario() {
		String tipo = (String) comboTipo.getSelectedItem();
		txtAtr1.setText(""); txtAtr2.setText("");
		txtAtr2.setVisible(false); lblAtr2.setVisible(false);
		
		if (tipo.equals("Pokemon")) {
			lblAtr1.setText("Daño:");
			lblAtr2.setText("Cant. Energías:");
			txtAtr2.setVisible(true); lblAtr2.setVisible(true);
		} else if (tipo.equals("Item")) {
			lblAtr1.setText("Bonificacion:");
		} else if (tipo.equals("Supporter")) {
			lblAtr1.setText("Efectos por Turno");
		} else if (tipo.equals("Energy")) {
			lblAtr1.setText("Elemento:");
		}
	}
	
	private void configurarEventos(JButton btnAg, JButton btnMod, JButton btnEl) {
		btnAg.addActionListener(e -> {
			try {
				String nombre = txtNombre.getText();
				String rareza = txtRareza.getText();
				String tipo = (String) comboTipo.getSelectedItem();
				String atr1 = txtAtr1.getText();
				String atr2 = txtAtr2.isVisible() ? ";" + txtAtr2.getText() : "";
				
				String lineaFalsa = nombre + ";"+rareza+";"+tipo+";"+atr1+";"+atr2;
				Carta nueva = CartaFactory.crearCarta(lineaFalsa);
				System.out.println("Enviando " + lineaFalsa);
				
				if (nueva != null) {
					SistemaImpl.getInstancia().agregarCarta(nueva);
					JOptionPane.showMessageDialog(this, "Carta agregada exitosamente");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
			}
		});
		
		btnMod.addActionListener(e -> {
			try {
				String nombre = txtNombre.getText();
				String tipo = (String) comboTipo.getSelectedItem();
				int val1 = tipo.equals("Energy") ? 0 : Integer.parseInt(txtAtr1.getText());
				int val2 = tipo.equals("Pokemon") ? Integer.parseInt(txtAtr2.getText()) : 0;
				String valStr = tipo.equals("Energy") ? txtAtr1.getText() : "";
				
				boolean exito = SistemaImpl.getInstancia().modificarCarta(nombre, val1, val2, valStr);
				if (exito) JOptionPane.showMessageDialog(this, "Carta Modificada");
				else JOptionPane.showMessageDialog(this, "Carta no encontrada");
 			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Verifique los valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnEl.addActionListener(e -> {
			String nombre = txtNombre.getText();
			if (SistemaImpl.getInstancia().eliminarCarta(nombre)) {
				JOptionPane.showMessageDialog(this, "Carta Eliminada");
			} else {
				JOptionPane.showMessageDialog(this, "Carta no encontrada");
			}
		});
	}
	
}
