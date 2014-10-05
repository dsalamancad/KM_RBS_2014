package co.uniandes.KM.logicPuzzles.mundo;

public class ComplexFact {

	private Fact fact1, fact2;
	private Operator operator;
	
	
	/**
	 * @param fact1
	 * @param fact2
	 * @param operator
	 */
	public ComplexFact(Fact fact1, Fact fact2, Operator operator) {
		super();
		this.fact1 = fact1;
		this.fact2 = fact2;
		this.operator = operator;
	}
	/**
	 * @return the fact1
	 */
	public Fact getFact1() {
		return fact1;
	}
	/**
	 * @param fact1 the fact1 to set
	 */
	public void setFact1(Fact fact1) {
		this.fact1 = fact1;
	}
	/**
	 * @return the fact2
	 */
	public Fact getFact2() {
		return fact2;
	}
	/**
	 * @param fact2 the fact2 to set
	 */
	public void setFact2(Fact fact2) {
		this.fact2 = fact2;
	}
	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
}
