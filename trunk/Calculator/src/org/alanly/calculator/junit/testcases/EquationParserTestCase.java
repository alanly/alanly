package org.alanly.calculator.junit.testcases;

import java.util.ArrayDeque;
import java.util.Queue;

import org.alanly.calculator.logic.EquationParser;

import junit.framework.TestCase;

public class EquationParserTestCase extends TestCase {
	
	private EquationParser ep;

	protected void setUp() throws Exception {
		super.setUp();
		
		this.ep = new EquationParser();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		this.ep = null;
	}
	
	public void testSolve() {
		// Initialise an infix equation deque
		Queue<String> equation = new ArrayDeque<String>();
		
		// Create an equation within the queue/deque
		equation.offer("3");
		equation.offer("*");
		equation.offer("4");
		
		// Test
		assertEquals("12", ep.solve(equation).toPlainString());
	}
	
	public void testIsNumber() {
		String string = "abc";
		
		assertFalse("abc = ", ep.isNumber(string));
		
		string = "123";
		
		assertTrue("123 = ", ep.isNumber(string));
	}

}
