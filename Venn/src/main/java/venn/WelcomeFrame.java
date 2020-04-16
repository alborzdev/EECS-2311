package venn;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

public class WelcomeFrame implements ActionListener {

	public JButton btnContinue;
	public JFrame frame;
	public JButton btnCreate;
	public JButton btnExit;

	public WelcomeFrame() {

		frame = new JFrame("Welcome Frame");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);// sets the location of the frame to be in the middle.
		frame.setResizable(false); // makes the frame not resizable (constant size for frame)
		frame.getContentPane().setLayout(null);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Customizable Venn Diagram");
		lblWelcomeToThe.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblWelcomeToThe.setBounds(108, 11, 815, 170);
		frame.getContentPane().add(lblWelcomeToThe);
		
		
		JLabel lblLogo = new JLabel(new ImageIcon(this.getClass().getResource("Logo.png")));
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
		if (arg0.getSource() == btnContinue) {
			JFileChooser jfc = new JFileChooser();
			String desktop = System.getProperty("user.home") + "/Desktop";
			
			
			jfc.setDialogTitle("Choose a File to Open");
				
			FileFilter jpegFilter = new CustomFilter(".jpeg", "JPEG");
			FileFilter pngFilter = new CustomFilter(".png", "PNG");
				
			jfc.addChoosableFileFilter(jpegFilter);
			jfc.addChoosableFileFilter(pngFilter);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setCurrentDirectory(new File(desktop));
			int response = jfc.showOpenDialog(frame);
			
			if(response == JFileChooser.APPROVE_OPTION && jfc.getSelectedFile().getParentFile().exists()) {
				File path = jfc.getSelectedFile();
				
				String name = "";
				
				if(path.getName().indexOf(".jpeg")!= -1) {
					name = path.getName().replaceAll(".jpeg", "");
				}else if(path.getName().indexOf(".png")!= -1){
					name = path.getName().replaceAll(".png", "");
				}else {
					name = path.getName();
				}
				
				
				if(new File(path.getParentFile().getAbsolutePath()+"/"+name+"C.txt").exists()) {
					if(new File(path.getParentFile().getAbsolutePath()+"/"+name+"T.txt").exists()) {
						
						javax.swing.SwingUtilities.invokeLater(new Runnable() {
				            public void run() {
								new MainFrame(MainFrame.MODE_OPEN, path.getAbsolutePath());
				            }
				        });
						
						frame.dispose();

					}else {
						JOptionPane.showMessageDialog(frame, "Please select any file created by this program!",
								"Error: One or more file doesn't exist!",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(frame, "Please select any file created by this program!",
								"Error: One or more file doesn't exist!",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		}else if(arg0.getSource() == btnCreate){
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
					new MainFrame(MainFrame.MODE_NEW, "");
	            }
	        });
			this.frame.dispose();
		}else if(arg0.getSource() == btnExit) {
			this.frame.dispose();
		}
	}
	
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				new WelcomeFrame();
            }
        });
	}
}