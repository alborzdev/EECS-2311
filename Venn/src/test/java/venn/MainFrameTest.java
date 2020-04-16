package venn;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.KeyStroke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainFrameTest {
	MainFrame mf;
	@BeforeEach
	void setUp() throws Exception {
		mf = new MainFrame();
	}

	@AfterEach
	void cleanUpEach() throws Exception {
		mf.clearAllInfo();
	}
	
	public void addClick() throws InterruptedException, AWTException {
		Robot r = new Robot();
		Thread.sleep(100);
		r.mouseMove(1020, 370);    
	    r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(100);
	}
	
	public void deleteClick() throws InterruptedException, AWTException  {
		Robot r = new Robot();

		Thread.sleep(100);
		r.mouseMove(1200, 370);    
	    r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(100);
	}
	
	public void addTextClick() throws InterruptedException, AWTException  {
		Robot r = new Robot();

		Thread.sleep(100);
		r.mouseMove(1020, 470);    
	    r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(100);
	}
	
	
	@Test
	void testDefaultState() throws InterruptedException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		Thread.sleep(100);
	}
	
	@Test
	void testAddingCircles() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		addClick();
		assertEquals(3,mf.CI.size());
		assertEquals(0,mf.TI.size());
		
	}
	
	@Test
	void testAddingMultipleCircles() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(1,mf.getAddValue());
		
		mf.addCircleAndText.setValue("3");
		addClick();
		Thread.sleep(500);
		assertEquals(5,mf.CI.size());
		assertEquals(0,mf.TI.size());

		
	}
	
	@Test
	void testAddingEmptyText() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		
		addTextClick();
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());

		
	}
	

	@Test
	void testAddingText() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		
		mf.txtAddText.setText("Hello World!");
		addTextClick();
		assertEquals(2,mf.CI.size());
		assertEquals(1,mf.TI.size());

		
	}
	
	@Test
	void testAddingMultipleText() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		
		mf.addCircleAndText.setValue("3");
		mf.txtAddText.setText("Hello World!");
		addTextClick();
		assertEquals(2,mf.CI.size());
		assertEquals(3,mf.TI.size());

		
	}
	
	@Test
	void testDefaultTable() throws InterruptedException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		
		assertEquals(2, mf.jtable.getRowCount());		
		
	}
	
	
	
	@Test
	void testTableWithCircles() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		mf.jtable.setRowSelectionInterval(0, 1);
		assertTrue(mf.isSameType(mf.CIRCLE_TYPE));
		
		mf.addCircleAndText.setValue("3");
		addClick();
		Thread.sleep(500);
		mf.jtable.setRowSelectionInterval(0, 4);
		assertEquals(5,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(5,mf.jtable.getRowCount());
		assertTrue(mf.isSameType(mf.CIRCLE_TYPE));
		
	}
	@Test
	void testTableWithText() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		assertFalse(mf.isSameType(mf.TEXT_TYPE));
		
		mf.addCircleAndText.setValue("3");
		mf.txtAddText.setText("Hello World!");
		addTextClick();
		assertEquals(2,mf.CI.size());
		assertEquals(3,mf.TI.size());
		mf.jtable.setRowSelectionInterval(2, 4);
		assertEquals(5,mf.jtable.getRowCount());
		assertTrue(mf.isSameType(mf.TEXT_TYPE));
		
	}
	
	
	@Test
	void testDeletingMinCircles() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		mf.jtable.setRowSelectionInterval(0, 0);
		deleteClick();
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		
	}
	
	@Test
	void testDeletingCircles() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		addClick();
		assertEquals(3,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(3,mf.jtable.getRowCount());
		
		mf.jtable.setRowSelectionInterval(0, 0);
		deleteClick();
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		
	}

	@Test
	void testDeletingMultipleCircles01() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		mf.addCircleAndText.setValue("3");
		addClick();
		Thread.sleep(500);
		assertEquals(5,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(5,mf.jtable.getRowCount());
		
		mf.jtable.setRowSelectionInterval(0, 2);
		Thread.sleep(100);
		deleteClick();
		Thread.sleep(500);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());

		
	}
	
	@Test
	void testDeletingMultipleCircles02() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		mf.addCircleAndText.setValue("3");
		addClick();
		Thread.sleep(500);
		assertEquals(5,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(5,mf.jtable.getRowCount());
		
		mf.jtable.setRowSelectionInterval(0, 4);
		deleteClick();
		Thread.sleep(500);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());

		
	}
	
	@Test
	void testDeletingText() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		mf.txtAddText.setText("Hello World!");
		addTextClick();
		assertEquals(2,mf.CI.size());
		assertEquals(1,mf.TI.size());
		assertEquals(3,mf.jtable.getRowCount());
		
		mf.jtable.setRowSelectionInterval(2, 2);
		deleteClick();
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());

		
	}

	
	@Test
	void testDeletingMultipleText01() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		mf.txtAddText.setText("Hello World!");
		mf.addCircleAndText.setValue("3");
		addTextClick();
		assertEquals(2,mf.CI.size());
		assertEquals(3,mf.TI.size());
		assertEquals(5,mf.jtable.getRowCount());
		
		mf.jtable.setRowSelectionInterval(2, 4);
		deleteClick();
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());

		
	}

	@Test
	void testDeletingMultipleText02() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		mf.txtAddText.setText("Hello World!");
		mf.addCircleAndText.setValue("7");
		addTextClick();
		assertEquals(2,mf.CI.size());
		assertEquals(7,mf.TI.size());
		assertEquals(9,mf.jtable.getRowCount());
		
		mf.jtable.setRowSelectionInterval(2, 8);
		deleteClick();
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());

		
	}
	
	@Test
	void testUndo() throws InterruptedException, AWTException {
		MainFrame mf = new MainFrame();
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		ArrayList<CircleInfo> expCI = mf.CI;
		ArrayList<TextInfo> expTI = mf.TI;
		mf.txtAddText.setText("Hello World!");
		mf.addCircleAndText.setValue("1");
		addTextClick();
		Thread.sleep(500);
		mf.addCircleAndText.setValue("3");
		addClick();
		Thread.sleep(500);
		mf.jtable.setRowSelectionInterval(0, 1);
		deleteClick();
		Thread.sleep(500);
		mf.addCircleAndText.setValue("1");
		addClick();
		Thread.sleep(500);
		mf.txtAddText.setText("Hello World!");
		addTextClick();
		Thread.sleep(500);
		
		//8 actions performed, undo these
		mf.frame.requestFocus();
		Robot r = new Robot();
		for(int i = 0; i < 8; i++) {
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_Z);
		}
		assertEquals(expCI,mf.CI);
		assertEquals(expTI,mf.TI);
	}
	
	@Test
	void testRedo() throws InterruptedException, AWTException {
		Thread.sleep(100);
		assertEquals(2,mf.CI.size());
		assertEquals(0,mf.TI.size());
		assertEquals(2,mf.jtable.getRowCount());
		
		
		mf.txtAddText.setText("Hello World!");
		mf.addCircleAndText.setValue("1");
		addTextClick();
		mf.addCircleAndText.setValue("3");
		addClick();
		mf.jtable.setRowSelectionInterval(0, 1);
		deleteClick();
		mf.addCircleAndText.setValue("1");
		addClick();
		mf.txtAddText.setText("Hello World!");
		addTextClick();
		
		ArrayList<CircleInfo> expCI = mf.CI;
		ArrayList<TextInfo> expTI = mf.TI;
		
		//8 actions performed, undo these
		mf.frame.requestFocus();
		Robot r = new Robot();
		for(int i = 0; i < 8; i++) {
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_Z);
		}
		
		///redo these 8 actions
		for(int i = 0; i < 8; i++) {
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_Y);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_Y);
		}
		assertEquals(expCI,mf.CI);
		assertEquals(expTI,mf.TI);
		
	}

}
