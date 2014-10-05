package co.uniandes.KM.logicPuzzles.mundo;

public class Fact implements CoordinatedObject{

	private Integer[] coordinates;

	public Fact(Integer[] coordinates) {
		super();
		this.coordinates = coordinates;
	}

	public Integer[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Integer[] coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
