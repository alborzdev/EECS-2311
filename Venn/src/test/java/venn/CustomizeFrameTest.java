package venn;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomizeFrameTest {
	private CustomizeFrame cf;
	@BeforeEach
	void setUp() throws Exception {
		cf = new CustomizeFrame();
	}

	@Test
	void testInputtingNameDefault() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(cf.txtName.getText(), "Venn Diagram");
		cf.txtName.setText("");
		assertEquals(cf.txtName.getText(), "");
		Thread.sleep(1000);
	}
	
	@Test
	void testInputtingNameText() throws InterruptedException {
		Thread.sleep(1000);
		cf.txtName.setText("My Venn Diagram");
		assertEquals(cf.txtName.getText(), "My Venn Diagram");
		cf.txtName.setText("Stat Venn Diagram");
		assertEquals(cf.txtName.getText(), "Stat Venn Diagram");
		Thread.sleep(1000);
	}
	
	@Test
	void testSliderDefault() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(cf.sldrCircles.getValue(), 2);
		cf.sldrCircles.setValue(0);
		assertEquals(cf.sldrCircles.getValue(), 2);
		Thread.sleep(1000);
	}
	
	@Test
	void testSliderValue() throws InterruptedException {
		Thread.sleep(1000);
		cf.sldrCircles.setValue(-1);
		assertEquals(cf.sldrCircles.getValue(), 2);
		cf.sldrCircles.setValue(100);
		assertEquals(cf.sldrCircles.getValue(), 7);
		cf.sldrCircles.setValue(4);
		assertEquals(cf.sldrCircles.getValue(), 4);
		cf.sldrCircles.setValue(7);
		assertEquals(cf.sldrCircles.getValue(), 7);
		
		Thread.sleep(1000);
	}
	
	@Test
	void testFontSelectionDefault() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(cf.fontList.getSelectedItem().toString(), "Default");
		Thread.sleep(1000);
	}
	
	@Test
	void testFontSelectionValue() throws InterruptedException {
		Thread.sleep(1000);
		String font="Times New Roman";
		cf.fontList.setSelectedItem(font);
		assertEquals(cf.fontList.getSelectedItem().toString(), font);
		font="Consolas";
		cf.fontList.setSelectedItem(font);
		assertEquals(cf.fontList.getSelectedItem().toString(), font);
		font="Default";
		cf.fontList.setSelectedItem(font);
		assertEquals(cf.fontList.getSelectedItem().toString(), font);
		Thread.sleep(1000);
	}
	

	@Test
	void testSizeSelectionDefault() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(cf.sizeList.getSelectedItem().toString(), "Default");
		Thread.sleep(1000);
	}
	

	@Test
	void testSizeSelectionValue() throws InterruptedException {
		Thread.sleep(1000);
		String size="10";
		cf.sizeList.setSelectedItem(size);
		assertEquals(cf.sizeList.getSelectedItem().toString(), size);
		size="0";
		cf.sizeList.setSelectedItem(size);
		assertEquals(cf.sizeList.getSelectedItem().toString(), "10");
		size="Default";
		cf.sizeList.setSelectedItem(size);
		assertEquals(cf.sizeList.getSelectedItem().toString(), size);
		Thread.sleep(1000);
	}
	

	@Test
	void testColorSelectionValue() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(cf.btnColor.getBackground(), Color.BLACK);
		cf.btnColor.setBackground(Color.red);
		assertEquals(cf.btnColor.getBackground(), Color.red);
		cf.btnColor.setBackground(Color.magenta);
		assertEquals(cf.btnColor.getBackground(), Color.magenta);
		cf.btnColor.setBackground(new Color(5, 5, 200));
		assertEquals(cf.btnColor.getBackground(), new Color(5, 5, 200));
		Thread.sleep(1000);
	}
	
	@Test
	void testAdvanceSettings() throws InterruptedException {
		Thread.sleep(1000);
		cf.boxUseAdvance.doClick();
		Thread.sleep(1000);
	}
	
	@Test
	void testCreateButton() throws InterruptedException {
		Thread.sleep(1000);
		cf.btnCreate.doClick();
		Thread.sleep(1000);
	}
	
	@Test
	void testBackButton() throws InterruptedException {
		Thread.sleep(1000);
		cf.btnBack.doClick();
		Thread.sleep(1000);
	}
	
	
	
	

}
