package com.pk.Webwork.Main;

import java.io.File;
import java.util.Scanner;

public class Main {

	private static Scanner load;

	public static void main(String[] args) {
		new Interface();
	}

	public static void load(File key) {
		try {
			load = new Scanner(key);
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

}
