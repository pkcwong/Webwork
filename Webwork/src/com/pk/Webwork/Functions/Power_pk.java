package com.pk.Webwork.Functions;

import java.util.Stack;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

public class Power_pk extends PostfixMathCommand {

	public Power_pk() {
		super.numberOfParameters = 2;
	}

	public void run(Stack inStack) throws ParseException {
		super.checkStack(inStack);
		Object param_b = inStack.pop();
		Object param_a = inStack.pop();
		if (param_a instanceof Double && param_b instanceof Double) {
			double a=((Double) (param_a)).doubleValue();
			double b=((Double) (param_b)).doubleValue();
			int k;
			if (a>=0) {
				k=1;
			}
			else {
				a=-a;
				k=-1;
			}
			double r = k*Math.pow(a, b);
			inStack.push(new Double(r));
		} else {
			throw new ParseException("Error in Power.java");
		}
	}

}
