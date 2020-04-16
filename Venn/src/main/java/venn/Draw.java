package venn;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.DebugGraphics;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Draw extends JPanel{
	private int endX, endY;
	public static ArrayList<Color> colors;
	public int index;
	public int size = 600;
	public static int SIZE=600;
	public Draw() {
		
		endX = 0;
		endY = 0;
		colors = new ArrayList<>();
		
	}
	
	//open mode
	public Draw(int index,String mode) {
		this.index = index;
		endX = MainFrame.CI.get(index).getX()+(SIZE/2);
		endY = MainFrame.CI.get(index).getY()+(SIZE/2);
		
		colors = new ArrayList<>();
		this.setOpaque(false);
		colors.add(MainFrame.CI.get(index).getColor());
		int x = MainFrame.CI.get(index).getX();
		int y = MainFrame.CI.get(index).getY();
		int size = MainFrame.CI.get(index).getSize();
		this.setBounds(x,y,size,size);
		
		this.addMouseListener(new MouseHandler(this));
		this.addMouseMotionListener(new MouseHandler(this));
		
		
	}
	
	
	//new mode
	public Draw(int index) {
		colors = new ArrayList<>();
		
		this.index = index;
		this.setOpaque(false);
		BufferedImage img = new BufferedImage(SIZE,SIZE,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
    	
		
		BufferedImage cimg = new BufferedImage(SIZE,SIZE,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d1 = cimg.createGraphics();
		g2d1.setClip(new Ellipse2D.Float(0,0,SIZE,SIZE));
		
		Color foreColor;
		do {
		float rr = (float) (Math.random());
		float rg = (float) (Math.random());
		float rb = (float) (Math.random());
		foreColor = new Color(rr,rg,rb,0.5f);
		}while(colors.contains(foreColor));
		
		colors.add(foreColor);
		g2d1.setColor(foreColor);
	    g2d1.fillOval(0, 0, SIZE, SIZE);
	    g2d1.setColor(Color.black);
	    g2d1.setStroke(new BasicStroke(2));
	    g2d1.drawOval(0+1,0+1,SIZE-2,SIZE-2);
	    g2d1.drawImage(img, 0, 0,SIZE,SIZE, null);
	    
	    
	    int[] bounds = MainFrame.generateCircleBounds();
	    endX = bounds[0]; //0 - X
		endY = bounds[1]; //1 - Y
		CircleInfo ci = new CircleInfo(2,SIZE,foreColor);
		ci.setX(endX-(SIZE/2));
		ci.setY(endY-(SIZE/2));
		ci.setImage(cimg);
        MainFrame.CI.add(this.index,ci);
        this.setBounds(endX-(SIZE/2), endY-(SIZE/2), SIZE, SIZE);
        this.addMouseListener(new MouseHandler(this));
        this.addMouseMotionListener(new MouseHandler(this));
		
        
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.drawImage(MainFrame.CI.get(index).getImage(),0,0,MainFrame.CI.get(index).getSize(),MainFrame.CI.get(index).getSize(),null);
        
       g2d.dispose();
       g.dispose();
    	
    }

	
	private static class MouseHandler implements MouseListener, MouseMotionListener {
		private Draw d;
		private static int x, y;
		public MouseHandler(Draw d) {
			this.d = d;
			x = 0;
			y= 0;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX()-x;
			int dy = e.getY()-y;
			this.d.setBounds(this.d.getX()+dx, this.d.getY()+dy, d.size, d.size);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

