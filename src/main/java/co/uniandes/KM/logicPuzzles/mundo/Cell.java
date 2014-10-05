/**
 * 
 */
package co.uniandes.KM.logicPuzzles.mundo;

import java.util.ArrayList;

import co.uniandes.KM.logicPuzzles.Configuration;

/**
 * @author danielsalamanca
 */
public class Cell implements CoordinatedObject{
	
	public final static String UNKNOWN = "?";
	public final static String TRUE = "O";
	public final static String IMPOSSIBLE = "X";
	public final static String POSSIBLE = "P";
	
	private String status;
	
	private Integer[] coordinates;

	public Cell(String status, Integer[] coordinates) {
		super();
		this.status = status;
		if(status==null)
			status = UNKNOWN;
		this.coordinates = coordinates;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
	/* (non-Javadoc)
	 * @see co.uniandes.KM.logicPuzzles.mundo.CoordinatedObject#getCoordinates()
	 */
	public Integer[] getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 */
	public void setCoordinates(Integer[] coordinates) {
		this.coordinates = coordinates;
	}

	public int segregateCoordinates(CoordinatedObject obj) {
	    boolean[] dimensionComparisson = compareArrays(this.coordinates, obj.getCoordinates());

	    ArrayList<Integer> positiveCounter = new ArrayList<Integer>();
	    for(int i = 0; i < Configuration.DIMENSION_AMOUNT; i++) {
	        if(dimensionComparisson[i])
	            positiveCounter.add(i);
	    }
	    
	    return positiveCounter.size();
	}

	public boolean[] compareArrays(Object[] array1, Object[] array2) {
	    boolean[] result = new boolean[array1.length];
	    for(int i = 0; i < result.length ; i++) {
	        result[i] = array1[i].equals(array2[i]);
	    }
	    return result;
	}
	
	public String coordinatesToString() {
		String result = "["+coordinates[0]+"";
		for (int i = 1; i < coordinates.length; i++) {
			result+=","+coordinates[i];
		}
		return result+"]";
	}
	
	@Override
	public String toString() {
	    return coordinatesToString()+":"+status;
	}
}
