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

		
		Integer[] coorF1 = {3,-1,-1}; //Falsa
		Integer[] coorF2 = {-1,-1,0}; //Verdadera
		Fact fact1 = new Fact(coorF1);
		Fact fact2 = new Fact(coorF2);
		ComplexFact cf1= new ComplexFact(fact1, fact2, Operator.LESS);
		

		Integer[] coorF3 = {-1,-1,2}; //Verdadera
		Integer[] coorF4 = {-1,-1,0}; //Falsa
		Fact fact3 = new Fact(coorF3);
		Fact fact4 = new Fact(coorF4);
		
		ComplexFact cf2= new ComplexFact(fact3, fact4, Operator.LESS);
		
		Integer[] coorF11 = {0,-1,-1}; //Verdadera
		Integer[] coorF12 = {1,-1,-1}; //Falsa
		Fact fact11 = new Fact(coorF11);
		Fact fact12 = new Fact(coorF12);
		
		ComplexFact cf3= new ComplexFact(fact11, fact12, Operator.LESS);
		
		Integer[] coorF9 = {2,1,-1}; //Verdadera
		Integer[] coorF10 = {-1,1,2}; //Falsa
		Fact fact9 = new Fact(coorF9);
		Fact fact10 = new Fact(coorF10);
		
		ComplexFact cf4= new ComplexFact(fact9, fact10, Operator.OR);
		
		
		Integer[] coorF5 = {-1,0,-1}; //Verdadera
		Integer[] coorF6 = {-1,3,-1}; //Falsa
		Integer[] coorF7 = {-1,-1,3}; //Verdadera
		Integer[] coorF8 = {2,-1,-1}; //Verdadera
		Fact fact5 = new Fact(coorF5);
		Fact fact6 = new Fact(coorF6);
		Fact fact7 = new Fact(coorF7);
		Fact fact8 = new Fact(coorF8);
		XORFact xf= new XORFact(fact5, fact6, fact7, fact8);
		
		session.insert(cf4);
		session.insert(cf1);
		session.insert(cf3);
		session.insert(cf2);
		
		
		session.insert(xf);
		
		
        session.insert(message);
        session.fireAllRules();
        
        for (int i = 0; i < boardMatrix.length; i++) {
			for (int j = 0; j < boardMatrix.length; j++) {
				for (int k = 0; k < boardMatrix.length; k++) {
					
					System.out.println(boardMatrix[i][j][k].coordinatesToString()+":"+boardMatrix[i][j][k].getStatus()); 
				}
			}
		}
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