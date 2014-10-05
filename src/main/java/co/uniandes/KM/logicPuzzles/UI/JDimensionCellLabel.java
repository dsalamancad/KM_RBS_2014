package co.uniandes.KM.logicPuzzles.UI;

import javax.swing.JLabel;

public class JDimensionCellLabel extends JLabel {
	
	private Integer[] dimensionIndexes;
	private Integer[] coordinates;
	
	public JDimensionCellLabel(String text, Integer[] dimensionIndexes, Integer[] coordinates) {
		super(text);
		this.dimensionIndexes = dimensionIndexes;
		this.coordinates = coordinates;
	}

	public Integer[] getDimensionIndexes() {
		return dimensionIndexes;
	}

	public void setDimensionIndexes(Integer[] dimensionIndexes) {
		this.dimensionIndexes = dimensionIndexes;
	}

	public Integer[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Integer[] coordinates) {
		this.coordinates = coordinates;
	}


}
