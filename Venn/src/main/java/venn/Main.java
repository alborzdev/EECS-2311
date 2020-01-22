package venn;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JPanel implements MouseListener{
	public final static int WIDTH = 500, HEIGHT = 500;
	private boolean triggered, pressedCancel, addClicked;
	private JFrame frame;
	private int xPos, yPos;
	private String strInput;
	private int numClicks;
	private JButton btnAdd;
	
	public Main() {
		frame = new JFrame("Customizable Venn Diagram");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);// set the location of the frame to be in the middle.
		frame.setResizable(false);
		frame.setLayout(null);
		frame.add(this);
		frame.setVisible(true);
		this.setSize(WIDTH,HEIGHT);
		frame.addMouseListener(this);
		//this.addMouseMotionListener(this);
		triggered = false;
		pressedCancel = false;
		xPos = WIDTH/2;
		yPos = HEIGHT/2;
		strInput = "Test";
		numClicks = 0;
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds((WIDTH/2)-50, HEIGHT-100, 100, 50);
		btnAdd.addMouseListener(this);
		frame.add(btnAdd);
		
	//	circles = new ArrayList<Circle>();
		
//		circles.add(new Circle(WIDTH - (WIDTH/4), HEIGHT/2, 100, 100));
//		circles.add(new Circle(WIDTH/4, HEIGHT/2, 100, 100));
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
	
		if(numClicks == 0) {
			g.drawOval((WIDTH/4)-50, HEIGHT/2-100, 200, 200);
			g.drawOval((WIDTH/4)+70, HEIGHT/2-100, 200, 200);
		}
		if(addClicked) {
			g.drawOval((WIDTH/4)-50, HEIGHT/2-100, 200, 200-numClicks);
			addClicked = false;
		}
		if(triggered) {
			g.drawString(strInput, xPos, yPos);
			triggered = false;
			strInput = "Test";
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() != btnAdd) {
			numClicks++;
			xPos = arg0.getX();
			yPos = arg0.getY();
			try {
				do {
					strInput = JOptionPane.showInputDialog("Enter Text: ", "0");
				}while(strInput.equals(null) || strInput.equals("Test"));
			}catch(Exception e) {
				strInput = "Test";
				pressedCancel = true; //when they press cancel on JOptionPane
			}
			if(!pressedCancel) {
				triggered = true;
				paintComponent(this.getGraphics());
			}
			pressedCancel = false;
		}else if(arg0.getSource() == btnAdd) {
			numClicks ++;
			addClicked = true;
			paintComponent(this.getGraphics());
		}
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
		if(arg0.getSource() == btnAdd) {
			System.out.print("HELLO");
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void mouseDragged(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

	

}
