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
	public static int M;
	private static String remarks;
	public static ArrayList<String> var = new ArrayList<String>();
	public static ArrayList<String> value = new ArrayList<String>();
	private static ArrayList<String> sysVar = new ArrayList<String>();
	private static ArrayList<String> sysValue = new ArrayList<String>();
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
			Frame.writeBody("Loaded File ' " + key.getName() + " '");
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
		clearSys();
		try {
			load = new Scanner(key);
			int i = 0;
			while (i != id) {
				String line = load.nextLine();
				if (line.equals("#")) {
					i++;
				}
				if (i == id) {
					String text = "";
					remarks = load.nextLine();
					Frame.writeHead(remarks);
					expression = load.nextLine();
					N = load.nextInt();
					for (int j = 0; j != N; j++) {
						String defaultVar = load.next();
						String defaultExpression = load.next();
						var.add(defaultVar);
						value.add(defaultExpression);
						text = text + defaultExpression + "\n";
						cal.input(defaultExpression);
						if (cal.validate()) {
							cal.addVariable(defaultVar, cal.evaluate());
							Frame.writeBody("Loaded Var " + defaultVar + "=" + cal.evaluate());
						} else {
							Frame.writeBody("[ERROR] See Parser");
							Frame.writeHead(cal.errLog());
						}
					}
					if (Frame.Input.getText() == null || Frame.Input.getText().isEmpty()) {
						Frame.Input.setText(text);
					}
					M = load.nextInt();
					for (int j = 0; j != M; j++) {
						String defaultVar = load.next();
						String defaultExpression = load.next();
						sysVar.add(defaultVar);
						sysValue.add(defaultExpression);
						cal.input(defaultExpression);
						if (cal.validate()) {
							cal.addVariable(defaultVar, cal.evaluate());
							Frame.writeBody("Loaded sysVar " + defaultVar + "=" + cal.evaluate());
						} else {
							Frame.writeBody("[ERROR] See Parser");
							Frame.writeHead(cal.errLog());
						}
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
		clearUser();
		input.replace("\n", " ");
		if (input != null && !input.isEmpty()) {
			String[] parts = input.split("\\s+");
			for (int i = 0; i != parts.length; i++) {
				if (i < N) {
					value.add(parts[i]);
					cal.input(parts[i]);
					if (cal.validate()) {
						cal.addVariable(var.get(i), cal.evaluate());
						Frame.writeBody("Set " + var.get(i) + "=" + cal.evaluate());
					} else {
						Frame.writeBody("[ERROR] See Parser");
						Frame.writeHead(cal.errLog());
					}
				} else {
					Frame.writeBody("[WARNING] Extra args[ ] '" + parts[i] + "'");
				}
			}
			for (int i = 0; i < var.size() - parts.length; i++) {
				Frame.writeBody("[WARNING] Missing args[ ] '" + var.get(parts.length + i - 1));
			}
		}
		for (int i = 0; i != sysVar.size(); i++) {
			cal.input(sysValue.get(i));
			if (cal.validate()) {
				cal.addVariable(sysVar.get(i), cal.evaluate());
				Frame.writeBody("Set " + sysVar.get(i) + "=" + cal.evaluate());
			} else {
				Frame.writeBody("[ERROR] See Parser");
				Frame.writeHead(cal.errLog());
			}
		}
	}

	/**
	 * CALLS PARSER
	 * <p>
	 */
	public static void execute() {
		Frame.writeBody("Compiling...");
		Frame.writeHead(">>> " + expression);
		cal.input(expression);
		if (cal.validate()) {
			Frame.writeBody("Success");
			Frame.writeHead("\t" + cal.evaluate());
		} else {
			if (N != 0 || M != 0) {
				Frame.writeBody("[ERROR] See Parser");
				Frame.writeHead(cal.errLog());
			} else {
				Frame.writeBody("Success");
				Frame.writeHead("\t" + expression);
			}
		}
	}

	/**
	 * RESETS SYSTEM VARIABLES
	 * <p>
	 */
	public static void clearSys() {
		cal.reset();
		while (var.size() != 0) {
			var.remove(0);
		}
		while (sysVar.size() != 0) {
			sysVar.remove(0);
		}
		while (sysValue.size() != 0) {
			sysValue.remove(0);
		}
	}

	/**
	 * RESETS USER INPUT
	 * <p>
	 */
	public static void clearUser() {
		while (value.size() != 0) {
			value.remove(0);
		}
	}

}
