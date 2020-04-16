package venn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

public class Text extends JLabel {
	
	public Text(int index) {
		TextInfo ti = MainFrame.TI.get(index);
		this.setText(ti.getText());
		this.setFont(ti.getFont());
		this.setOpaque(ti.isOpaque());
		this.setForeground(ti.getForeColor());
		this.setBackground(ti.getBackColor());
		
		this.addMouseListener(new MouseHandler(this));
		this.addMouseMotionListener(new MouseHandler(this));
		this.setBounds(MainFrame.WIDTH/4-50, MainFrame.HEIGHT/4-50, 9*ti.getText().length(),40);
	}
	
	public Text(int index,Object[] data) {
		//data array has 5 elements
		//0-font, 1-forecolor, 2-backcolor, 3-opaqueness, 4-text
		Font font = (Font)data[0];
		Color foreColor = (Color)data[1];
		Color backColor = (Color)data[2];
		boolean isOpaque = (boolean) data[3];
		String text = data[4].toString();

		this.setText(text);
		this.setFont(font);
		this.setOpaque(isOpaque);
		this.setForeground(foreColor);
		this.setBackground(backColor);
		
		TextInfo ti = new TextInfo(text);
		ti.setForeColor(foreColor);
		ti.setBackColor(backColor);
		ti.setFont(font);
		ti.setOpaque(isOpaque);
		
		MainFrame.TI.add(index,ti);
		
		
		this.addMouseListener(new MouseHandler(this));
		this.addMouseMotionListener(new MouseHandler(this));
		this.setBounds(MainFrame.WIDTH/4-50, MainFrame.HEIGHT/4-50, 100, 40);
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
		this.lbl.setBounds(this.lbl.getX()+dx, this.lbl.getY()+dy, this.lbl.getWidth(), this.lbl.getHeight());
		if(lbl.getPreferredSize().getWidth() <= 100) {
			lbl.setSize(lbl.getPreferredSize());
		}else{
			Dimension d = lbl.getPreferredSize();
			lbl.setSize((int)(d.getWidth()+(100-d.getWidth())),(int)d.getHeight());
		}
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
