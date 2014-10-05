package co.uniandes.KM.logicPuzzles.UI;

import javax.swing.*;

import java.awt.event.*;

public class DropDownList extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	private JComboBox dominio1;
	private JLabel fraseDominio1;
	private JLabel textoDominio1;

	public DropDownList() {
		setLayout(null);
		dominio1 = new JComboBox();
		dominio1.setBounds(10, 10, 150, 20);
		add(dominio1);
		dominio1.addItem("Daniel");
		dominio1.addItem("Carolina");
		dominio1.addItem("Beto");
		dominio1.addItem("Carlos");

		dominio1.addItemListener(this);

		fraseDominio1 = new JLabel("es la due–a de");
		textoDominio1 = new JLabel("PERRO");
		textoDominio1.setBounds(200, 10, 150, 20);
		fraseDominio1.setBounds(300, 10, 150, 20);
		add(textoDominio1);
		add(fraseDominio1);

	}

	public static void main(String[] args) {

		DropDownList ventana = new DropDownList();
		ventana.setBounds(10, 10, 960, 580);
		ventana.setVisible(true);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == dominio1) {
			String seleccionDominio1 = (String) dominio1.getSelectedItem();
			textoDominio1.setText(seleccionDominio1);
		}

	}
}