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
	private static String remarks;
	public static ArrayList<String> var = new ArrayList<String>();
	public static ArrayList<Double> value = new ArrayList<Double>();
	private static ArrayList<String> sysVar=new ArrayList<String>();
	private static ArrayList<String> sysValue=new ArrayList<String>();
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
					//TODO
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
				//TODO
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
		//TODO
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
