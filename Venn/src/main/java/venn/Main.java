
package venn;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JPanel implements MouseListener {
	public final static int WIDTH = 500, HEIGHT = 500; // size of the window/frame

	private boolean triggered, pressedCancel, addClicked; // booleans for checking if certain buttons are pressed
	private JFrame frame; // declares frame
	private int xPos, yPos; // variables for x and y position for the mouse
	private String strInput; // string variable for inputting Text that will display on the frame
	private int numClicks; // the number of mouse clicks on the frame
	private JButton btnAdd; // Add button to add more circles TEST COMMENT

	public Main() {
		// initializes the frame to have certain properties
		frame = new JFrame("Customizable Venn Diagram"); // inside "" is the name for the window/frame
		frame.setSize(WIDTH, HEIGHT); // sets the frame to have size of 500 by 500
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame when x button is clicked (top right
																// button)
		frame.setLocationRelativeTo(null);// sets the location of the frame to be in the middle.
		frame.setResizable(false); // makes the frame not resizable (constant size for frame)
		frame.setLayout(null); // sets absolute layout for the frame, so when adding stuff you have to set
								// x,y,width, and height.
		frame.add(this); // adds JPanel to the frame
		frame.setVisible(true); // sets frame visible to true
		this.setSize(WIDTH, HEIGHT); // sets JPanel's size to the same size as frame
		frame.addMouseListener(this); // adds mouse listener to the frame so mouse related actions can be performed
		// this.addMouseMotionListener(this);

		// default initialization for triggered, pressedCancel, xPos, yPos, strInput and
		// numClicks
		triggered = false;
		pressedCancel = false;
		xPos = WIDTH / 2;
		yPos = HEIGHT / 2;
		strInput = "Test";
		numClicks = 0;

		// creates a new button on the frame
		btnAdd = new JButton("ADD");
		btnAdd.setBounds((WIDTH / 2) - 50, HEIGHT - 100, 100, 50); // sets the button to be in the middle of the frame
		btnAdd.addMouseListener(this); // adds mouse listener to the button so mouse related actions can be performed
		frame.add(btnAdd); // adds button to the frame

		// circles = new ArrayList<Circle>();

//		circles.add(new Circle(WIDTH - (WIDTH/4), HEIGHT/2, 100, 100));
//		circles.add(new Circle(WIDTH/4, HEIGHT/2, 100, 100));

	}

	Color yellow1 = new Color(255, 255, 127);
	Color blue1 = new Color(137, 207, 240);

	@Override
	public void paintComponent(Graphics g) {
		// This code will be changed later but for now..

		// draws default 2 circles when frame is displayed
		if (numClicks == 0) {
			// sets the colour of the first circle to yellow
			g.setColor(yellow1);
			// draws the first circle to be in the middle and to the left
			g.fillOval((WIDTH / 4) - 50, HEIGHT / 2 - 100, 200, 200);

			// sets the colour of the second circle to blue
			g.setColor(blue1);
			// draws the second circle to be in the middle and to the right
			g.fillOval((WIDTH / 4) + 70, HEIGHT / 2 - 100, 200, 200);
		}

		// adds new circles as you click add button
		if (addClicked) {
			// draws new circles but each circle's height is decreased by the number of
			// clicks on the frame
			// for example if you have clicked 5 times on the frame so far then it will
			// reduce circle's height by 5.
			// g.drawOval(x, y, width, height) <-- input arguments are as follows
			g.drawOval((WIDTH / 4) - 50, HEIGHT / 2 - 100, 200, 200 - numClicks);
			addClicked = false;
		}

		// adds new text on the screen when clicked on the frame
		if (triggered) {
			g.drawString(strInput, xPos, yPos);
			triggered = false;
			strInput = "Test";
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		if ((WIDTH / 4) - 50 <= arg0.getX() && arg0.getX() <= (WIDTH / 4) + 270) { // Boundary in the X component of the
																					// screen
			if ((HEIGHT / 2) - 100 <= arg0.getY() && arg0.getY() <= (HEIGHT / 2) + 100) { // Boundary in the Y component
																							// of the screen
				if (arg0.getSource() != btnAdd) { // if mouse is not clicked on the button add then
					numClicks++; // increases clicks done on the frame
					xPos = arg0.getX(); // gets mouse x pos
					yPos = arg0.getY();// gets mouse y pos

					try {
						// takes the input from the user but if user enters nothing or "Test" then it
						// will loop again until
						// they have the input right
						do {
							strInput = JOptionPane.showInputDialog("Enter Text: ", "0");
						} while (strInput.equals(null) || strInput.equals("Test"));
					} catch (Exception e) {
						// when user presses cancel on the dialog then it will throw error and catch
						// block will run
						// Therefore, when cancel is pressed it will set the boolean pressedCancel to
						// true and text to the default value
						strInput = "Test";
						pressedCancel = true;
					}

					// this is will print the user text if they haven't clicked cancel on the dialog
					if (!pressedCancel) {
						triggered = true;
						paintComponent(this.getGraphics());
					}

					// sets the pressedCancel to have default value
					pressedCancel = false;

				} else if (arg0.getSource() == btnAdd) { // if mouse is clicked on the button add then
					numClicks++; // increases the clicks done on the frame
					addClicked = true; // sets the addClicked to true
					paintComponent(this.getGraphics()); // calls the paintComponent method to draw more circles
				}
			}
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
