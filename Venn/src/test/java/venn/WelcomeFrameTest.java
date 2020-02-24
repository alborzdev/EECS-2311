package venn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WelcomeFrameTest {
	private WelcomeFrame wf;
	@BeforeEach
	void setUp() throws Exception {
		wf = new WelcomeFrame();
	}

	@Test
	void testContinue() throws InterruptedException {
		Thread.sleep(1000);
		wf.btnContinue.doClick();
		Thread.sleep(1000);
	}

}
