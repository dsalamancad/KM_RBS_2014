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
	JComboBox reglasParaCrear;

	public void contruyePanelIzquierdo() {

		panelIzquierdo = new JPanel();

		titulo = new JLabel(
				"<html>Creaci—n de Reglas:<br>Seleccione alguno de los siguientes tipos:</html>");
		reglasParaCrear = new JComboBox();
		reglasParaCrear.addItem("Seleccione el tipo de regla...");
		reglasParaCrear.addItem("Regla simple");
		reglasParaCrear.addItem("Regla Compleja");

		panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

		panelIzquierdo.add(titulo);
		panelIzquierdo.add(reglasParaCrear);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);// le
																		// pasamos
																		// como
																		// argumento
																		// esta
																		// misma
																		// ventana
		reglasParaCrear.addActionListener(controlDemoCombo);// agregamos
															// escuchas

	}

	public void contruyePanelDerecho() {
		panelDerecho = new JPanel();

		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
		
		
		construirSegundoSetDeDominios();
		
		// este es para construir lista de dominios en la regla completa
				// en este panel va el segundo y cuarto set
				construirSegundoSetDeDominiosReglaCompleja();
				construirCuartoSetDeDominiosReglaCompleja();

	}

	public void contruyePanelInferior() {
		panelInferior = new JPanel();

		itemPrimerDominiReglaSimple = new JLabel();
		itemSegundoDominiReglaSimple = new JLabel();

		itemPrimerDominiReglaSimple.setText("Item1");
		panelInferior.add(itemPrimerDominiReglaSimple);

		itemSegundoDominiReglaSimple.setText("Item2");
		panelInferior.add(itemSegundoDominiReglaSimple);
		itemSegundoDominiReglaSimple.setBounds(100, 70, 60, 20);

	}

	public void contruyeVentana() {

		JFrame frame = new JFrame();

		panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		// este es para construir lista de dominios en la regla simple
		construirPrimerSetDeDominios();

		// este es para construir lista de dominios en la regla completa
		// en este panel va el primer y tercer set
		construirPrimerSetDeDominiosReglaCompleja();
		construirSetOperadores();
		construirTercerSetDeDominiosReglaCompleja();

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

		comboDominiosSet1 = new JComboBox(dominio);// creamos el primer combo, y
													// le
		// pasamos un array de cadenas
		comboDominiosSet1.setSelectedIndex(0);// por defecto quiero visualizar
												// el
		// primer item
		itemsComboDominiosSet1 = new JComboBox();// creamo el segundo combo,
													// vacio
		itemsComboDominiosSet1.setEnabled(false);// //por defecto q aparesca
													// desabilidado

		labeltituloSeleccionDominios.setText("Seleccione el primer Dominio");
		panelCentral.add(labeltituloSeleccionDominios);
		labeltituloSeleccionDominios.setBounds(30, 30, 50, 20);
		panelCentral.add(comboDominiosSet1);
		comboDominiosSet1.setBounds(100, 30, 150, 24);
		panelCentral.add(itemsComboDominiosSet1);
		itemsComboDominiosSet1.setBounds(100, 70, 150, 24);

		panelCentral.setBounds(10, 50, 370, 110);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);// le
																		// pasamos
																		// como
																		// argumento
																		// esta
																		// misma
																		// ventana
		comboDominiosSet1.addActionListener(controlDemoCombo);// agregamos
																// escuchas

	}

	public void construirSegundoSetDeDominios() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */
		labeltituloSeleccionDominios = new JLabel();

		itemsComboDominiosSet2 = new JComboBox(dominio);// creamos el primer
														// combo, y le
		// pasamos un array de cadenas
		itemsComboDominiosSet2.setSelectedIndex(0);// por defecto quiero
													// visualizar el
		// primer item
		comboDominiosSet2 = new JComboBox();// creamo el segundo combo, vacio
		comboDominiosSet2.setEnabled(false);// //por defecto q aparesca
											// desabilidado

		labeltituloSeleccionDominios.setText("Seleccione el segundo Dominio");
		panelDerecho.add(labeltituloSeleccionDominios);

		panelDerecho.add(itemsComboDominiosSet2);

		panelDerecho.add(comboDominiosSet2);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);// le
																		// pasamos
																		// como
																		// argumento
																		// esta
																		// misma
																		// ventana
		itemsComboDominiosSet2.addActionListener(controlDemoCombo);// agregamos
																	// escuchas

	}

	public void construirPrimerSetDeDominiosReglaCompleja() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */

		labeltituloSeleccionDominios = new JLabel();

		comboDominiosSet1ReglaCompleja = new JComboBox(dominio);// creamos el primer combo,y le pasamos un array de cadenas
		comboDominiosSet1ReglaCompleja.setSelectedIndex(0);// por defecto quiero visualizar el primer item
		itemscomboDominiosSet1ReglaCompleja = new JComboBox();// creamo el segundo combo, vacio
		itemscomboDominiosSet1ReglaCompleja.setEnabled(false);// //por defecto que aparezca deshabilitado

		labeltituloSeleccionDominios.setText("Seleccione el primer Dominio de regla compleja");
		panelCentral.add(labeltituloSeleccionDominios);
		labeltituloSeleccionDominios.setBounds(30, 30, 50, 20);
		panelCentral.add(comboDominiosSet1ReglaCompleja);
		comboDominiosSet1ReglaCompleja.setBounds(100, 30, 150, 24);
		panelCentral.add(itemscomboDominiosSet1ReglaCompleja);
		itemscomboDominiosSet1ReglaCompleja.setBounds(100, 70, 150, 24);

		panelCentral.setBounds(10, 50, 370, 110);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);
		comboDominiosSet1ReglaCompleja.addActionListener(controlDemoCombo);// agregamos escuchas

	}
	
	public void construirSegundoSetDeDominiosReglaCompleja() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */

		labeltituloSeleccionDominios = new JLabel();

		comboDominiosSet2ReglaCompleja = new JComboBox(dominio);// creamos el Segundo combo,y le pasamos un array de cadenas
		comboDominiosSet2ReglaCompleja.setSelectedIndex(0);// por defecto quiero visualizar el Segundo item
		itemscomboDominiosSet2ReglaCompleja = new JComboBox();// creamo el segundo combo, vacio
		itemscomboDominiosSet2ReglaCompleja.setEnabled(false);// //por defecto que aparezca deshabilitado

		labeltituloSeleccionDominios.setText("Seleccione el Segundo Dominio de regla compleja");
		panelDerecho.add(labeltituloSeleccionDominios);
		labeltituloSeleccionDominios.setBounds(30, 30, 50, 20);
		panelDerecho.add(comboDominiosSet2ReglaCompleja);
		comboDominiosSet2ReglaCompleja.setBounds(100, 30, 150, 24);
		panelDerecho.add(itemscomboDominiosSet2ReglaCompleja);
		itemscomboDominiosSet2ReglaCompleja.setBounds(100, 70, 150, 24);

		panelDerecho.setBounds(10, 50, 370, 110);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);
		comboDominiosSet2ReglaCompleja.addActionListener(controlDemoCombo);// agregamos escuchas

	}
	
	public void construirTercerSetDeDominiosReglaCompleja() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */

		labeltituloSeleccionDominios = new JLabel();

		comboDominiosSet3ReglaCompleja = new JComboBox(dominio);// creamos el Tercer combo,y le pasamos un array de cadenas
		comboDominiosSet3ReglaCompleja.setSelectedIndex(0);// por defecto quiero visualizar el Tercer item
		itemscomboDominiosSet3ReglaCompleja = new JComboBox();// creamo el Tercer combo, vacio
		itemscomboDominiosSet3ReglaCompleja.setEnabled(false);// //por defecto que aparezca deshabilitado

		labeltituloSeleccionDominios.setText("Seleccione el Tercer Dominio de regla compleja");
		panelCentral.add(labeltituloSeleccionDominios);
		labeltituloSeleccionDominios.setBounds(30, 30, 50, 20);
		panelCentral.add(comboDominiosSet3ReglaCompleja);
		comboDominiosSet1ReglaCompleja.setBounds(100, 30, 150, 24);
		panelCentral.add(itemscomboDominiosSet3ReglaCompleja);
		itemscomboDominiosSet3ReglaCompleja.setBounds(100, 70, 150, 24);

		panelCentral.setBounds(10, 50, 370, 110);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);
		comboDominiosSet4ReglaCompleja.addActionListener(controlDemoCombo);// agregamos escuchas

	}
	
	public void construirCuartoSetDeDominiosReglaCompleja() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */

		labeltituloSeleccionDominios = new JLabel();

		comboDominiosSet4ReglaCompleja = new JComboBox(dominio);// creamos el Cuarto combo,y le pasamos un array de cadenas
		comboDominiosSet4ReglaCompleja.setSelectedIndex(0);// por defecto quiero visualizar el Cuarto item
		itemscomboDominiosSet4ReglaCompleja = new JComboBox();// creamo el Cuarto combo, vacio
		itemscomboDominiosSet4ReglaCompleja.setEnabled(false);// //por defecto que aparezca deshabilitado

		labeltituloSeleccionDominios.setText("Seleccione el Cuarto Dominio de regla compleja");
		panelDerecho.add(labeltituloSeleccionDominios);
		labeltituloSeleccionDominios.setBounds(30, 30, 50, 20);
		panelDerecho.add(comboDominiosSet4ReglaCompleja);
		comboDominiosSet4ReglaCompleja.setBounds(100, 30, 150, 24);
		panelDerecho.add(itemscomboDominiosSet4ReglaCompleja);
		itemscomboDominiosSet4ReglaCompleja.setBounds(100, 70, 150, 24);

		panelDerecho.setBounds(10, 50, 370, 110);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);
		comboDominiosSet4ReglaCompleja.addActionListener(controlDemoCombo);// agregamos escuchas

	}
	
	public void construirSetOperadores() {
		/**
		 * construido a partir de tutorial de
		 * http://inforux.wordpress.com/2008/07
		 * /11/aprendiendo-con-jcombobox-seleciones-diversas/
		 */

		labeltituloSeleccionOperador = new JLabel();

		comboOperadores = new JComboBox(operadores);// creamos el Cuarto combo,y le pasamos un array de cadenas
		comboOperadores.setSelectedIndex(0);// por defecto quiero visualizar el Cuarto item
		

		labeltituloSeleccionOperador.setText("Seleccione el operador que relaciona ambas reglas");
		panelCentral.add(labeltituloSeleccionOperador);
		labeltituloSeleccionOperador.setBounds(30, 30, 50, 20);
		panelCentral.add(comboOperadores);
		comboOperadores.setBounds(100, 30, 150, 24);
		

		panelCentral.setBounds(10, 50, 370, 110);

		/* Creamos el objeto controlador, para manejar los eventos */
		ControlDemoCombo controlDemoCombo = new ControlDemoCombo(this);
		comboOperadores.addActionListener(controlDemoCombo);// agregamos escuchas

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
	// Ac‡ toca volverlas din‡micas y que lean de nuestro DimensionDataInput
	String[] dominioNombres = { "DANIEL", "SEBASTIAN", "ANDREA", "JACOBO" };// array del primer combo
	String[] dominioHoras = { "9:00", "10:00", "11:00", "12:00" };// array del
																	// segundo
																	// combo
	String[] dominioEnfermedades = { "DOLOR ESPALDA", "DOLOR CABEZA",
			"DOLOR CADERA", "DOLOR OJOS" };// array del tercer combo
	String[] dominio = { "Selecione Dominio", "Nombres", "Horas",
			"Enfermedades" };// array del primer combo
	String[] operadores = { "Selecione Operador","OR","AND","GREATER","LESS" };// array de operadores para  compuesta
	public JComboBox comboDominiosSet1;
	public JComboBox itemsComboDominiosSet1, itemscomboDominiosSet1ReglaCompleja, itemscomboDominiosSet2ReglaCompleja, itemscomboDominiosSet3ReglaCompleja, itemscomboDominiosSet4ReglaCompleja;
	public JComboBox itemsComboDominiosSet2;
	public JComboBox comboDominiosSet2;
	public JComboBox comboDominiosSet1ReglaCompleja, comboDominiosSet2ReglaCompleja, comboDominiosSet3ReglaCompleja, comboDominiosSet4ReglaCompleja;
	public JComboBox comboOperadores;
	public JLabel labeltituloSeleccionDominios, labeltituloSeleccionOperador;
	private JLabel itemPrimerDominiReglaSimple, itemSegundoDominiReglaSimple;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}