package com.pk.Webwork.Main;

import java.io.File;
import java.util.Scanner;

public class Main {

	private static Scanner cin;
	
	public static void main(String[] args) {

	}

	public static void load(File key) {
		try {
			cin = new Scanner(key);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}

}
