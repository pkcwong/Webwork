package com.pk.Webwork.Main;

import java.io.File;
import java.util.Scanner;

public class Main {

	private static Scanner load;
	private static Calculator cal=new Calculator();
	
	public static void main(String[] args) {
		Interface Frame=new Interface();
		Frame.writeBody("lol");
		Frame.writeBody("again");
	}

	public static void load(File key) {
		try {
			load = new Scanner(key);
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
}
