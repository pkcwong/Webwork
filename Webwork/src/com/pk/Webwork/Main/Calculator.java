package com.pk.Webwork.Main;

import java.util.ArrayList;

public class Calculator {

	private org.nfunk.jep.JEP Parser;
	private ArrayList<String> var = new ArrayList<String>();
	private String expression;

	public Calculator() {
		this.clear();
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
	public void addVariable(String var, float value) {
		Parser.addVariable(var, value);
		this.var.add(var);
	}

	public void clearVariable() {
		while (var.size() != 0) {
			Parser.removeVariable(var.get(0));
			var.remove(0);
		}
	}

	/**
	 * EVALUATES THE EXPRESSION
	 * <p>
	 * 
	 * @return value of the expression on success, error on failure
	 */
	public String evaluate() {
		Parser.parseExpression(this.expression);
		if (Parser.getErrorInfo() != null) {
			return Parser.getErrorInfo();
		}
		return Double.toString(Parser.getValue());
	}

}
