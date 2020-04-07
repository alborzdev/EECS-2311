package venn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MainFrame implements MouseListener, KeyListener, ActionListener, ListSelectionListener{
	public final static int WIDTH = 1366, HEIGHT = 768; // size of the window/frame
	private final int MIN_CIRCLES = 2, MAX_CIRCLES = 7;
	private final String CIRCLE_TYPE = "Circle", TEXT_TYPE = "Text", PANEL_TYPE = "PANEL"; //PANEL_TYPE is the background jlayeredpanel
	private final String VALUE_DEFAULT = "Default", VALUE_CUSTOMIZE = "Customize";
	private final int SIZE_C_MIN = 100, SIZE_C_MAX = 600, SIZE_C_INIT = 600, SIZE_S_MIN = 0, SIZE_S_MAX = 10, SIZE_S_INIT = 2;
	private JSlider sliderSize, sliderStroke;
	private JFrame frame; // declares frame
	private JTable jtable;
	private JLabel lblForeColor,lblBackColor,lblFont,lblFSize, lblCSize, lblStroke,lblCColor;
	private JPanel panelCustomizeText, panelCustomizeCir, panelCustomizeMsg;
	private JCheckBox checkOpaque;
	private JSpinner addCircleAndText;
	private JButton btnAdd, btnDel, btnEdit, btnAddText, btnForeColor,btnBackColor, btnCColor;
	private JTextField delAddCircles, delAddText, txtAddText;
	private JLayeredPane jlpane;
	public static int PANE_INDEX = 2, CINDEX = 0, TINDEX = 0; //CINDEX = circle index, TINDEX = text index
	public static ArrayList<CircleInfo> CI;
	public static ArrayList<TextInfo> TI;
	private JComboBox selectLayer, sizeList, fontList;
	private JColorChooser jcc;
	public static boolean DRAGGING = false;
	public boolean btnForeClicked = true, btnCColorClicked = false;

	public MainFrame() {
		
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////FRAME RELATED STUFF/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		frame = new JFrame("Customizable Venn Diagram"); // inside "" is the name for the window/frame
		frame.setSize(WIDTH, HEIGHT); // sets the frame to have size of 500 by 500
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame when x button is clicked (top right															// button)
		frame.setLocationRelativeTo(null);// sets the location of the frame to be in the middle.
		frame.setResizable(false); // makes the frame not resizable (constant size for frame)
		frame.setLayout(null); // sets absolute layout for the frame, so when adding stuff you have to set					// x,y,width, and height.
		//frame.add(this); // adds JPanel to the frame
		frame.addMouseListener(this); // adds mouse listener to the frame so mouse related actions can be performed
		frame.getContentPane().addKeyListener(this);
		//try {
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////MENU BAR RELATED STUFF/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
			
		JMenuBar jmb = new JMenuBar();
		//jmb.addKeyListener(this);
		
		JMenu menuFile = new JMenu("File");
		menuFile.addActionListener(this);
		//menuFile.addKeyListener(this);
		JMenu menuHelp = new JMenu("Help");
		menuHelp.addActionListener(this);
		//menuHelp.addKeyListener(this);
		
		JMenuItem itemOpen = new JMenuItem("Open");
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		
		JMenuItem itemSave = new JMenuItem("Save");
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		JMenuItem itemSaveAs = new JMenuItem("SaveAs");
		itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		JMenuItem itemClose = new JMenuItem("Close");
		itemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		
		JMenuItem itemAbout = new JMenuItem("About");
		itemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		
		JMenuItem itemSearch = new JMenuItem("Search");
		itemSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));

		
		menuFile.add(itemOpen);
		menuFile.addSeparator();
		menuFile.add(itemSave);
		menuFile.addSeparator();
		menuFile.add(itemSaveAs);
		menuFile.addSeparator();
		menuFile.add(itemClose);
		
		menuHelp.add(itemAbout);
		menuHelp.addSeparator();
		menuHelp.add(itemSearch);

		
		jmb.add(menuFile);
		jmb.add(menuHelp);
		
		frame.setJMenuBar(jmb);


		
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////JLAYEREDPANE RELATED STUFF/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		CI = new ArrayList<CircleInfo>();
		TI = new ArrayList<TextInfo>();
		
		jcc = new JColorChooser();

		jlpane = new JLayeredPane();
		jlpane.setBorder(BorderFactory.createLineBorder(Color.black));
		jlpane.setBounds(10, 10, WIDTH-400, HEIGHT-100);
		jlpane.setOpaque(true);
		jlpane.setBackground(Color.white);
		jlpane.addMouseListener(this);
		//jlpane.addMouseMotionListener(this);
		
		frame.add(jlpane);
		
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////JTABLE RELATED STUFF/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Name");
		dtm.addColumn("Object Type");
		dtm.addColumn("Pos");
		
		
		
		Object[] o = new Object[17];
		jtable = new JTable(dtm);
		jtable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jtable.getSelectionModel().addListSelectionListener(this);
		jtable.getColumnModel().getColumn(2).setMaxWidth(40);
		
		
		JScrollPane jsp = new JScrollPane(jtable);
		jsp.setBounds(WIDTH-400+20, 10, 300, 300-5);
		jtable.setFillsViewportHeight(true);
		frame.add(jsp);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////COMPONENTS RELATED STUFF/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		btnAdd = new JButton("Add");
		btnAdd.setBounds(WIDTH-400+20, jsp.getY()+jsp.getHeight()+5,112,40);
		btnAdd.addMouseListener(this);
		frame.add(btnAdd);
		
		btnDel = new JButton("Delete");
		btnDel.setBounds(btnAdd.getX()+btnAdd.getWidth(), jsp.getY()+jsp.getHeight()+5,112,40);
		btnDel.addMouseListener(this);
		frame.add(btnDel);
		
//		btnEdit = new JButton("Edit");
//		btnEdit.setBounds(btnDel.getX()+75,jsp.getY()+jsp.getHeight()+20,75,40);
//		btnEdit.addMouseListener(this);
//		frame.add(btnEdit);
		String[] values = {"1", "2", "3", "4", "5", "6", "7" };
		addCircleAndText = new JSpinner(new SpinnerListModel(values));
		addCircleAndText.setBounds(btnDel.getX()+btnDel.getWidth(), btnDel.getY(), 75, 40);
		addCircleAndText.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(((String)addCircleAndText.getValue()).matches("\\d+")) {
					int value = Integer.parseInt((String)addCircleAndText.getValue());
					if(value > 7) {
						addCircleAndText.setValue(new String("7"));
					}else if(value < 1) {
						addCircleAndText.setValue(new String("1"));
					}
				}else {
					addCircleAndText.setValue(new String("1"));
				}
				
			}
		});
		frame.add(addCircleAndText);
		
		txtAddText = new JTextField();
		txtAddText.setBounds(btnAdd.getX(), btnAdd.getY()+40+5,300,40);
		frame.add(txtAddText);
		
