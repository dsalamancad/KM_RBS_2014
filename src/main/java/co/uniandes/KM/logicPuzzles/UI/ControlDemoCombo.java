package co.uniandes.KM.logicPuzzles.UI;

import java.awt.event.*;
import co.uniandes.KM.logicPuzzles.UI.RulesUI;

public class ControlDemoCombo implements ActionListener {
	RulesUI frameDemoCombo;

	public ControlDemoCombo(RulesUI objeto) {
		frameDemoCombo = objeto;
	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == frameDemoCombo.comboDominiosSet1)// pregunta
																	// si el
																	// evento
																	// viene del
																	// primer
																	// combo
		{/* pregunta si viene del primer elemnto */
			if (frameDemoCombo.comboDominiosSet1.getSelectedIndex() == 0) {
				frameDemoCombo.itemsComboDominiosSet1.removeAllItems();
				frameDemoCombo.itemsComboDominiosSet1.setEnabled(false);
				
			}
			/* pregunta si viene del segundo elemnto */
			if (frameDemoCombo.comboDominiosSet1.getSelectedIndex() == 1) {
				frameDemoCombo.itemsComboDominiosSet1.removeAllItems();
				for (int i = 0; i < frameDemoCombo.dominioNombres.length; i++)
					frameDemoCombo.itemsComboDominiosSet1
							.addItem(frameDemoCombo.dominioNombres[i]);
								
								
				frameDemoCombo.itemsComboDominiosSet1.setEnabled(true);
				// frameDemoCombo.itemsComboDominiosSet2.removeItemAt(1);

			}
			/* pregunta si viene del tercer elemnto */
			if (frameDemoCombo.comboDominiosSet1.getSelectedIndex() == 2) {
				frameDemoCombo.itemsComboDominiosSet1.removeAllItems();
				for (int i = 0; i < frameDemoCombo.dominioHoras.length; i++)
					frameDemoCombo.itemsComboDominiosSet1
							.addItem(frameDemoCombo.dominioHoras[i]);
				frameDemoCombo.itemsComboDominiosSet1.setEnabled(true);
				// frameDemoCombo.itemsComboDominiosSet2.removeItemAt(2);
			}
			/* pregunta si viene del cuarto elemnto */
			if (frameDemoCombo.comboDominiosSet1.getSelectedIndex() == 3) {
				frameDemoCombo.itemsComboDominiosSet1.removeAllItems();
				for (int i = 0; i < frameDemoCombo.dominioEnfermedades.length; i++)
					frameDemoCombo.itemsComboDominiosSet1
							.addItem(frameDemoCombo.dominioEnfermedades[i]);
				frameDemoCombo.itemsComboDominiosSet1.setEnabled(true);
			}
		}

		if (evento.getSource() == frameDemoCombo.itemsComboDominiosSet2)// pregunta
																		// si el
																		// evento
																		// viene
																		// del
																		// primer
																		// combo
		{/* pregunta si viene del primer elemnto */
			if (frameDemoCombo.itemsComboDominiosSet2.getSelectedIndex() == 0) {
				frameDemoCombo.comboDominiosSet2.removeAllItems();
				frameDemoCombo.comboDominiosSet2.setEnabled(false);
			}
			/* pregunta si viene del segundo elemnto */
			if (frameDemoCombo.itemsComboDominiosSet2.getSelectedIndex() == 1) {
				frameDemoCombo.comboDominiosSet2.removeAllItems();
				for (int i = 0; i < frameDemoCombo.dominioNombres.length; i++)
					frameDemoCombo.comboDominiosSet2
							.addItem(frameDemoCombo.dominioNombres[i]);
				frameDemoCombo.comboDominiosSet2.setEnabled(true);
			}
			/* pregunta si viene del tercer elemnto */
			if (frameDemoCombo.itemsComboDominiosSet2.getSelectedIndex() == 2) {
				frameDemoCombo.comboDominiosSet2.removeAllItems();
				for (int i = 0; i < frameDemoCombo.dominioHoras.length; i++)
					frameDemoCombo.comboDominiosSet2
							.addItem(frameDemoCombo.dominioHoras[i]);
				frameDemoCombo.comboDominiosSet2.setEnabled(true);
			}
			/* pregunta si viene del cuarto elemnto */
			if (frameDemoCombo.itemsComboDominiosSet2.getSelectedIndex() == 3) {
				frameDemoCombo.comboDominiosSet2.removeAllItems();
				for (int i = 0; i < frameDemoCombo.dominioEnfermedades.length; i++)
					frameDemoCombo.comboDominiosSet2
							.addItem(frameDemoCombo.dominioEnfermedades[i]);
				frameDemoCombo.comboDominiosSet2.setEnabled(true);
			}
		}

		if (evento.getSource() == frameDemoCombo.reglasParaCrear)// pregunta si
																	// el evento
																	// viene del
																	// primer
																	// combo
		{/* pregunta si viene del primer elemnto */
			if (frameDemoCombo.reglasParaCrear.getSelectedIndex() == 0) {
				System.out.println("nada seleccionado");
			}
			/* pregunta si viene del segundo elemnto */
			if (frameDemoCombo.reglasParaCrear.getSelectedIndex() == 1) {
				System.out.println("regla simple");
				//creo que deberia ser que si selecciona esta, construya el primer y segundo set de dominios,pero no se como implementarlo;

			}
			/* pregunta si viene del tercer elemnto */
			if (frameDemoCombo.reglasParaCrear.getSelectedIndex() == 2) {
				System.out.println("regla compleja");
				//no est‡ hecho
				}

		}
	}
}