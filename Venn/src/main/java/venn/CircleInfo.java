package venn;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class CircleInfo {
	private int x, y;
	private Color c;
	private BufferedImage img;
	
	public CircleInfo(int x, int y, BufferedImage img, Color c) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.c = c;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	public Color getColor() {
		return this.c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setImage(BufferedImage img) {
		this.img = img;
	}
	
	public BufferedImage getImage() {
		return this.img;
	}
	
	
}
