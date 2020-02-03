package venn;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;


class CounterTest {
	Counter c;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new Counter();
	}

	
	@Test
	public void testIncrement01() {
		assertEquals(1,c.increment());
		assertEquals(2,c.increment());
		assertEquals(3,c.increment());
	}
	
	@Test
	public void testIncrement02() {
		assertEquals(1,c.increment(1));
		assertEquals(0,c.increment(-1));
		assertEquals(10,c.increment(10));
	}
	
	@Test
	public void testDecrement01() {
		assertEquals(-1,c.decrement());
		assertEquals(-2,c.decrement());
		assertEquals(-3,c.decrement());
	}
	
	@Test
	public void testDecrement02() {
		assertEquals(-5,c.decrement(5));
		assertEquals(5,c.decrement(-10));
		assertEquals(0,c.decrement(5));
	}
	
	@Test
	public void testReset() {
		assertEquals(0,c.reset());
		c.decrement(-100);
		assertEquals(0,c.reset());
	}
	
	@Test
	public void textToString() {
		c.increment(-10);
		assertEquals("Counter: -10", c.toString());
		c.reset();
		assertEquals("Counter: 0", c.toString());
	}

}
