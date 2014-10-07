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
	
	public String coordinatesToString() {
		String result = "["+coordinates[0]+"";
		for (int i = 1; i < coordinates.length; i++) {
			result+=","+coordinates[i];
		}
		return result+"]";
	}
}
