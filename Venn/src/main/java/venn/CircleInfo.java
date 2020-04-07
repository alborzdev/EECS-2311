package venn;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class CircleInfo {
	private String name;
	private int x, y, strokeSize, size, prevSize;
	private Color c;
	private BufferedImage img;
	
	public CircleInfo(int stroke, int size, Color c) {
		this.strokeSize = stroke;
		this.size = size;
		this.prevSize = size;
		this.c = c;
		this.img = null;
		this.x = this.y = 0;
		this.name = "";
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setSize(int size) {
		this.prevSize = this.size;
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
	
	public void updateImage() {
		BufferedImage img = new BufferedImage(this.size,this.size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		
		BufferedImage cimg = new BufferedImage(this.size,this.size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d1 = cimg.createGraphics();
		g2d1.setClip(new Ellipse2D.Float(0,0,this.size,this.size));
		Color foreColor = new Color(this.c.getRed(),this.c.getGreen(),this.c.getBlue(),127);
		g2d1.setColor(foreColor);
	    g2d1.fillOval(0, 0, this.size, this.size);
	    g2d1.setColor(Color.black);
	    g2d1.setStroke(new BasicStroke(this.strokeSize));
	    g2d1.drawOval(0+strokeSize/2,0+strokeSize/2,this.size-strokeSize,this.size-strokeSize);
	    g2d1.drawImage(img, 0, 0,this.size,this.size, null);
		
	    this.img = cimg;
		
		g2d.dispose();
		g2d1.dispose();
	}
		
}
