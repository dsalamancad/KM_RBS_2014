/**
 * 
 */
package co.uniandes.KM.logicPuzzles.mundo;

import org.kie.api.runtime.KieSession;

import co.uniandes.KM.logicPuzzles.Configuration;
import co.uniandes.KM.logicPuzzles.UI.DroolsTest.Message;

/**
 * @author sebastian
 *
 */
public class LogicPuzzle {
    
    private LogicDimension[] dimensions;
    
    private Cell[][][] boardMatrix;
    
    private KieSession session;
    
    public LogicPuzzle(LogicDimension[] dimensions)
    {
        this.dimensions = dimensions;
        boardMatrix = new Cell[Configuration.ITEMS_PER_DIMENSION][Configuration.ITEMS_PER_DIMENSION][Configuration.ITEMS_PER_DIMENSION];
		for (int i = 0; i < boardMatrix.length; i++) {
			for (int j = 0; j < boardMatrix.length; j++) {
				for (int k = 0; k < boardMatrix.length; k++) {
					Integer[] coordinateArray = {i,j,k};
					boardMatrix[i][j][k] = new Cell(Cell.UNKNOWN, coordinateArray);
				}
			}
		}
    }
    
	/**
	 * @return the dimensions
	 */
	public LogicDimension[] getDimensions() {
		return dimensions;
	}

	/**
	 * @param dimensions the dimensions to set
	 */
	public void setDimensions(LogicDimension[] dimensions) {
		this.dimensions = dimensions;
	}

	/**
	 * @return the tablero
	 */
	public Cell[][][] getTablero() {
		return boardMatrix;
	}

	/**
	 * @param tablero the tablero to set
	 */
	public void setTablero(Cell[][][] tablero) {
		this.boardMatrix = tablero;
	}

	public KieSession getSession() {
		return session;
	}

	public void setSession(KieSession session) {
		this.session = session;
		for (int i = 0; i < boardMatrix.length; i++) {
			for (int j = 0; j < boardMatrix.length; j++) {
				for (int k = 0; k < boardMatrix.length; k++) {
					session.insert(boardMatrix[i][j][k]);
				}
			}
		}
		Message message = new Message();
		message.setMessage("ZOMGWTFBBQ!!!1!oneone");
		Integer[] factCoordinates = {0,0,-1};
		//Integer[] factCoordinates = {1,1,-1};
		Fact fact = new Fact(factCoordinates);
		session.insert(fact);
		Integer[] coorF1 = {0,1,-1}; //Falsa
		Integer[] coorF2 = {0,-1,0}; //Verdadera
		Fact fact1 = new Fact(coorF1);
		Fact fact2 = new Fact(coorF2);
		ComplexFact cf = new ComplexFact(fact1, fact2, Operator.OR);
		session.insert(cf);
        session.insert(message);
        session.fireAllRules();
	}
	
	public String getSummary(Integer[] dimensionIndexes, Integer[] coordinates)
	{
		Integer[] coordinateSet = new Integer[Configuration.DIMENSION_AMOUNT];
		for (int i = 0; i < Configuration.DIMENSION_AMOUNT; i++) {
			coordinateSet[i] = -1;
		}
		for (int i = 0; i < dimensionIndexes.length; i++) {
			coordinateSet[dimensionIndexes[i]] = coordinates[i];
		}
		return ((Cell) followCoordinateSet(boardMatrix, coordinateSet)).toString();
	}
	
	public Object followCoordinateSet( Object currentSearchSpace ,  Integer[] coordinateSet) {
		if(currentSearchSpace.getClass().isArray())
		{
			int currentCoordinate = coordinateSet[0] == -1?3:coordinateSet[0];
			if (coordinateSet[0] == -1 && !((Object[]) currentSearchSpace)[currentCoordinate].getClass().isArray())
				currentCoordinate = 0;
			Integer[] newCoordinateSet = new Integer[coordinateSet.length-1];
			System.arraycopy(coordinateSet, 1 , newCoordinateSet , 0 , newCoordinateSet.length);
			return followCoordinateSet(((Object[]) currentSearchSpace)[currentCoordinate], newCoordinateSet);
		}
		else
			return currentSearchSpace;
	}
	
	
	
}