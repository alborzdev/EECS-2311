package venn;

import java.awt.Graphics;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class MainPaint extends JPanel {
	private int x, y, height, width;
	
	public void paintHelper(Graphics g, Circle circle) {
		x = circle.getXPos();
		y = circle.getYPos();
		height = circle.getHeight();
		width = circle.getWidth();
		
		paint(g);
	}
	public void paint(Graphics g) {
		if(Circle.NUM_CIRCLES > 0 && Main.TRIGGERED) {
			g.drawOval(x, y, width, height);
			Main.TRIGGERED = false;
			System.out.printf("Painted with x = %d, y = %d, width = %d, heigth = %d\n",x,y,width,height);
		}
	}
}
