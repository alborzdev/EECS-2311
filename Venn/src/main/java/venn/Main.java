package venn;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
	public final static int WIDTH = 500, HEIGHT = 500;
	public static boolean TRIGGERED = false;
	private JFrame frame;
	private JButton btnAdd;
	private List<Circle> circles;
	
	
	public Main() {
		frame = new JFrame("Customizable Venn Diagram");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.add(this);
		frame.setVisible(true);
		this.setSize(WIDTH,HEIGHT);
		this.repaint();
		circles = new ArrayList<Circle>();
		
//		circles.add(new Circle(WIDTH - (WIDTH/4), HEIGHT/2, 100, 100));
//		circles.add(new Circle(WIDTH/4, HEIGHT/2, 100, 100));
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawOval((WIDTH/4)-50, HEIGHT/2-100, 200, 200);
		g.drawOval((WIDTH/4)+70, HEIGHT/2-100, 200, 200);
	}


}
