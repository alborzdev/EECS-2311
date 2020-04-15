package venn;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class CircleInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int x, y, strokeSize, size;
	private static int ID=0;
	private Color c;
	private int[][] imgArray;
	
	public CircleInfo() {
		this.name = "Circle"+ID;
		this.x = this.y = this.strokeSize = this.size = 0;
		this.c = null;
		imgArray = new int[600][600];
		ID++;
	}
	
	
	public CircleInfo(int stroke, int size, Color c) {
		this.strokeSize = stroke;
		this.size = size;
		this.c = c;
		this.x = this.y = 0;
		this.name = "Circle"+ID;
		imgArray = new int[600][600];
		ID++;
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
		if(stroke < 0 ) {
			stroke = 0;
		}else if(stroke > 10) {
			stroke = 10;
		}
		this.strokeSize = stroke;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.length()>0) {
			this.name = name;
		}
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
		//int pixelLength = 4; //4 channels A B G R
		int[][] arr = new int[img.getHeight()][img.getWidth()];
		for(int row=0; row < img.getHeight(); row++) {
			for(int col=0; col < img.getWidth(); col++) {
				arr[row][col] = img.getRGB(col, row);
			}
		}
		imgArray = arr;
//		for(int i = 0; i + 3 < pixels.length; i+=pixelLength) {
//			int argb = 0;
//			argb += (((int) pixels[i] & 0xff) << 24); // A
//			argb += ((int) pixels[i+1] & 0xff); // B
//			argb += (((int) pixels[i+3] & 0xff) << 16); // R
//			argb += (((int) pixels[i+2] & 0xff) << 8); // G
//			
//			imgArray[row][col] = argb;
//			col++;
//			if(col == img.getWidth()) {
//				col = 0;
//				row++;
//			}
//		}
//		
	}
	
	public BufferedImage getImage() {
		BufferedImage img = new BufferedImage(imgArray.length, imgArray[0].length, BufferedImage.TYPE_INT_ARGB);
		
		
		for(int i = 0; i < imgArray.length; i++) {
			for(int j = 0; j < imgArray[i].length; j++ ) {
//				int argb = 0;
//				argb += (((int) imgArray[i][j] & 0xff) << 24); // A
//				argb += ((int) imgArray[i][j] & 0xff); // B
//				argb += (((int) imgArray[i][j] & 0xff) << 16); // R
//				argb += (((int) imgArray[i][j] & 0xff) << 8); // G
				
				img.setRGB(j, i,imgArray[i][j]);
			}
		}
		return img;
	}
	
	public void updateImage() {
		BufferedImage img = new BufferedImage(this.size,this.size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		
		BufferedImage cimg = new BufferedImage(this.size,this.size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d1 = cimg.createGraphics();
		g2d1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d1.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d1.setClip(new Ellipse2D.Float(0,0,this.size,this.size));
		Color foreColor = new Color(this.c.getRed(),this.c.getGreen(),this.c.getBlue(),127);
		g2d1.setColor(foreColor);
	    g2d1.fillOval(0, 0, this.size, this.size);
	    g2d1.setColor(Color.black);
	    g2d1.setStroke(new BasicStroke(this.strokeSize));
	    g2d1.drawOval(0+strokeSize/2,0+strokeSize/2,this.size-strokeSize,this.size-strokeSize);
	    g2d1.drawImage(img, 0, 0,this.size,this.size, null);
		
	    setImage(cimg);
		
		g2d.dispose();
		g2d1.dispose();
	}
	
//	@Override 
//	public void writeExternal(ObjectOutput out) throws IOException {
//		  System.out.println("HelloWC");
//	}
//	  
//	@Override 
//	public void readExternal(ObjectInput in) throws IOException,ClassNotFoundException { 
//		  System.out.println("HelloRC");
//	}
	 
		
}
