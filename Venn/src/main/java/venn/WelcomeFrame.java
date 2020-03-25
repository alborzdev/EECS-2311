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
		
		
		JLabel lblLogo = new JLabel(new ImageIcon("Resources/StatVenn_Edited.png"));
		lblLogo.setBounds(108, 181, 800, 400);
		frame.add(lblLogo);
		
		btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		btnContinue.setBounds(418, 578, 191, 74);
		btnContinue.addActionListener(this);
		frame.getContentPane().add(btnContinue);
		frame.setVisible(true); // sets frame visible to true

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnContinue) {
			this.frame.dispose();
			CustomizeFrame frame = new CustomizeFrame();
		}
	}

}
