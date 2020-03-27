package venn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame implements MouseListener{
	public final static int WIDTH = 1366, HEIGHT = 768; // size of the window/frame

	private JFrame frame; // declares frame
	private JTable jtable;
	private JButton btnAdd, btnDel, btnEdit, btnAddText;
	private JTextField delAddCircles, delAddText, txtAddText;
	private JLayeredPane jlpane;
	public static int PANE_INDEX = 2, INDEX = 0;
	public static ArrayList<CircleInfo> CI;
	private JComboBox selectLayer;
	public static boolean DRAGGING = false;
	public MainFrame() {
		frame = new JFrame("Customizable Venn Diagram"); // inside "" is the name for the window/frame
		frame.setSize(WIDTH, HEIGHT); // sets the frame to have size of 500 by 500
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame when x button is clicked (top right															// button)
		frame.setLocationRelativeTo(null);// sets the location of the frame to be in the middle.
		frame.setResizable(false); // makes the frame not resizable (constant size for frame)
		frame.setLayout(null); // sets absolute layout for the frame, so when adding stuff you have to set					// x,y,width, and height.
		//frame.add(this); // adds JPanel to the frame
		frame.addMouseListener(this); // adds mouse listener to the frame so mouse related actions can be performed

		
		CI = new ArrayList<CircleInfo>();

		jlpane = new JLayeredPane();
		jlpane.setBorder(BorderFactory.createLineBorder(Color.black));
		jlpane.setBounds(10, 10, WIDTH-400, HEIGHT-100);
		jlpane.setOpaque(true);
		jlpane.setBackground(Color.white);
		jlpane.addMouseListener(this);
		//jlpane.addMouseMotionListener(this);
		
		frame.add(jlpane);
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Name");
		dtm.addColumn("Object Type");
		Object[] o = new Object[17];
		dtm.addRow(o);
		jtable = new JTable(dtm);

		JScrollPane jsp = new JScrollPane(jtable);
		jsp.setBounds(WIDTH-400+20, 10, 300, 300-5);
		jtable.setFillsViewportHeight(true);
		frame.add(jsp);
		
		btnAdd = new JButton("+");
		btnAdd.setBounds(WIDTH-400+20, jsp.getY()+jsp.getHeight()+20,75,40);
		btnAdd.addMouseListener(this);
		frame.add(btnAdd);
		
		btnDel = new JButton("-");
		btnDel.setBounds(btnAdd.getX()+75, jsp.getY()+jsp.getHeight()+20,75,40);
		btnDel.addMouseListener(this);
		frame.add(btnDel);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(btnDel.getX()+75,jsp.getY()+jsp.getHeight()+20,75,40);
		btnEdit.addMouseListener(this);
		frame.add(btnEdit);
		
		delAddCircles = new JTextField();
		delAddCircles.setBounds(btnEdit.getX()+75, jsp.getY()+jsp.getHeight()+20,75,40);
		frame.add(delAddCircles);
		
		txtAddText = new JTextField();
		txtAddText.setBounds(btnAdd.getX(), btnAdd.getY()+40+20,230,40);
		frame.add(txtAddText);
		
		delAddText = new JTextField();
		delAddText.setBounds(txtAddText.getX()+230+20, btnAdd.getY()+40+20, 50, 40);
		frame.add(delAddText);

		btnAddText = new JButton("ADD TEXT");
		btnAddText.setBounds(txtAddText.getX(),txtAddText.getY()+40+20,300,40);
		btnAddText.addMouseListener(this);
		frame.add(btnAddText);
		


		String[] layers = {"0","1","2","3","4","5","6","7","8"};
		selectLayer = new JComboBox(layers);
		selectLayer.setSelectedIndex(0);
		selectLayer.setBounds(WIDTH-200, 100, 100, 40);
		//frame.add(selectLayer);
		
	    jlpane.add(new Draw(100,100,INDEX),INDEX,0);
		System.out.println("SIZE: "+ CI.size() + "CC: " + jlpane.getComponentCountInLayer(0));
	
		INDEX++;
	    jlpane.add(new Draw(100,100,INDEX),INDEX,0);
		System.out.println("SIZE: "+ CI.size() + "CC: " + jlpane.getComponentCountInLayer(0));
		
		frame.setVisible(true);		
	}


	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if(arg0.getSource() == btnAdd) {
			jlpane.removeAll();
			jlpane.repaint();
			MainFrame.CI.clear();
			PANE_INDEX++;
			INDEX = 0;
			while(INDEX < PANE_INDEX) {
				jlpane.add(new Draw(100,100,INDEX),INDEX,0);
				System.out.println("HEY ADD");
				if(INDEX == PANE_INDEX-1) {
					break;
				}else {
					INDEX++;
				}
			}
		}else if(arg0.getSource() == btnAddText) {
			jlpane.add(new Text("HELLO " + PANE_INDEX));
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
	
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
	}
}
