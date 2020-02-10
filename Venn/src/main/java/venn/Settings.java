package venn;

import java.awt.Color;
import java.awt.Font;

public class Settings {
	public static int numCircles;
	public static Color color;
	public static Font font;
	public static String name;
	
	public Settings() {
		Settings.numCircles = 2;
		Settings.color = Color.black;
		Settings.font = new Font("Arial", Font.PLAIN, 10);
		Settings.name = "Venn Diagram";
	}
	
	public static void setFont(Font f) {
		Settings.font = f;
	}
	
	public static void setColor(Color c) {
		Settings.color = c;
	}
	
	public static void setName(String name) {
		Settings.name = name;
	}
	
	public static void setNumCircles(int n) {
		Settings.numCircles = n;
	}
}
