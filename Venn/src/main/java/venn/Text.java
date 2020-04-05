package venn;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

public class Text extends JLabel {

	public Text(int index,Object[] data) {
		//data array has 5 elements
		//0-font, 1-size, 2-forecolor, 3-backcolor, 4-opaqueness, 5-text
		String font = data[0].toString();
		String size = data[1].toString();
		String text = data[5].toString();
		Color foreColor = (Color)data[2];
		Color backColor = (Color)data[3];
		boolean isOpaque = (boolean) data[4];
		
		this.setText(text);
		this.setOpaque(isOpaque);
		
		TextInfo ti = new TextInfo(text,foreColor);
		ti.setBackColor(backColor);
		ti.setFont(font);
		ti.setSize(size);
		ti.setOpaque(isOpaque);
		
		MainFrame.TI.add(index,ti);
		
		
		this.addMouseListener(new MouseHandler(this));
		this.addMouseMotionListener(new MouseHandler(this));
		this.setBounds(MainFrame.WIDTH/4-50, MainFrame.HEIGHT/4-50, 100, 100);
	}
	
	
private static class MouseHandler implements MouseListener, MouseMotionListener {
	private JLabel lbl;
	private static int x, y;
	public MouseHandler(JLabel lbl) {
		this.lbl = lbl;
		x = y = 0;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int dx = e.getXOnScreen() - x;
		int dy = e.getYOnScreen() - y;
		this.lbl.setBounds(this.lbl.getX()+dx, this.lbl.getY()+dy, 100, 100);
		x += dx;
		y += dy;
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getXOnScreen();
		y = e.getYOnScreen();
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
