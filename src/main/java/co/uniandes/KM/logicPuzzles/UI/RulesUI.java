package co.uniandes.KM.logicPuzzles.UI;

import co.uniandes.KM.logicPuzzles.UI.ControlDemoCombo;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class RulesUI extends JFrame implements ActionListener {
	// contenedores

	/**
	 * 
	 */
	private static final long serialVersionUID = -397721002000936746L;

	private JPanel panelIzquierdo, panelCentral, panelDerecho, panelInferior;

	// textos
	private JLabel titulo;

	// Listas desplegables
	private JComboBox reglasParaCrear;

	public void contruyePanelIzquierdo() {

		panelIzquierdo = new JPanel();

		titulo = new JLabel(
				"<html>Creaci—n de Reglas:<br>Seleccione alguno de los siguientes tipos:</html>");
		reglasParaCrear = new JComboBox();
		reglasParaCrear.addItem("Regla simple");
		reglasParaCrear.addItem("Regla Compleja");

		panelIzquierdo
				.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

		panelIzquierdo.add(titulo);
		panelIzquierdo.add(reglasParaCrear);

	}

	public void contruyePanelDerecho() {
		panelDerecho = new JPanel();

		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
		construirSegundoSetDeDominios();

	}

	public void contruyePanelInferior() {
		panelInferior = new JPanel();

	}

	public void contruyeVentana() {

		JFrame frame = new JFrame();

		panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		construirPrimerSetDeDominios();

		// agregamos los paneles al frame principal

		frame.add(panelCentral, BorderLayout.CENTER);
		frame.add(panelIzquierdo, BorderLayout.WEST);
		frame.add(panelDerecho, BorderLayout.EAST);
		frame.add(panelInferior, BorderLayout.SOUTH);
		// Configuramos el frame
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void construirPrimerSetDeDominios() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */

		labeltituloSeleccionDominios = new JLabel();
		labelItems = new JLabel();
		
		comboDominiosSet1 = new JComboBox(dominio);// creamos el primer combo, y le
											// pasamos un array de cadenas
		comboDominiosSet1.setSelectedIndex(0);// por defecto quiero visualizar el
										// primer item
		itemsComboDominiosSet1 = new JComboBox();// creamo el segundo combo, vacio
		itemsComboDominiosSet1.setEnabled(false);// //por defecto q aparesca desabilidado

		labeltituloSeleccionDominios.setText("Seleccione el primer Dominio");
		panelCentral.add(labeltituloSeleccionDominios);
		labeltituloSeleccionDominios.setBounds(30, 30, 50, 20);
		panelCentral.add(comboDominiosSet1);
		comboDominiosSet1.setBounds(100, 30, 150, 24);
		panelCentral.add(itemsComboDominiosSet1);
		itemsComboDominiosSet1.setBounds(100, 70, 150, 24);
		labelItems.setText("Item");
		panelCentral.add(labelItems);
		labelItems.setBounds(30, 70, 60, 20);

		panelCentral.setBounds(10, 50, 370, 110);
		

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);// le
																		// pasamos
																		// como
																		// argumento
																		// esta
																		// misma
																		// ventana
		comboDominiosSet1.addActionListener(controlDemoCombo);// agregamos escuchas

	}

	public void construirSegundoSetDeDominios() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */
		labeltituloSeleccionDominios = new JLabel();
		labelItems = new JLabel();
		
		itemsComboDominiosSet2 = new JComboBox(dominio);// creamos el primer combo, y le
											// pasamos un array de cadenas
		itemsComboDominiosSet2.setSelectedIndex(0);// por defecto quiero visualizar el
										// primer item
		comboDominiosSet2 = new JComboBox();// creamo el segundo combo, vacio
		comboDominiosSet2.setEnabled(false);// //por defecto q aparesca desabilidado

		labeltituloSeleccionDominios.setText("Seleccione el segundo Dominio");
		panelDerecho.add(labeltituloSeleccionDominios);
		
		panelDerecho.add(itemsComboDominiosSet2);
		
		panelDerecho.add(comboDominiosSet2);
		
		labelItems.setText("Item");
		panelDerecho.add(labelItems);
		

		panelDerecho.setBounds(10, 50, 370, 110);
		

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);// le
																		// pasamos
																		// como
																		// argumento
																		// esta
																		// misma
																		// ventana
		itemsComboDominiosSet2.addActionListener(controlDemoCombo);// agregamos escuchas

	}

	public RulesUI() {
		contruyePanelIzquierdo();
		contruyePanelDerecho();
		contruyePanelInferior();
		contruyeVentana();
	}

	public static void main(String[] inforux) {
		new RulesUI().setVisible(true);
	}

	// Desclaramos las variables autilizar para crear los combos. 
	//Ac‡ toca volverlas din‡micas y que lean de nuestro DimensionDataInput
	String[] dominioNombres = { "DANIEL", "SEBASTIAN", "ANDREA", "JACOBO" };// array
																			// del
																			// primer
																			// combo
	String[] dominioHoras = { "9:00", "10:00", "11:00", "12:00" };// array del
																	// segundo
																	// combo
	String[] dominioEnfermedades = { "DOLOR ESPALDA", "DOLOR CABEZA",
			"DOLOR CADERA", "DOLOR OJOS" };// array del tercer combo
	String[] dominio = { "Selecione Dominio", "Nombres", "Horas",
			"Enfermedades" };// array del primer combo
	public JComboBox comboDominiosSet1;
	public JComboBox itemsComboDominiosSet1;
	public JComboBox itemsComboDominiosSet2;
	public JComboBox comboDominiosSet2;
	public JLabel labeltituloSeleccionDominios;
	private JLabel labelItems;
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}