//		delAddText = new JTextField();
//		delAddText.setBounds(txtAddText.getX()+230+20, btnAdd.getY()+40+20, 50, 40);
//		frame.add(delAddText);

		btnAddText = new JButton("ADD TEXT");
		btnAddText.setBounds(txtAddText.getX(),txtAddText.getY()+40,txtAddText.getWidth(),40);
		btnAddText.addMouseListener(this);
		frame.add(btnAddText);
		


		String[] layers = {"0","1","2","3","4","5","6","7","8"};
		selectLayer = new JComboBox(layers);
		selectLayer.setSelectedIndex(0);
		selectLayer.setBounds(WIDTH-200, 100, 100, 40);
		//frame.add(selectLayer);
		
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////CUSTOMIZE TEXT PANEL RELATED STUFF//////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		
		int panelCusHeight = jlpane.getY()+jlpane.getHeight()-(btnAddText.getY()+btnAddText.getHeight()+20);
		panelCustomizeText = new JPanel();
		panelCustomizeText.setBounds(btnAddText.getX(), btnAddText.getY()+40+20,300, panelCusHeight);
		panelCustomizeText.setLayout(null);
		panelCustomizeText.setVisible(false);
		
		lblFont = new JLabel("Selected Font: ");
		lblFont.setBounds(0, 5, 100, 30);
		panelCustomizeText.add(lblFont);
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		List<String> tempList = Arrays.asList(fonts);		
		ArrayList<String> fontsList = new ArrayList<>(tempList);
		fontsList.add(0, "Default");
		
		fonts = fontsList.toArray(new String[fontsList.size()]);
		
		fontList = new JComboBox(fonts);
		fontList.setSelectedIndex(0);
		fontList.setBounds(lblFont.getX()+lblFont.getWidth()+100, 5,100, 30);
		panelCustomizeText.add(fontList);
		
		lblFSize = new JLabel("Selected Size: ");
		lblFSize.setBounds(0,35, 100, 30);
		panelCustomizeText.add(lblFSize);
		
		String[] sizes = {"Default", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"};
		
		sizeList = new JComboBox(sizes);
		sizeList.setSelectedIndex(0);
		sizeList.setBounds(lblFSize.getX()+lblFSize.getWidth()+100, lblFSize.getY(), 100, 30);
		panelCustomizeText.add(sizeList);
		
		lblForeColor = new JLabel("Selected Foreground Color: ");
		lblForeColor.setBounds(0,65,200, 30);
		panelCustomizeText.add(lblForeColor);
		
		btnForeColor = new JButton();
		btnForeColor.setBounds(lblForeColor.getX()+lblForeColor.getWidth(), lblForeColor.getY(), 100, 30);
		btnForeColor.setBackground(Color.BLACK);
		btnForeColor.addActionListener(this);
		panelCustomizeText.add(btnForeColor);
		
		lblBackColor = new JLabel("Selected Background Color: ");
		lblBackColor.setBounds(0,lblForeColor.getY()+lblForeColor.getHeight(),200, 30);
		panelCustomizeText.add(lblBackColor);
		
		btnBackColor = new JButton();
		btnBackColor.setBounds(lblBackColor.getX()+lblBackColor.getWidth(), lblBackColor.getY(), 100, 30);
		btnBackColor.setBackground(Color.BLACK);
		btnBackColor.addActionListener(this);
		panelCustomizeText.add(btnBackColor);
				
		checkOpaque = new JCheckBox("Opaque background");
		checkOpaque.setSelected(false);
		checkOpaque.setHorizontalAlignment(JCheckBox.CENTER);
		checkOpaque.setBounds(panelCustomizeText.getWidth()/2-100, btnBackColor.getY()+btnBackColor.getHeight()+5, 200, 30);
		panelCustomizeText.add(checkOpaque);
		
		frame.add(panelCustomizeText);
		
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////CUSTOMIZE CIRCLE PANEL RELATED STUFF////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
		
		panelCustomizeCir = new JPanel();
		panelCustomizeCir.setBounds(btnAddText.getX(), btnAddText.getY()+40+20,300, panelCusHeight);
		panelCustomizeCir.setLayout(null);
		panelCustomizeCir.setVisible(false);
		
		lblCColor = new JLabel("Selected Color");
		lblCColor.setBounds(0, 5, 100, 30);
		panelCustomizeCir.add(lblCColor);
		
		btnCColor = new JButton();
		btnCColor.setBounds(lblCColor.getX()+lblCColor.getWidth()+100, lblCColor.getY(), 100, 30);
		btnCColor.setBackground(Color.black);
		btnCColor.addActionListener(this);
		panelCustomizeCir.add(btnCColor);
		

		lblStroke = new JLabel("Selected Stroke Size: ");
		lblStroke.setBounds(0, lblCColor.getY()+lblCColor.getHeight(), 200, 30);
		panelCustomizeCir.add(lblStroke);
		
		sliderStroke = new JSlider(JSlider.HORIZONTAL, SIZE_S_MIN, SIZE_S_MAX, SIZE_S_INIT);
		sliderStroke.setSize(300,30);
		sliderStroke.setMajorTickSpacing(2);
		sliderStroke.setMinorTickSpacing(1);
		sliderStroke.setPaintLabels(true);
		sliderStroke.setPaintTicks(true);
		sliderStroke.setSnapToTicks(true);
		sliderStroke.setBounds(0, lblStroke.getY()+lblStroke.getHeight()-10, 300, 50);
		sliderStroke.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int strokeSize = sliderStroke.getValue();
				int[] indexes = jtable.getSelectedRows();
				for(int i = 0; i < indexes.length; i++) {
					int index = Integer.parseInt(jtable.getValueAt(indexes[i], 2).toString());
					CircleInfo c = CI.get(index);
					c.setStrokeSize(strokeSize);
					c.updateImage();
					CI.set(index, c);
					jlpane.repaint();
				}
			}
			
		});
		panelCustomizeCir.add(sliderStroke);
		
		
		lblCSize = new JLabel("Selected Size: ");
		lblCSize.setBounds(0, sliderStroke.getY()+lblStroke.getHeight()+15, 100, 30);
		panelCustomizeCir.add(lblCSize);
		
		sliderSize = new JSlider(JSlider.HORIZONTAL, SIZE_C_MIN, SIZE_C_MAX, SIZE_C_INIT);
		sliderSize.setMajorTickSpacing(100);
		sliderSize.setMinorTickSpacing(50);
		sliderSize.setPaintLabels(true);
		sliderSize.setPaintTicks(true);
		sliderSize.setSnapToTicks(true);
		sliderSize.setBounds(0, lblCSize.getY()+lblCSize.getHeight()-10, 300, 50);
		sliderSize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int size = sliderSize.getValue();
				int[] indexes = jtable.getSelectedRows();
				for(int i = 0; i < indexes.length; i++) {
					int index = Integer.parseInt(jtable.getValueAt(indexes[i], 2).toString());
					//CI.get(index).setSize(size);
					//CI.get(index).updateImage();
					CircleInfo c = CI.get(index);
					c.setSize(size);
					Draw d = (Draw) jlpane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)[index];
					d.size = size;
					d.setOpaque(false);
					d.setBounds(c.getX(), c.getY(), c.getSize(), c.getSize());
					
				}
				jlpane.repaint();
			}
			
		});
		panelCustomizeCir.add(sliderSize);
		
		frame.add(panelCustomizeCir);
		
