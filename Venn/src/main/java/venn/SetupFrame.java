package venn;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

public class SetupFrame extends JFrame{
	private JButton btnBack, btnCreate,btnAdvanced;
	private JTextField txtName;
	private JLabel lblName, lblCircles, lblBasic, lblAdvanced;
	private JSlider sldrCircles;
	private final int WIDTH = 600, HEIGHT = 400, NUM_CIRCLES_MIN = 2, NUM_CIRCLES_MAX = 7, NUM_CIRCLES_INIT = 2;
	
	public SetupFrame() {
		//initialize frame components
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel basic = new JPanel();
		basic.setSize(WIDTH/2, HEIGHT-100);
		basic.setLayout(null);
		basic.setVisible(true);
		
		lblBasic = new JLabel("Basic");
		lblBasic.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblBasic.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasic.setVerticalAlignment(SwingConstants.CENTER);
		lblBasic.setBounds(0, 20, (WIDTH/2)-4, 50);
		basic.add(lblBasic);
		
		lblName = new JLabel("Enter a name for Venn Diagram: ");
		lblName.setBounds(20, 100, 200, 50);
		basic.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(20, 150, 200, 30);
		basic.add(txtName);
		
		lblCircles = new JLabel("Choose the number of circles to create: ");
		lblCircles.setBounds(20, 200, 200, 50);
		basic.add(lblCircles);
		
		sldrCircles = new JSlider(JSlider.HORIZONTAL, NUM_CIRCLES_MIN, NUM_CIRCLES_MAX, NUM_CIRCLES_INIT);
		sldrCircles.setBounds(20, 250, 200, 50);
		sldrCircles.setMajorTickSpacing(1);
		sldrCircles.setMinorTickSpacing(1);
		sldrCircles.setPaintLabels(true);
		sldrCircles.setPaintTicks(true);
		sldrCircles.setSnapToTicks(true);
		basic.add(sldrCircles);
		
		
		JPanel advanced = new JPanel();
		advanced.setLayout(null);
		advanced.setVisible(true);
		
		JPanel bottom = new JPanel();
		bottom.setSize(WIDTH,100);
		bottom.setVisible(true);
		
		btnBack = new JButton("Back");
		btnBack.setBounds((WIDTH/2)-100, HEIGHT-100, 100, 50);
		bottom.add(btnBack);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds((WIDTH/2), HEIGHT-100, 100, 50);
		bottom.add(btnCreate);
		
		
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
	}
	
}
