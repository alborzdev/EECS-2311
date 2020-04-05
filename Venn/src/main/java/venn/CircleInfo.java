package venn;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class CircleInfo {
	private String name;
	private int x, y, strokeSize, size;
	private Color c;
	private BufferedImage img;
	
	public CircleInfo(int stroke, int size, Color c) {
		this.strokeSize = stroke;
		this.size = size;
		this.c = c;
		this.img = null;
		this.x = this.y = 0;
		this.name = "";
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getStrokeSize() {
		return this.strokeSize;
	}
	
	public void setStrokeSize(int stroke) {
		this.strokeSize = stroke;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
