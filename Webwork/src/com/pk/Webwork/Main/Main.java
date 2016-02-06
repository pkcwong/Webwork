package com.pk.Webwork.Main;

import java.io.File;
import java.util.Scanner;

public class Main {

	private static Scanner load;
	
	public static void main(String[] args) {
		Calculator cal=new Calculator();
		cal.input("1*cos(pi)+2*2^2");
		cal.addVariable("h", 0);
		System.out.println(cal.evaluate());
	}

	public static void load(File key) {
		try {
			load = new Scanner(key);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}

}
