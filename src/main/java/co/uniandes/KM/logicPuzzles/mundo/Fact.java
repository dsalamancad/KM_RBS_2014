package co.uniandes.KM.logicPuzzles.mundo;

import java.util.ArrayList;

import co.uniandes.KM.logicPuzzles.Configuration;

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
	
	public boolean onlyOneCoordinate(){
		int n=0;
		for (int i = 0; i < coordinates.length; i++) {
			if(coordinates[i]==-1){
				n++;
			}
		}
		return (n==(Configuration.DIMENSION_AMOUNT-1));
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
	
	public Fact combineFacts(Fact f1){
		Integer[] coorResult = new Integer[coordinates.length];
	    for(int i = 0; i < coorResult.length ; i++) {
	    	coorResult[i] = (f1.coordinates[i]==-1?coordinates[i]:f1.coordinates[i]);
	    }
	    Fact resFact= new Fact(coorResult);
	    return resFact;	    		
	}
	
	public Integer getDimensionValue(int dimension){
		return coordinates[dimension];
	}
	
	public ArrayList<Integer> commonCoordinates(CoordinatedObject obj) {
	    boolean[] dimensionComparisson = compareArrays(this.coordinates, obj.getCoordinates());

	    ArrayList<Integer> positiveCounter = new ArrayList<Integer>();
	    for(int i = 0; i < Configuration.DIMENSION_AMOUNT; i++) {
	        if(dimensionComparisson[i])
	            positiveCounter.add(i);
	    }
	    
	    return positiveCounter;
	}
}
