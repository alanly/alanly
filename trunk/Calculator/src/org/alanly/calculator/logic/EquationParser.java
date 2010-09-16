/**
 * Contains the necessary logic classes to perform basic Calculator functions.
 */
package org.alanly.calculator.logic;

import java.math.BigDecimal;
import java.util.Queue;
import java.util.Iterator;

/**
 * An <code>EquationParser</code> contains the necessary logic to solve basic mathematical arithmetic equations based on an equation contained within a <code>Queue</code.
 * 
 * @author Alan Ly
 * @version 1.5
 */
public class EquationParser { 
	
	private Queue<String> inputEquation;
	private BigDecimal result;
	private boolean solved;
	
	/**
	 * Creates an <code>EquationParser</code> with null values.
	 */
	public EquationParser() {
		super();
		this.initialise(null);
	}
	
	/**
	 * Creates an <code>EquationParser</code> with a specified equation in the form of a <code>Queue</code>.
	 * 
	 * @param inputEquation the queue containing the equation
	 */
	public EquationParser(Queue<String> inputEquation) {
		super();
		this.initialise(inputEquation);
	}
	
	/**
	 * Initialises the instance.
	 * 
	 * @param inputEquation the input queue
	 */
	private void initialise(Queue<String> inputEquation) {
		this.setInputQueue(inputEquation);
		this.result = new BigDecimal(0);
		this.solved = false;
		
		if(this.inputEquation != null)
			this.solve(this.inputEquation);
	}
	
	/**
	 * Sets the input queue of type String containing the appropriate equation.
	 * 
	 * @param inputEquation the input queue
	 */
	public void setInputQueue(Queue<String> inputEquation) {
		this.inputEquation = inputEquation;
	}
	
	/**
	 * Gets the equation in the form of a <code>Queue</code>.
	 * 
	 * @return the equation in a <code>Queue</code>
	 */
	public Queue<String> getEquationQueue() {
		return inputEquation;
	}
	
	/**
	 * Gets the equation in the form of a <code>String</code>.
	 * 
	 * @return the equation in a <code>String</code>
	 */
	public String getEquationString() {
		Iterator<String> equationIterator = inputEquation.iterator();
		String equationString = "";
		
		while(equationIterator.hasNext())
			equationString += equationIterator.next() + " ";
		
		return equationString;
	}
	
	/**
	 * Gets the result of the equation as a <code>Double</code>.
	 * 
	 * @return the result of the equation
	 */
	public BigDecimal getEquationResult() {
		return result;
	}
	
	/**
	 * Returns a boolean value representing whether or not this equation has been solved or not.
	 * 
	 * @return a boolean representing the solved state of this equation
	 */
	public boolean isSolved() {
		return solved;
	}
	
	/**
	 * Resets the solved boolean in the object.
	 */
	public void resetSolved() {
		this.solved = false;
	}
	
	/**
	 * Solves the specified equation which should be in Infix notation and in <code>Queue<code> form and sets the solved state to true.
	 * 
	 * @param inputEquation the Infix equation to solve
	 */
	public BigDecimal solve(Queue<String> inputEquation) {				
		// Generate Postfix Equation and Solve It
		this.result = EquationUtilities.solvePostfixEquation(EquationUtilities.generatePostfix(inputEquation));
		this.solved = true;
		
		return this.result;
	}
	
	/**
	 * Solves this equation.
	 */
	public BigDecimal solve() {
		return this.solve(this.inputEquation);
	}
	
}