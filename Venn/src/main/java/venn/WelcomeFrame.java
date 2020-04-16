package venn;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomeFrame implements ActionListener {

	public JButton btnContinue;
	public JFrame frame;
	public JButton btnCreate;
	public JButton btnExit;

	public WelcomeFrame() {

		frame = new JFrame("Welcome");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);// sets the location of the frame to be in the middle.
		frame.setResizable(false); // makes the frame not resizable (constant size for frame)
		frame.getContentPane().setLayout(null);

		JLabel lblWelcomeToThe = new JLabel("Welcome to the Customizable Venn Diagram");
		lblWelcomeToThe.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblWelcomeToThe.setBounds(108, 11, 815, 170);
		frame.getContentPane().add(lblWelcomeToThe);
		
		
		JLabel lblLogo = new JLabel(new ImageIcon("Resources/Logo.png"));
		lblLogo.setBounds(108, 181, 800, 400);
		frame.add(lblLogo);
		
		btnContinue = new JButton("Open");
		btnContinue.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		btnContinue.setBounds(418, 578, 191, 74);
		btnContinue.addActionListener(this);
		frame.getContentPane().add(btnContinue);
		frame.setVisible(true); // sets frame visible to true
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		btnExit.setBounds(200, 578, 191, 74);
		btnExit.addActionListener(this);
		frame.getContentPane().add(btnExit);
		frame.setVisible(true); // sets frame visible to true
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		btnCreate.setBounds(650, 578, 191, 74);
		btnCreate.addActionListener(this);
		frame.getContentPane().add(btnCreate);
		frame.setVisible(true); // sets frame visible to true

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnContinue || arg0.getSource() == btnCreate) {
			this.frame.dispose();
			MainFrame frame = new MainFrame();
		} else if(arg0.getSource() == btnExit) {
			this.frame.dispose();
		}
	}

}
