/**
 * Contains the necessary logic classes to perform basic Calculator functions.
 */
package org.alanly.calculator.logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Iterator;
import java.util.Stack;

/**
 * An <code>EquationParser</code> contains the necessary logic to solve basic mathematical arithmetic equations based on an equation contained within a <code>Queue</code.
 * 
 * @author Alan Ly
 * @version 2.4
 */
public class EquationParser { 
	
	/**
	 * A HashMap that contains the precedence values for the operators
	 */
	private HashMap<Character, Integer> precedenceMap;
	
	/**
	 * The default <code>MathContext</code> value to use for BigDecimal operations.
	 * 
	 * Precision of 16 digits with a <code>HALF_UP</code> rounding mode.
	 * 
	 * @see java.math.MathContext
	 */
	private static final MathContext MATH_CONTEXT = new MathContext(16, RoundingMode.HALF_UP);
	
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
		this.initialisePrecedenceMap();
		this.setInputQueue(inputEquation);
		this.result = new BigDecimal(0);
		this.solved = false;
		
		// If inputEquation queue is not null, then solve input equation
		if(this.inputEquation != null)
			this.solve(this.inputEquation);
	}
	
	/**
	 * Initialises the internal <code>HashMap</code> with the appropriate orders of precedence for the arithmetic operators.
	 */
	private void initialisePrecedenceMap() {
		// Initialise HashMap
		precedenceMap = new HashMap<Character, Integer>();
		
		// Add orders of precedence, from lowest to highest
		precedenceMap.put('+', 1); // Precedence for addition
		precedenceMap.put('-', 1); // Precedence for subtraction
		precedenceMap.put('*', 2); // Precedence for multiplication
		precedenceMap.put('/', 2); // Precedence for division
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
		// Create iterator and String
		Iterator<String> equationIterator = inputEquation.iterator();
		String equationString = "";
		
		// While iterator has next another value, append to the end of the string
		while(equationIterator.hasNext())
			equationString += equationIterator.next() + " ";
		
		return equationString;
	}
	
	/**
	 * Gets the result of the equation as a <code>BigDecimal</code>.
	 * 
	 * @return the result of the equation in <code>BigDecimal</code>
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
	 * @return the result of the equation in <code>BigDecimal</code>
	 */
	public BigDecimal solve(Queue<String> inputEquation) {				
		// Generate Postfix Equation and Solve It
		this.result = this.solvePostfixEquation(this.generatePostfix(inputEquation));
		
		// Set state
		this.solved = true;
		
		return this.result;
	}
	
	/**
	 * Solves the equation contained in this object.
	 * 
	 * @return the result of the equation in <code>BigDecimal</code>
	 */
	public BigDecimal solve() {
		return this.solve(this.inputEquation);
	}
	
	/**
	 * Generates a Postfix (Polish Notation) equation from an Infix equation. Infix equation must be in the form of an <code>Queue</code> and output will be in the form 
	 * of a <code>Queue</code>. Assumes that input <code>Queue</code> contains valid values.
	 *  
	 * @param inputEquation the Infix equation to convert into Postfix
	 * @return the Postfix equivalence of <code>inputEquation</code>
	 * @since 2.0
	 */
	public Queue<String> generatePostfix(Queue<String> inputEquation) {

		// Create Collections used to separate and/or hold operands and operators
		Stack<String> operatorStack = new Stack<String>(); // Temporarily holds the operators in the equation
		Queue<String> outputQueue = new ArrayDeque<String>(); // Holds the Postfix output of the equation

		// Instantiate Iterator for inputEquation
		Iterator<String> equationIterator = inputEquation.iterator();

		// Iterate through the inputEquation Queue and process each item in Collection
		while(equationIterator.hasNext()) {
			
			// Fetch next value from Iterator
			String value = equationIterator.next();

			// Determine if 'value' is a number or not
			if(this.isNumber(value)) {
				// If 'value' is a number then add it into the outputQueue
				outputQueue.add(value);
			} else {
				
				// If 'value' is a operator then determine whether there is anything in the operatorStack
				if(!operatorStack.empty()) {
					
					// If the operatorStack is NOT empty then peek at the last operator in the stack
					String lastOperator = operatorStack.peek();

					// Determine if the current operator is greater than the last operator in the stack
					if(precedenceMap.get(value.charAt(0)) > precedenceMap.get(lastOperator.charAt(0))) {
						
						// If it is then simply add it to the end of the operator stack
						operatorStack.add(value);
					} else {
						
						// If it isn't then dump the stack into the outputQueue up to an operator that's smaller than the current value
						while(!operatorStack.empty() && precedenceMap.get(operatorStack.peek().charAt(0)) >= precedenceMap.get(value.charAt(0))) 
							outputQueue.add(operatorStack.pop());

						// Add the current value into the stack
						operatorStack.add(value);
					}
				} else {
					// If the operatorStack is empty then simply add the operator into the stack
					operatorStack.add(value);
				}
			}
		}

		// Pop all values out of stack into outputQueue
		while(!operatorStack.empty())
			outputQueue.add(operatorStack.pop());

		return outputQueue;
	}
	
	/**
	 * Solves a given arithmetic equation that has been formatted in Postfix (Polish Notation). The Postfix equation must be presented as a F.I.F.O. <code>Queue</code>.
	 * Operations are performed through the <code>BigDecimal</code> object with a fixed precision level.
	 * 
	 * @param postfixEquation the Postfix equation in <code>Queue</code> format
	 * @return the result of the calculation as a <code>BigDecimal</code>
	 * @see java.math.BigDecimal
	 * @since 2.0
	 */
	public BigDecimal solvePostfixEquation(Queue<String> postfixEquation) {
		
		// Declare temporary Queue to hold values during an operation
		Stack<BigDecimal> operationStack = new Stack<BigDecimal>();
		
		// Declare Iterator to iterate through postfixEquation
		Iterator<String> equationIterator = postfixEquation.iterator();
		
		// Loop through entire postfixEquation and process values
		while(equationIterator.hasNext()) {
			
			// Fetch next value from the postfixEquation
			String value = equationIterator.next();
			
			// If value is a number, then add it into the operation stack
			if(this.isNumber(value))
				operationStack.add(new BigDecimal(value));
			else {
				// Declare temporary double variables that will store the appropriate operands
				BigDecimal operandTwo = operationStack.pop();
				BigDecimal operandOne = operationStack.pop();
				
				// Perform the appropriate processing
				switch(value.charAt(0)) {
					case '+':								
						// Perform operation and then add results back into operationStack
						operationStack.add(operandOne.add(operandTwo, MATH_CONTEXT));
						break;
					case '-':							
						operationStack.add(operandOne.subtract(operandTwo, MATH_CONTEXT));
						break;
					case '/':
						operationStack.add(operandOne.divide(operandTwo, MATH_CONTEXT));
						break;
					case '*':
						operationStack.add(operandOne.multiply(operandTwo, MATH_CONTEXT));
						break;
					default:
						throw new InputMismatchException("Invalid Operator: " + value);
				}
			}
		}
		
		return operationStack.pop();
	}

	/**
	 * Determines if a particular <code>String</code> value is a number or not. Returns <strong>true</strong> if <code>value</code> is a number and <strong>false</strong> if otherwise.
	 * 
	 * @param value the value to test
	 * @return the validity of the input as a number
	 * @since 2.0
	 */
	public boolean isNumber(String value) {
		try {
			// Try parsing the value
			Double.parseDouble(value);

			// Return true if the value parses successfully
			return true;
		} catch(Exception e) {
			// Return false if the value fails to parse
			return false;
		}
	}
	
}