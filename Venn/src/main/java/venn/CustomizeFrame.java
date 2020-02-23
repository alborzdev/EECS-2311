package venn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CustomizeFrame extends JFrame implements ActionListener{
	private JButton btnBack, btnCreate,btnAdvance, btnColor;
	private JTextField txtName;
	private JLabel lblName, lblCircles, lblBasic, lblAdvance, lblFont, lblSize, lblColor;
	private JSlider sldrCircles;
	private JComboBox fontList, sizeList, colorList;
	private JCheckBox boxUseAdvance;
	private final int WIDTH = 800, HEIGHT = 400, NUM_CIRCLES_MIN = 2, NUM_CIRCLES_MAX = 7, NUM_CIRCLES_INIT = 2;
	
	public CustomizeFrame() {
		
		//initialize frame components
		setTitle("Customizable Venn Diagram");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		

/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////BASIC PANE RELATED STUFF////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel basic = new JPanel();
		basic.setBounds(0,0,(WIDTH/2)-2, HEIGHT-100);
		basic.setLayout(null);
		basic.setVisible(true);
		
		lblBasic = new JLabel("Basic Options");
		lblBasic.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblBasic.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasic.setVerticalAlignment(SwingConstants.CENTER);
		lblBasic.setBounds(0, 20, WIDTH/2, 50);
		basic.add(lblBasic);
		
		lblName = new JLabel("Enter a name for the Venn Diagram: ");
		lblName.setBounds(20, 70, 230, 50);
		basic.add(lblName);
		
		txtName = new JTextField();
		txtName.setText("Venn Diagram");
		txtName.setBounds(20, 120, 200, 30);
		basic.add(txtName);
		
		lblCircles = new JLabel("Choose the number of circles to create: ");
		lblCircles.setBounds(20, 170, 400, 50);
		basic.add(lblCircles);
		
		sldrCircles = new JSlider(JSlider.HORIZONTAL, NUM_CIRCLES_MIN, NUM_CIRCLES_MAX, NUM_CIRCLES_INIT);
		sldrCircles.setBounds(20, 220, 200, 50);
		sldrCircles.setMajorTickSpacing(1);
		sldrCircles.setMinorTickSpacing(1);
		sldrCircles.setPaintLabels(true);
		sldrCircles.setPaintTicks(true);
		sldrCircles.setSnapToTicks(true);
		basic.add(sldrCircles);
		

/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////ADVANCED PANE RELATED STUFF/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel advanced = new JPanel();
		advanced.setBounds((WIDTH/2)+2,0, WIDTH, HEIGHT-100);
		advanced.setLayout(null);
		advanced.setVisible(true);
		

		lblAdvance = new JLabel("Advance Options");
		lblAdvance.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblAdvance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvance.setVerticalAlignment(SwingConstants.CENTER);
		lblAdvance.setBounds(0, 20, WIDTH/2, 50);
		advanced.add(lblAdvance);
		
		lblFont = new JLabel("Selected Font: ");
		lblFont.setBounds(20, 70, 100, 50);
		advanced.add(lblFont);
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		List<String> tempList = Arrays.asList(fonts);		
		ArrayList<String> fontsList = new ArrayList<>(tempList);
		fontsList.add(0, "Default");
		
		fonts = fontsList.toArray(new String[fontsList.size()]);
		
		fontList = new JComboBox(fonts);
		fontList.setSelectedIndex(0);
		fontList.setBounds(lblFont.getX()+lblFont.getWidth()+20, 80,100, 30);
		advanced.add(fontList);
		
		lblSize = new JLabel("Selected Size: ");
		lblSize.setBounds(20,140, 100, 50);
		advanced.add(lblSize);
		
		String[] sizes = {"Default", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"};
		
		sizeList = new JComboBox(sizes);
		sizeList.setSelectedIndex(0);
		sizeList.setBounds(lblSize.getX()+lblSize.getWidth()+20, 150, 100, 30);
		advanced.add(sizeList);
		
		lblColor = new JLabel("Selected Color: ");
		lblColor.setBounds(20,210,100, 50);
		advanced.add(lblColor);
		
		btnColor = new JButton();
		btnColor.setBounds(lblColor.getX()+lblColor.getWidth()+20, 220, 100, 30);
		btnColor.setBackground(Color.BLACK);
		btnColor.addActionListener(this);
		advanced.add(btnColor);
		
/////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////BOTTOM PANE RELATED STUFF/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel bottom = new JPanel();
		bottom.setBounds(0,HEIGHT-100, WIDTH,100);
		bottom.setLayout(null);
		bottom.setVisible(true);
		
		btnBack = new JButton("Back");
		btnBack.setBounds((WIDTH/2)-100, 0, 100, 50);
		btnBack.addActionListener(this);
		//bottom.add(btnBack);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds((WIDTH/2)-50, 0, 100, 50);
		btnCreate.addActionListener(this);
		bottom.add(btnCreate);
		
		boxUseAdvance = new JCheckBox("Use Advanced Settings", false);
		boxUseAdvance.setBounds((WIDTH/2)+54, 0, 200, 50);
		bottom.add(boxUseAdvance);
		
		JSplitPane split = new JSplitPane();
		split.setBounds(0, 0, WIDTH, HEIGHT/2);
		split.setDividerSize(4);
		split.setDividerLocation(WIDTH/2);
		split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split.setLeftComponent(basic);
		split.setRightComponent(advanced);
		
		JSplitPane split2 = new JSplitPane();
		split2.setSize(WIDTH, 100);
		split2.setTopComponent(split);
		split2.setBottomComponent(bottom);
		split2.setDividerLocation(HEIGHT-100);
		split2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split2.setDividerSize(0);
		
		
		
		add(split2);
		setVisible(true);
		
		//Welcome popup
		JOptionPane.showMessageDialog(this, "Welcome to Venn Diagram Application!");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnCreate) {
			Settings s = new Settings();
			Settings.setName(txtName.getText());
			Settings.setNumCircles(sldrCircles.getValue());
			if(boxUseAdvance.isSelected()) {
				String size = sizeList.getSelectedItem().toString();
				String font = fontList.getSelectedItem().toString();
				int sizeValue = 10;
				if(size.equals("Default") && !font.equals("Default")) {
					Settings.setFont(new Font(font, Font.PLAIN, sizeValue));
				}else if(!size.equals("Default") && font.equals("Default")) {
					sizeValue = Integer.valueOf(size);
					Settings.setFont(new Font("Arial", Font.PLAIN, sizeValue));
				}else if(!size.equals("Default") && !font.equals("Default")) {
					sizeValue = Integer.valueOf(size);
					Settings.setFont(new Font(font, Font.PLAIN, sizeValue));
				}
				Settings.setColor(btnColor.getBackground());
			}
			System.out.println(Settings.name + " " + Settings.numCircles + " " + Settings.color.toString() + " " + Settings.font.toString());
			dispose();
			Main m = new Main();
			
		}else if(e.getSource() == btnColor) {
			Color newC = JColorChooser.showDialog(this, "Select a Color", Color.white);			
			btnColor.setBackground(newC);
			
		}
		
	}
	
}

