package venn;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

public class Text extends JLabel {
	
	public Text(String txt) {
		this.setText(txt);
		this.setOpaque(false);
		this.addMouseListener(new MouseHandler(this));
		this.addMouseMotionListener(new MouseHandler(this));
		this.setBounds(MainFrame.WIDTH/4-50, MainFrame.HEIGHT/4-50, 100, 100);
	}
	
	
private static class MouseHandler implements MouseListener, MouseMotionListener {
	private JLabel lbl;
	private int x, y;
	public MouseHandler(JLabel lbl) {
		this.lbl = lbl;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int dx = e.getX() - x;
		int dy = e.getY() - y;
		System.out.println("HELLO");
		this.lbl.setBounds(this.lbl.getX()+dx, this.lbl.getY()+dy, 100, 100);
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
		x = e.getX();
		y = e.getY();
		System.out.println("HELLO");
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
