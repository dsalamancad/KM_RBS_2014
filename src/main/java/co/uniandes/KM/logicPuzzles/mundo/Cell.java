/**
 * 
 */
package co.uniandes.KM.logicPuzzles.mundo;

/**
 * @author danielsalamanca
 *
 */
public class Cell {
	
	public final static String UNKNOWN = "?";
	public final static String TRUE = "O";
	public final static String IMPOSSIBLE = "X";
	
	private String status;
	
	private int x,y,z;

	public Cell(String status, int x, int y, int z) {
		super();
		this.status = status;
		this.x = x;
		this.y = y;
		this.z = z;
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

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	
	
	
	

}
