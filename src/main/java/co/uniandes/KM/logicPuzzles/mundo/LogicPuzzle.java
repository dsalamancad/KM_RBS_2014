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
        boardMatrix = new Cell[Configuration.ITEM_PER_DIMENSION_AMOUNT][Configuration.ITEM_PER_DIMENSION_AMOUNT][Configuration.ITEM_PER_DIMENSION_AMOUNT];
		for (int i = 0; i < boardMatrix.length; i++) {
			for (int j = 0; j < boardMatrix.length; j++) {
				for (int k = 0; k < boardMatrix.length; k++) {
					boardMatrix[i][j][k] = new Cell(Cell.UNKNOWN, i, j, k);
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
        session.insert(message);
        session.fireAllRules();
	}
    
    
}
