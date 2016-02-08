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
	public static String expression = "1+2";

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
			Frame.writeBody("loaded File '" + key.getName() + "'");
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
		try {
			load = new Scanner(key);
			// TODO: load file
			int i = 0;
			while (i != id) {

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
		String[] parts = input.split("\\s+");
		// TODO: extract user input
	}

	/**
	 * CALLS PARSER
	 * <p>
	 */
	public static void execute() {
		// TODO: call Parser
		
	}

	/**
	 * RESETS USER INPUT
	 */
	public static void clear() {
		while (var.size() != 0) {
			var.remove(0);
		}
		while (value.size() != 0) {
			value.remove(0);
		}
	}

}
