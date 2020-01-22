package venn;

public class Circle {
	private int height, width, xPos, yPos;
	public static int NUM_CIRCLES;
	
	public Circle(int x, int y, int width, int height) {
		this.height = height;
		this.width = width;
		this.xPos = x;
		this.yPos = y;
		NUM_CIRCLES++;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getXPos() {
		return xPos;
	}

	public void setXPos(int x) {
		this.xPos = x;
	}
	
	public int getYPos() {
		return yPos;
	}

	public void setYPos(int y) {
		this.yPos = y;
	}
}
