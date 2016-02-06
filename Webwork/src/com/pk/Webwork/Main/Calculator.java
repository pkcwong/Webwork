package com.pk.Webwork.Main;

import java.util.ArrayList;

public class Calculator {

	private org.nfunk.jep.JEP Parser;
	private ArrayList<String> var;
	private String expression;

	public Calculator() {
		this.init();
	}

	/**
	 * CLEARS ALL DATA
	 * <p>
	 */
	public void init() {
		this.Parser = new org.nfunk.jep.JEP();
		this.Parser.addStandardFunctions();
		this.Parser.addStandardConstants();
		var = new ArrayList<String>();
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
		this.Parser.addVariable(var, value);
		this.var.add(var);
	}

	/**
	 * RESETS VARIABLE LIST
	 * <p>
	 */
	public void clearVariable() {
		while (var.size() != 0) {
			this.Parser.removeVariable(var.get(0));
			var.remove(0);
		}
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

}
