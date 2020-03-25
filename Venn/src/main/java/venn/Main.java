
package venn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JPanel implements MouseListener {
	public final static int WIDTH = 1000, HEIGHT = 700; // size of the window/frame

	private boolean triggered, pressedCancel, addClicked; // booleans for checking if certain buttons are pressed
	private JFrame frame; // declares frame
	private int xPos, yPos; // variables for x and y position for the mouse
	private boolean circlesChosen3;
	private boolean circlesChosen4;
	private double maxBoundYellow; // Maximum bound for first circle (circle on left, yellow)
	private double maxBoundBlue; // Maximum bound for second circle (circle on right, skyblue)
	private double maxBoundWhite; // Maximum bound for third circle (circle on top, white)
	private double maxBoundGreen; // Maximum bound for third circle (circle on top, white)
	private String yellowLegendTitle;
	private boolean yellowLegend; //a check to see if the legend for any circle has been displayed
	private boolean yellowLegendAdded;
	private boolean blueLegend;
	private boolean blueLegendAdded;
	private boolean yellowAndBlueLegend;
	private boolean yellowAndBlueLegendAdded;
	private boolean notLegend;
	private boolean notLegendAdded;
	private boolean whiteLegend;
	private boolean greenLegend;
	private int circleRadius; //radius of circle
	private String strInput; // string variable for inputting Text that will display on the frame
	private int numClicks; // the number of mouse clicks on the frame
	private int addClicks = 0; //number of clicks on the "Add" button
	private JButton btnAdd; // Add button to add more circles 
	public JButton btnStats;
	public JTextField txtNameYellow;
	public JTextField txtNameBlue;
	
	public JTextField lblYellowCircleName;
	public JTextField lblBlueCircleName;
	
	public JTextField lblInputYellowLeg;
	public JTextField lblInputBlueLeg;
	
	public JTextField lblInputYellowCircle;
	public JTextField lblInputBlueCircle;
	
	private JTextField lblInputYellowBlueCircle;
	private JTextField lblInputNot;
	
	private double yellowCircleInput;
	private double blueCircleInput;
	private double yellowBlueCircleInput;
	private double notInput;
	private int intInput;
	private JTextArea outputStats;
	private String yellowCircleName;
	private String blueCircleName;
	private boolean btnStatsPressed;
	
	
	
	public Main() {
		// initializes the frame to have certain properties
		frame = new JFrame(Settings.name); // inside "" is the name for the window/frame
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
		//btnAdd = new JButton("ADD");
		//btnAdd.setBounds((WIDTH / 2) - 50, HEIGHT - 100, 100, 50); // sets the button to be in the middle of the frame
		//btnAdd.addMouseListener(this); // adds mouse listener to the button so mouse related actions can be performed
		//frame.add(btnAdd); // adds button to the frame
		
		btnStats = new JButton("Display Stats");
		btnStats.setBounds(700, 50, 200, 50);
		btnStats.addMouseListener(this);
		frame.add(btnStats);
	}

	Color yellow = new Color(1f, 0.9f, 0.3f, (float) 0.5);
	Color black = new Color(0f, 0f, 0f);
	Color blue = new Color(0.6f, 0.7f, 0.9f, (float) 0.5);
	Color white = new Color(1f, 1f, 1f, (float) 0.6);
	Color lightGreen = new Color(0.5f, 0.9f, 0.6f, (float) 0.5);
	Color magenta = new Color(0.8f, 0.6f, 0.7f, (float) 0.3);

	@Override
	public void paintComponent(Graphics g) {
		// This code will be changed later but for now..
		
		// draws default 2 circles when frame is displayed
		if (numClicks == 0) {
			g.setColor(black);
			g.drawRect(WIDTH/4, HEIGHT/4, WIDTH/2, HEIGHT/2);
			g.setColor(magenta);
			g.fillRect(WIDTH/4, HEIGHT/4, WIDTH/2, HEIGHT/2);
			g.setColor(black);
			g.drawOval((WIDTH / 2) - 170, HEIGHT / 2 - 100, 200, 200);
			// sets the colour of the first circle to yellow
			g.setColor(yellow);
			// draws the first circle to be in the middle and to the left
			g.fillOval((WIDTH / 2) - 170, HEIGHT / 2 - 100, 200, 200);

			g.setColor(black);
			g.drawOval((WIDTH / 2) - 30, HEIGHT / 2 - 100, 200, 200);
			// sets the colour of the second circle to blue
			g.setColor(blue);
			// draws the second circle to be in the middle and to the right
			g.fillOval((WIDTH / 2) - 30, HEIGHT / 2 - 100, 200, 200);

		}

		// adds new circles as you click add button
		if (addClicks == 1 && addClicked == true || Settings.numCircles >= 3 && circlesChosen3 == false)  {
			addClicks = 1;
			g.setColor(black);
			g.drawOval((WIDTH / 3), HEIGHT / 2 - 180, 200, 200);

			g.setColor(white);
			// draws the first circle to be in the middle and to the left
			g.fillOval((WIDTH / 3), HEIGHT / 2 - 180, 200, 200);
			circlesChosen3 = true;
			addClicked = false;
		} 
		if (addClicks == 2 && addClicked == true || Settings.numCircles == 4 && circlesChosen4 == false) {
			addClicks = 2;
			g.setColor(black);
			g.drawOval((WIDTH / 3), HEIGHT / 2 - 20, 200, 200);
			g.setColor(lightGreen);
			// draws the first circle to be in the middle and to the left
			g.fillOval((WIDTH / 3), HEIGHT / 2 - 20, 200, 200);
			circlesChosen4 = true;
			addClicked = false;
		}

		// adds new text on the screen when clicked on the frame
		if (triggered) {
			g.setFont(Settings.font);
			intInput = Integer.parseInt(strInput);
			if(yellowLegend && yellowLegendAdded == false) {
				
				
				yellowCircleInput = intInput;
				lblInputYellowCircle = new JTextField(strInput);
				lblInputYellowCircle.setBounds((WIDTH / 2) - 85, HEIGHT / 2 - 15, 30, 30);
				frame.add(lblInputYellowCircle);
				
				g.drawRect(50, 50, 20, 20);
				g.setColor(yellow);
				g.fillRect(50, 50, 20, 20);
				g.setColor(black);
				
				txtNameYellow = new JTextField();
				txtNameYellow.setText("Add Title");
				txtNameYellow.setBounds(75, 45, 100, 30);
				txtNameYellow.addMouseListener(this);
				frame.add(txtNameYellow);
				
				lblInputYellowLeg = new JTextField(strInput);
				lblInputYellowLeg.setBounds(75, 75, 70, 30);
				frame.add(lblInputYellowLeg);
	
				lblYellowCircleName = new JTextField("Add Title");
				lblYellowCircleName.setBounds((WIDTH / 3) + 40, HEIGHT / 2 - 135, 100, 30);
				this.add(lblYellowCircleName);
				
				yellowLegendAdded = true;
				yellowLegend = false;
			}
			else if(yellowLegend && yellowLegendAdded == true) {
				yellowCircleInput = intInput;
				
				lblInputYellowCircle.setText(strInput);
				lblInputYellowLeg.setText(strInput);
				
				
				yellowLegend = false;
				
			}
			if(blueLegend && blueLegendAdded == false) {
				blueCircleInput = intInput;
				lblInputBlueCircle = new JTextField(strInput);
				lblInputBlueCircle.setBounds((WIDTH / 2) + 55, HEIGHT / 2 - 15, 30, 30);
				frame.add(lblInputBlueCircle);
				
				g.drawRect(200, 50, 20, 20);
				g.setColor(blue);
				g.fillRect(200, 50, 20, 20);
				g.setColor(black);
				g.drawString("Add Title", 225, 63);
				txtNameBlue = new JTextField();
				txtNameBlue.setText("Add Title");
				txtNameBlue.setBounds(225, 45, 100, 30);
				txtNameBlue.addMouseListener(this);
				frame.add(txtNameBlue);
				
				lblInputBlueLeg = new JTextField(strInput);
				lblInputBlueLeg.setBounds(225, 75, 70, 30);
				frame.add(lblInputBlueLeg);
				
				lblBlueCircleName = new JTextField("Add Title");
				lblBlueCircleName.setBounds((WIDTH / 4)*2 + 30, HEIGHT / 2 - 135, 100, 30);
				frame.add(lblBlueCircleName);
				
				blueLegendAdded = true;
				blueLegend = false;
			}
			else if(blueLegend && blueLegendAdded == true) {
				blueCircleInput = intInput;
				lblInputBlueCircle.setText(strInput);
				lblInputBlueLeg.setText(strInput);
				blueLegend = false;
				
			}
			if(yellowAndBlueLegend && yellowAndBlueLegendAdded == false ) {
				yellowBlueCircleInput = intInput;
				lblInputYellowBlueCircle = new JTextField(strInput);
				lblInputYellowBlueCircle.setBounds((WIDTH / 2) - 15, HEIGHT / 2 - 15, 30, 30);
				frame.add(lblInputYellowBlueCircle);
				
				yellowAndBlueLegendAdded = true;
				yellowAndBlueLegend = false;
			}
			else if(yellowAndBlueLegend && yellowAndBlueLegendAdded == true ) {
				yellowBlueCircleInput = intInput;
				lblInputYellowBlueCircle.setText(strInput);
				yellowAndBlueLegend = false;
			}
			
			if(notLegend && notLegendAdded == false) {
				notInput = intInput;
				lblInputNot = new JTextField(strInput);
				lblInputNot.setBounds((WIDTH / 2) - 15, (HEIGHT / 2 + HEIGHT / 8) + 15, 30, 30);
				frame.add(lblInputNot);
				notLegendAdded = true;
				notLegend = false;
			}
			else if(notLegend && notLegendAdded == true) {
				notInput = intInput;
				lblInputNot.setText(strInput);
				notLegend = false;
			}
			
			triggered = false; 

		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

		xPos = arg0.getX(); // gets mouse x pos
		yPos = arg0.getY();// gets mouse y pos
		

		// Max bounds for each circle by colour
		maxBoundYellow = Math.sqrt((xPos - ((WIDTH / 2) - 70)) * (xPos - ((WIDTH / 2) - 70)) + (yPos - HEIGHT / 2) * (yPos - HEIGHT / 2));
		maxBoundBlue = Math.sqrt((xPos - ((WIDTH / 2) + 70)) * (xPos - ((WIDTH / 2) + 70)) + (yPos - HEIGHT / 2) * (yPos - HEIGHT / 2));
		maxBoundWhite = Math.sqrt((xPos - 333) * (xPos - 333) + (yPos - 294) * (yPos - 294));
		maxBoundGreen = Math.sqrt((xPos - 333) * (xPos - 333) + (yPos - 456) * (yPos - 456));

		// Radius of circle (its smaller than 100 so the number actually appears in the
		// circle, small bug)
		circleRadius = 100;
		
		//Setting the title text boxes as editable or non editable
		if(arg0.getSource() != txtNameYellow && yellowLegendAdded) {
			
			txtNameYellow.setEditable(false);
			yellowCircleName = txtNameYellow.getText();
			lblYellowCircleName.setText(yellowCircleName);
			
		} else if(arg0.getSource() == txtNameYellow && yellowLegendAdded) {
			
			txtNameYellow.setEditable(true);
			txtNameYellow.setEnabled(true);
		}
		if(arg0.getSource() != txtNameBlue && blueLegendAdded) {
			
			txtNameBlue.setEditable(false);
			blueCircleName = txtNameBlue.getText();
			lblBlueCircleName.setText(blueCircleName);
			
		} else if(arg0.getSource() == txtNameBlue && blueLegendAdded) {
			
			txtNameBlue.setEditable(true);
			txtNameBlue.setEnabled(true);
			
		}
		
		
		
		
		// This if block is checking if the btnAdd is not clicked and if there are ONLY
		// 2 CIRCLES, then do the following
		if (arg0.getSource() != btnAdd && addClicks == 0
				&& (xPos > WIDTH/4 && xPos < (WIDTH/4 + WIDTH/2) && yPos > HEIGHT/4 && yPos < (HEIGHT/4 + HEIGHT/2))) { 
			numClicks++; // increases clicks done on the frame
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
				if (maxBoundYellow < circleRadius && maxBoundBlue > circleRadius) { //check to see if click is ONLY in yellow circle
					yellowLegend = true;
					
				} //later, make the "yellowLegendAdded" boolean false if the circle is REMOVED (will be implemented at a later time)
				else if(maxBoundYellow > circleRadius && maxBoundBlue < circleRadius) { //check to see if click is ONLY in blue circle
					blueLegend = true;
				}
				else if(maxBoundYellow < circleRadius && maxBoundBlue < circleRadius) {
					yellowAndBlueLegend = true;
				}
				else if((xPos > WIDTH/4 && xPos < (WIDTH/4 + WIDTH/2) && yPos > HEIGHT/4 && yPos < (HEIGHT/4 + HEIGHT/2))
						&& !(maxBoundYellow < circleRadius || maxBoundBlue < circleRadius)) {
					notLegend = true;
				}
				paintComponent(this.getGraphics());
			}

			// sets the pressedCancel to have default value
			pressedCancel = false;
		}
		// This if block is checking if the btnAdd is not clicked and if there are ONLY
		// 3 CIRCLES, then do the following
		else if (arg0.getSource() != btnAdd && addClicks == 1
				&& (maxBoundYellow < circleRadius || maxBoundBlue < circleRadius || maxBoundWhite < circleRadius)) { 

			numClicks++; // increases clicks done on the frame
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
		} else if (arg0.getSource() != btnAdd && addClicks == 2 && (maxBoundYellow < circleRadius
				|| maxBoundBlue < circleRadius || maxBoundWhite < circleRadius || maxBoundGreen < circleRadius)) { 																													
			numClicks++; // increases clicks done on the frame
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
		}

		if (arg0.getSource() == btnAdd) { // if mouse is clicked on the button add then
			numClicks++; // increases the clicks done on the frame
			addClicks++;
			addClicked = true; // sets the addClicked to true
			paintComponent(this.getGraphics()); // calls the paintComponent method to draw more circles
		}
		if (arg0.getSource() == btnStats) { // if mouse is clicked on the button add then
			statsCrunch();
		}
		//TEST OUTPUT
		System.out.println(xPos + " x\ny " + yPos + "\n" + addClicks);
		System.out.println(yellowLegend + "\n" + blueLegend + "\n" + yellowAndBlueLegend);

	}
	
	public void statsCrunch() {
		DecimalFormat df = new DecimalFormat("0.0%");
		double population = yellowCircleInput + blueCircleInput + yellowBlueCircleInput + notInput;	
		double chooseYellow = yellowCircleInput/population;
		double chooseBlue = blueCircleInput/population;
		double chooseYellowBlue = yellowBlueCircleInput/population;
		double chooseNot = notInput/population;
		
		if(btnStatsPressed == false) {
		
		outputStats = new JTextArea("Population Size: " + population
						+ "\n\nOdds of Choosing " + yellowCircleName + ": " + df.format(chooseYellow)
						+ "\nOdds of Choosing " + blueCircleName + ": " + df.format(chooseBlue)
						+ "\nOdds of Choosing " + yellowCircleName + " & " + blueCircleName + ": " + df.format(chooseYellowBlue)
						+ "\nOdds of NOT Choosing " + yellowCircleName + " OR " + blueCircleName + ": " + df.format(chooseNot));
		outputStats.setLineWrap(true);
		outputStats.setWrapStyleWord(true);
		outputStats.setBounds(30, 200, 200, 300);
		System.out.println(yellowCircleName + " " + blueCircleName);
		
		frame.add(outputStats);
		btnStatsPressed = true;
		}
		else {
			outputStats.setText("Population Size: " + population
					+ "\n\nOdds of Choosing " + yellowCircleName + ": " + df.format(chooseYellow)
					+ "\nOdds of Choosing " + blueCircleName + ": " + df.format(chooseBlue)
					+ "\nOdds of Choosing " + yellowCircleName + " & " + blueCircleName + ": " + df.format(chooseYellowBlue)
					+ "\nOdds of NOT Choosing " + yellowCircleName + " OR " + blueCircleName + ": " + df.format(chooseNot));
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
