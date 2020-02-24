package venn;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

	private Main m;
	@BeforeEach
	void setUp() throws Exception {
		m = new Main();
	}

	@Test
	void testStatsButton() throws InterruptedException {
		Thread.sleep(1000);
		m.btnStats.doClick();
		Thread.sleep(1000);
	}
	
	@Test
	void testInputtingText() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		Robot r = new Robot();
		r.mouseMove(200, 200);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(100);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		r.delay(500);
		r.keyPress(KeyEvent.VK_3);
		r.keyPress(KeyEvent.VK_5);
		r.delay(100);
		r.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
	}
	
	@Test
	void testLegend() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		Robot r = new Robot();
		r.mouseMove((1366/2)-100,768/2);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(100);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		r.delay(500);
		r.keyPress(KeyEvent.VK_3);
		r.keyPress(KeyEvent.VK_5);
		r.delay(100);
		r.keyPress(KeyEvent.VK_ENTER);
		r.delay(500);
		
		assertEquals(m.txtNameYellow.getText(), "Add Title");
		m.txtNameYellow.setText("Math");
		assertEquals(m.txtNameYellow.getText(), "Math");
		Thread.sleep(1000);
	}
	
	
	

}
