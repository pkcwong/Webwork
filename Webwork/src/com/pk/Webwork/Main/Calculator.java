package com.pk.Webwork.Main;

public class Calculator {

	org.nfunk.jep.JEP Parser;
	private String expression;

	public Calculator() {

	}

	/**
	 * CLEARS ALL DATA
	 * <p>
	 */
	public void clear() {
		Parser = new org.nfunk.jep.JEP();
		Parser.addStandardFunctions();
		Parser.addStandardConstants();
	}

	/**
	 * ADDS A USER-DEFINED CONSTANT
	 * 
	 * @param var
	 *            variable name
	 * @param value
	 *            constant
	 */
	public void addVariable(String var, float value) {
		Parser.addVariable(var, value);
	}

	/**
	 * EVALUATES THE EXPRESSION
	 * <p>
	 * 
	 * @return value of the expression
	 */
	public float evaluate() {
		Parser.parseExpression(this.expression);
		return (float) (Parser.getValue());
	}

}
