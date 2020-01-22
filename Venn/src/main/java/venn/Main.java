package venn;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main implements MouseListener, ActionListener {
	public final static int WIDTH = 500, HEIGHT = 500;
	public static boolean TRIGGERED = false;
	private JFrame frame;
	private JButton btnAdd;
	private List<Circle> circles;
	private MainPaint mp;
	
	public Main() {
		frame = new JFrame("Customizable Venn Diagram");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setVisible(true);

		
		mp = new MainPaint();
		mp.setSize(200,200);
		frame.add(mp);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds((WIDTH/2)-50, HEIGHT-100, 100, 50);
		btnAdd.addActionListener(this);
		frame.add(btnAdd);
	
		
		circles = new ArrayList<Circle>();
		
		circles.add(new Circle(WIDTH - (WIDTH/4), HEIGHT/2, 100, 100));
		repaint(circles.get(circles.size()-1));
		circles.add(new Circle(WIDTH/4, HEIGHT/2, 100, 100));
		repaint(circles.get(circles.size()-1));
		
		
		
	}
	
	public void repaint(Circle circle) {
		Main.TRIGGERED = true;
		mp.paintHelper(mp.getGraphics(),circle);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(btnAdd == arg0.getSource()) {
			circles.add(new Circle(WIDTH/2, HEIGHT/4, 100-Circle.NUM_CIRCLES, 100-Circle.NUM_CIRCLES));
			repaint(circles.get(circles.size()-1));
		}
		
		mp.repaint();
		
	}

}
