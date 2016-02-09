package com.pk.Webwork.Main;

public class Calculator {

	private org.nfunk.jep.JEP Parser;
	private String expression;

	public Calculator() {
		this.reset();
	}

	/**
	 * CLEARS ALL DATA
	 * <p>
	 */
	public void reset() {
		this.Parser = new org.nfunk.jep.JEP();
		this.Parser.addStandardFunctions();
		this.Parser.addStandardConstants();
	}

	/**
	 * INPUT EXPRESSION
	 * <p>
	 * 
	 * @param expression
	 *            arithmetic expression
	 */
	public void input(String expression) {
		this.expression = expression;
	}

	/**
	 * ADDS A USER-DEFINED CONSTANT
	 * <p>
	 * 
	 * @param var
	 *            variable name
	 * @param value
	 *            constant
	 */
	public void addVariable(String var, double value) {
		this.Parser.addVariable(var, value);
	}

	/**
	 * VALIDATES EXPRESSION
	 * <p>
	 * 
	 * @return true on success
	 */
	public boolean validate() {
		this.Parser.parseExpression(this.expression);
		if (this.Parser.getErrorInfo() != null) {
			return false;
		}
		return true;
	}

	/**
	 * EVALUATES THE EXPRESSION
	 * <p>
	 * 
	 * @return value of the expression
	 */
	public double evaluate() {
		this.Parser.parseExpression(this.expression);
		return Parser.getValue();
	}

	public String errLog() {
		return this.Parser.getErrorInfo();
	}
	
}