/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////CUSTOMIZE MESSAGE PANEL RELATED STUFF////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

		panelCustomizeMsg = new JPanel();
		panelCustomizeMsg.setBounds(btnAddText.getX(), btnAddText.getY()+40+20,300, panelCusHeight);
		panelCustomizeMsg.setLayout(null);
		panelCustomizeMsg.setVisible(true);
		
		JLabel lblMsg = new JLabel("Please select any same components to modify!");
		lblMsg.setHorizontalAlignment(JLabel.CENTER);
		lblMsg.setBounds(0, panelCustomizeMsg.getHeight()/2-15, 300, 30);
		panelCustomizeMsg.add(lblMsg);
		
		frame.add(panelCustomizeMsg);
		
		
		
		
		
		//init stuff
	    jlpane.add(new Draw(CINDEX),JLayeredPane.DEFAULT_LAYER,CINDEX);
	    jlpane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)[CINDEX].addMouseListener(this);
	    addRow(CINDEX,CIRCLE_TYPE);
		System.out.println("SIZE: "+ CI.size() + "CC: " + jlpane.getComponentCountInLayer(0));
	
		CINDEX++;
	    jlpane.add(new Draw(CINDEX),JLayeredPane.DEFAULT_LAYER,CINDEX);
	    jlpane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)[CINDEX].addMouseListener(this);
	    addRow(CINDEX,CIRCLE_TYPE);
		System.out.println("SIZE: "+ CI.size() + "CC: " + jlpane.getComponentCountInLayer(0));
		
		frame.setVisible(true);		
	}
	
	
	public static int[] generateCircleBounds() {
		//jlayeredpane starts at x = 10 and ends at x = 10 + (WIDTH-400)
		//jlayeredpane starts at y = 10 and ends at y = 10 + (HEIGHT-100)
		//median of x is at x = (WIDTH-400)/2
		//median of y is at y = (HEIGHT-100)/2
		//where [(WIDTH-400)/2, (HEIGHT-100)/2] is middle point in jlayeredpane
		
		
		int endX = ((WIDTH-400)/2) + (int)((Draw.SIZE/4)*Math.cos( (2*Math.PI/MainFrame.PANE_INDEX)*MainFrame.CINDEX ));
		int endY = ((HEIGHT-100)/2) - (int)((Draw.SIZE/4)*Math.sin( (2*Math.PI/MainFrame.PANE_INDEX)*MainFrame.CINDEX));  // Note "-"
		return new int[] {endX,endY};
	}
	

	public void updateBounds() { 

		for(CINDEX=0; CINDEX < jlpane.getComponentCountInLayer(JLayeredPane.DEFAULT_LAYER); CINDEX++) {
			int[] xy = updateCircle(CINDEX);
			Draw d = (Draw) jlpane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)[CINDEX];
			d.setBounds(xy[0], xy[1],d.size, d.size);
		}
		
	}
	
	
	public int[] updateCircle(int index) {
		CircleInfo prevElem = CI.get(index);
		int[] bounds = generateCircleBounds();
		int newX = bounds[0]-(prevElem.getSize()/2);
		int newY = bounds[1]-(prevElem.getSize()/2);
		prevElem.setX(newX);
		prevElem.setY(newY);
		CI.set(index, prevElem);
		return new int[] {newX,newY};
	}
	
	public void addRow(int index, String type) {
		DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
		if(type.equals(CIRCLE_TYPE)) {
			dtm.addRow(new Object[]{"Circle"+index, type, index});
		}else {
			dtm.addRow(new Object[] {"Text"+index,type,index});
		}
	}
	
	
	public void removeRow(int index) {
		DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
		dtm.removeRow(index);
	}
	
	public boolean isSameType(String t) {
		String type = t;
		for(int index : jtable.getSelectedRows()) {
			if(type != jtable.getValueAt(index, 1)) {
				return false;
			}
		}
		return jtable.getSelectedRowCount()>0;
	}
	
	
	public void setProperties(String t, String v) {
		if(v.equals(VALUE_DEFAULT)) {
			if(t.equals(CIRCLE_TYPE)) {
				btnCColor.setBackground(Color.black);
				sliderStroke.setValue(2);
				sliderSize.setValue(600);
			}else if(t.equals(TEXT_TYPE)) {
				btnForeColor.setBackground(Color.black);
				btnBackColor.setBackground(Color.black);
				fontList.setSelectedIndex(0);
				sizeList.setSelectedIndex(0);
				checkOpaque.setSelected(false);
			}
		}else {
			int index = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 2).toString());

			if(t.equals(CIRCLE_TYPE)) {
				btnCColor.setBackground(CI.get(index).getColor());
				sliderStroke.setValue(CI.get(index).getStrokeSize());
				sliderSize.setValue(CI.get(index).getSize());
			}else if(t.equals(TEXT_TYPE)) {
				btnForeColor.setBackground(TI.get(index).getForeColor());
				btnBackColor.setBackground(TI.get(index).getBackColor());
				fontList.setSelectedItem(TI.get(index).getFont());
				sizeList.setSelectedItem(TI.get(index).getSize());
				checkOpaque.setSelected(TI.get(index).isOpaque());
			}
		}
	}
	
	public void updateTable() {
		int ccounter = 0, tcounter=0;
		for(int i = 0; i < jtable.getRowCount(); i++) {
			if(jtable.getValueAt(i, 1).equals(CIRCLE_TYPE)) {
				jtable.setValueAt(""+ccounter, i, 2);
				ccounter++;
			}else if(jtable.getValueAt(i, 1).equals(TEXT_TYPE)) {
				jtable.setValueAt(""+tcounter, i, 2);
				tcounter++;
			}
		}
	}
	
	public void switchPanel(int state) {
		//state: 0 - panelCustomizeText is visible, 1 - panelCustomizeCir is visible, 2 - panelCustomizeMsg is visible
		
		if(state == 0) {
			panelCustomizeCir.setVisible(false);
			panelCustomizeMsg.setVisible(false);
			panelCustomizeText.setVisible(true);
		}else if(state == 1) {
			panelCustomizeMsg.setVisible(false);
			panelCustomizeText.setVisible(false);
			panelCustomizeCir.setVisible(true);
		}else if(state == 2) {
			panelCustomizeCir.setVisible(false);
			panelCustomizeText.setVisible(false);
			panelCustomizeMsg.setVisible(true);
		}else {
			//Undefined state
		}
		
	}
	
	public int getAddValue() {
		String value = (String)addCircleAndText.getValue();
		return Integer.parseInt(value);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
		if(arg0.getSource() == btnAdd) {
			for(int i = 0; i < getAddValue(); i++) {
				if(PANE_INDEX < MAX_CIRCLES) {
					PANE_INDEX++;
					if(PANE_INDEX == 3 || PANE_INDEX == 4) {
						Draw.SIZE -= 145;
					}
					updateBounds();
					jlpane.add(new Draw(CINDEX),JLayeredPane.DEFAULT_LAYER,CINDEX);
				    jlpane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)[CINDEX].addMouseListener(this);
					DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
					addRow(CINDEX,CIRCLE_TYPE);
				}
			}
			jlpane.repaint();
			
		}else if(arg0.getSource() == btnDel) {
			if(!jtable.getSelectionModel().isSelectionEmpty() && PANE_INDEX > MIN_CIRCLES) {
				int[] selIndexes = jtable.getSelectedRows();
				
				for(int i = selIndexes.length-1; i >= 0; i--) {
					if(jtable.getValueAt(i, 1).equals(CIRCLE_TYPE)) {
						if(PANE_INDEX > MIN_CIRCLES) {
							PANE_INDEX--;
							if(PANE_INDEX == 2 || PANE_INDEX == 3) {
								Draw.SIZE += 145;
							}
							int index = Integer.parseInt((String)jtable.getValueAt(selIndexes[i], 2).toString());
							Component c = jlpane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)[index];
							jlpane.remove(c);
							updateBounds();
							removeRow(i);
						}
					}else if(jtable.getValueAt(i, 1).equals(TEXT_TYPE)) {
						TINDEX--;
						int index = Integer.parseInt(jtable.getValueAt(selIndexes[i], 2).toString());
						Component c = jlpane.getComponentsInLayer(JLayeredPane.MODAL_LAYER)[index];
						jlpane.remove(c);
						removeRow(i);
					}
				}
				updateTable();
				jlpane.repaint();
			}
		}else if(arg0.getSource() == btnAddText) {
			if(txtAddText.getText().length() > 0) {
				for(int i = 0; i < getAddValue(); i++) {
					
					addRow(TINDEX,TEXT_TYPE);
					Object[] data = {fontList.getSelectedItem(),sizeList.getSelectedItem(),btnForeColor.getBackground(),
							btnBackColor.getBackground(),checkOpaque.isSelected(), txtAddText.getText()};
					
					jlpane.add(new Text(TINDEX,data),JLayeredPane.MODAL_LAYER);
				    jlpane.getComponentsInLayer(JLayeredPane.MODAL_LAYER)[TINDEX].addMouseListener(this);

					TINDEX++;
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
		for(int k = 0; k < jlpane.getComponentCountInLayer(JLayeredPane.DEFAULT_LAYER); k++) {
			if(jlpane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)[k] == arg0.getSource()) {
				for(int j = 0; j < jtable.getRowCount(); j++) {
					int index = Integer.parseInt(jtable.getValueAt(j, 2).toString());
					String type = jtable.getValueAt(j, 1).toString();
					
					if(k == index && type == CIRCLE_TYPE) {
						jtable.setRowSelectionInterval(j, j);
						break;
					}
				}
			}
		}
		
		for(int k = 0; k < jlpane.getComponentCountInLayer(JLayeredPane.MODAL_LAYER); k++) {
			if(jlpane.getComponentsInLayer(JLayeredPane.MODAL_LAYER)[k] == arg0.getSource()) {
				for(int j = 0; j < jtable.getRowCount(); j++) {
					int index = Integer.parseInt(jtable.getValueAt(j, 2).toString());
					String type = jtable.getValueAt(j, 1).toString();
					
					if(k == index && type == TEXT_TYPE) {
						jtable.setRowSelectionInterval(j, j);
						break;
					}
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnForeColor) {
			btnForeClicked = true;
			btnCColorClicked = false;
			jcc.setColor(btnForeColor.getBackground());
			JDialog dialog = JColorChooser.createDialog(frame, "Select a Color", true, jcc,this,null);
			dialog.setVisible(true);
		}else if(e.getSource() == btnBackColor){
			btnForeClicked = false;
			btnCColorClicked = false;
			jcc.setColor(btnBackColor.getBackground());
			JDialog dialog = JColorChooser.createDialog(frame, "Select a Color", true, jcc,this,null);
			dialog.setVisible(true);
		}else if(e.getSource() == btnCColor) {
			btnCColorClicked = true;
			jcc.setColor(btnCColor.getBackground());
			JDialog dialog = JColorChooser.createDialog(frame, "Select a Color", true, jcc,this,null);
			dialog.setVisible(true);
		}else {
			if(btnCColorClicked) {
				btnCColor.setBackground(jcc.getColor());
			}else if(btnForeClicked) {
				btnForeColor.setBackground(jcc.getColor());
			}else {
				btnBackColor.setBackground(jcc.getColor());
			}
		}
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int codeOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK).getKeyCode();
		int codeSave = KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK).getKeyCode();
		int codeSaveAs = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK).getKeyCode();
		int codeClose = KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK).getKeyCode();
		int codeAbout = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK).getKeyCode();
		int codeSearch = KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK).getKeyCode();
		
		System.out.println("keycode: "+ e.getKeyCode() + " keystrokecode: " + codeClose);
		
		if(e.getKeyCode() == codeOpen) {
			
		}else if(e.getKeyCode() == codeSave) {
			
		}else if(e.getKeyCode() == codeSaveAs) {
			
		}else if(e.getKeyCode() == codeClose) {
			System.exit(0);
		}else if(e.getKeyCode() == codeAbout) {
			
		}else if(e.getKeyCode() == codeSearch) {
			
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {			
			if(e.getSource() == jtable.getSelectionModel()) {
			
				if(isSameType(CIRCLE_TYPE)) {
					if(jtable.getSelectedRowCount() > 1) {
						btnCColor.setBackground(Color.BLACK);
					}else {
						//btnCColor.setBackground(bg);
					}
					switchPanel(1);
				}else if(isSameType(TEXT_TYPE)){
					if(jtable.getSelectedRowCount() > 1) {
						btnForeColor.setBackground(Color.BLACK);
						btnBackColor.setBackground(Color.BLACK);
					}else {
						//btnCColor.setBackground(bg);
					}
					switchPanel(0);
				}else if(isSameType(PANEL_TYPE)) {
					
				}else {
					//UNDEFINED TYPE...ERROR
					switchPanel(2);
				}
			}
		}
	}
}
