package com.pk.Webwork.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static int questionID;
	public static File key;
	private static Scanner load;
	private static Calculator cal = new Calculator();
	private static Interface Frame;
	public static int N;
	public static ArrayList<String> var = new ArrayList<String>();
	public static ArrayList<Double> value = new ArrayList<Double>();
	public static String expression;

	public static void main(String[] args) {
		Frame = new Interface();
	}

	/**
	 * SETS TARGET FILE
	 * <p>
	 * 
	 * @param f
	 *            File
	 * @return true on success, false otherwise
	 */
	public static boolean setFile(File f) {
		if (f != null) {
			key = f;
			Frame.writeBody("loaded File ' " + key.getName() + " '");
			Frame.writeBody("Found " + getNumRecords() + " records");
			return true;
		}
		Frame.writeBody("[ERROR] There is a problem loading file");
		return false;
	}

	/**
	 * EXTRACTS ANSWER KEY
	 * <p>
	 * 
	 * @param id
	 *            question number
	 */
	public static void load(int id) {
		while (var.size() != 0) {
			var.remove(0);
		}
		try {
			load = new Scanner(key);
			int i = 0;
			while (i != id) {
				String line = load.nextLine();
				if (line.equals("#")) {
					i++;
				}
				if (i == id) {
					expression = load.nextLine();
					Frame.writeBody("Loaded expression ' " + expression + " '");
					N = load.nextInt();
					for (int j = 0; j != N; j++) {
						String tempVar = load.next();
						var.add(tempVar);
						Frame.writeBody("Loaded var ' " + tempVar + " '");
					}
				}
			}
		} catch (Exception err) {
			Frame.writeBody("[ERROR] There is a problem loading file");
			err.printStackTrace();
		}
	}

	/**
	 * NUMBER OF RECORDS IN FILE
	 * <p>
	 * 
	 * @return number of records
	 */
	public static int getNumRecords() {
		int total = 0;
		try {
			load = new Scanner(key);
		} catch (Exception err) {
			Frame.writeBody("[ERROR] There is a problem loading file");
			err.printStackTrace();
		}
		while (load.hasNext()) {
			String line = load.nextLine();
			if (line.equals("#")) {
				total++;
			}
		}
		return total;
	}

	/**
	 * HANDLES USER INPUT
	 * <p>
	 * 
	 * @param input
	 */
	public static void input(String input) {
		clear();
		input.replace("\n", " ");
		if (input != null && !input.isEmpty()) {
			String[] parts = input.split("\\s+");
			for (int i = 0; i != parts.length; i++) {
				Frame.writeBody("Got input ' " + parts[i] + " '");
				Calculator tempCal = new Calculator();
				tempCal.clear();
				for (int j = 0; j != value.size(); j++) {
					if (var.size() > j) {
						tempCal.addVariable(var.get(j), value.get(j));
					}
				}
				tempCal.input(parts[i]);
				if (tempCal.validate()) {
					double val = tempCal.evaluate();
					value.add(val);
					Frame.writeBody("Evaluated to " + val);
				} else {
					Frame.writeBody("[WARNING] Cannot parse input");
				}
			}
		}
	}

	/**
	 * CALLS PARSER
	 * <p>
	 */
	public static void execute() {
		Frame.writeBody("Compiling...");
		Frame.writeHead("Computing...");
		if (expression != null && !expression.isEmpty()) {
			String[] query = expression.split("\\s+");
			for (int k = 0; k != query.length; k++) {
				cal.clear();
				cal.input(query[k]);
				Frame.writeHead(">>> " + query[k]);
				for (int i = 0; i != value.size(); i++) {
					if (var.size() > i) {
						cal.addVariable(var.get(i), value.get(i));
						Frame.writeBody("Compiled @param ' " + var.get(i) + " '");
					} else {
						Frame.writeBody("[WARNING] Extra args[ ] ' " + value.get(i) + " '");
					}
				}
				if (N != 0) {
					if (cal.validate()) {
						Frame.writeBody("Expression evaluated to " + cal.evaluate());
						Frame.writeHead("\t" + cal.evaluate());
					} else {
						Frame.writeBody("[ERROR] See Parser log");
						Frame.writeHead(cal.errLog());
					}
				} else {
					Frame.writeBody("Expression evaluated to " + query[k]);
					Frame.writeHead("\t" + query[k]);
				}
			}
		}
		else {
			cal.clear();
			cal.input(expression);
			Frame.writeHead(">>> " + expression);
			for (int i = 0; i != value.size(); i++) {
				if (var.size() > i) {
					cal.addVariable(var.get(i), value.get(i));
					Frame.writeBody("Compiled @param ' " + var.get(i) + " '");
				} else {
					Frame.writeBody("[WARNING] Extra args[ ] ' " + value.get(i) + " '");
				}
			}
			if (N != 0) {
				if (cal.validate()) {
					Frame.writeBody("Expression evaluated to " + cal.evaluate());
					Frame.writeHead("\t" + cal.evaluate());
				} else {
					Frame.writeBody("[ERROR] See Parser log");
					Frame.writeHead(cal.errLog());
				}
			} else {
				Frame.writeBody("Expression evaluated to " + expression);
				Frame.writeHead("\t" + expression);
			}
		}
	}

	/**
	 * RESETS USER INPUT
	 */
	public static void clear() {
		while (value.size() != 0) {
			value.remove(0);
		}
	}

}
