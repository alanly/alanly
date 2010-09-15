/**
 * Contains the necessary logic classes to perform basic Calculator functions.
 */
package org.alanly.calculator.logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Contains static utility methods to handle arithmetic equations and work with numbers.
 * @author Alan Ly
 * @version 1.4
 */
public class EquationUtilities {
	
	private static HashMap<Character, Integer> precedencemap;
	
	/**
	 * Initialises the internal <code>HashMap</code> with the appropriate orders of precedence for the arithmetic operators.
	 */
	private static void initialisePrecedenceMap() {
		// Initialise HashMap
		precedencemap = new HashMap<Character, Integer>();
		
		// Add orders of precedence, from lowest to highest
		precedencemap.put('+', 1); // Precedence for addition
		precedencemap.put('-', 1); // Precedence for subtraction
		precedencemap.put('*', 2); // Precedence for multiplication
		precedencemap.put('/', 2); // Precedence for division
	}

	/**
	 * Generates a Postfix (Polish Notation) equation from an Infix equation. Infix equation must be in the form of an <code>Queue</code> and output will be in the form 
	 * of a <code>Queue</code>. Assumes that input <code>Queue</code> contains valid values.
	 *  
	 * @param inputEquation the Infix equation to convert into Postfix
	 * @return the Postfix equivalence of <code>inputEquation</code>
	 */
	public static Queue<String> generatePostfix(Queue<String> inputEquation) {
		// Initialise Precedence Map
		initialisePrecedenceMap();
		
		// Create Collections used to separate and/or hold operands and operators
		Stack<String> operatorStack = new Stack<String>(); // Temporarily holds the operators in the equation
		Queue<String> outputQueue = new ArrayDeque<String>(); // Holds the Postfix output of the equation
		
		// Instantiate Iterator for inputEquation
		Iterator<String> equationIterator = inputEquation.iterator();
		
		// Iterate through the inputEquation Queue and process each item in Collection
		while(equationIterator.hasNext()) {
			// Fetch next value from Iterator
			String value = equationIterator.next();
			
			// Determine if 'value' is a Double or not
			if(isNumber(value)) {
				// If 'value' is a number then add it into the outputQueue
				outputQueue.add(value);
			} else {
				// If 'value' is a operator then determine whether there is anything in the operatorStack
				if(!operatorStack.empty()) {
					// If the operatorStack is NOT empty then peek at the last value in the stack
					String lastOperator = operatorStack.peek();
					
					// Determine if the current operator is greater than the last operator in the stack
					if(precedencemap.get(value.charAt(0)) > precedencemap.get(lastOperator.charAt(0))) {
						// If it is then simply add it to the end of the operator stack
						operatorStack.add(value);
					} else {
						// If it isn't then dump the operator stack onto the output queue and then add the current operator to the head of the stack
						while(!operatorStack.empty())
							outputQueue.add(operatorStack.pop());
						
						operatorStack.add(value);
					}
				} else {
					// If the operatorStack is empty then simply add the operator into the stack
					operatorStack.add(value);
				}
			}
		}
		
		// Pop all values out of stack into outputQueue
		while(!operatorStack.empty()) {
			outputQueue.add(operatorStack.pop());
		}
		
		return outputQueue;
	}
	
	/**
	 * Solves a given arithmetic equation that has been formatted in Postfix (Polish Notation). The Postfix equation must be presented as a F.I.F.O. <code>Queue</code>.
	 * Operations are performed through the <code>BigDecimal</code> object with a 64-bit precision level (<code>MathContext.DECIMAL64</code>) in order to keep compatibility
	 * with the double data type.
	 * 
	 * @param postfixEquation the Postfix equation in <code>Queue</code> format
	 * @return the result of the calculation as a <code>BigDecimal</code>
	 * @see java.math.BigDecimal
	 * @see java.math.MathContext
	 */
	public static BigDecimal solvePostfixEquation(Queue<String> postfixEquation) {
		
		// Declare temporary Queue to hold values during an operation
		Stack<BigDecimal> operationStack = new Stack<BigDecimal>();
		
		// Declare Iterator to iterate through postfixEquation
		Iterator<String> equationIterator = postfixEquation.iterator();
		
		// Loop through entire postfixEquation and process values
		while(equationIterator.hasNext()) {
			// Fetch next value from the postfixEquation
			String value = equationIterator.next();
			
			// Declare temporary double variables that will store the appropriate operands 
			BigDecimal operandOne = new BigDecimal(0);
			BigDecimal operandTwo = new BigDecimal(0);
			
			// Check if value is a number
			if(isNumber(value))
				// If it is then add it to the operationStack
				operationStack.add(new BigDecimal(value));
			else {
				// If it isn't then perform the appropriate operation
				
				// Retrieve necessary operands
				operandTwo = operationStack.pop();
				operandOne = operationStack.pop();
				
				// Perform the operation
				switch(value.charAt(0)) {
					case '+':
						// Perform operation and then add results back into operationStack
						operationStack.add(operandOne.add(operandTwo, MathContext.DECIMAL64));
						break;
					case '-':
						operationStack.add(operandOne.subtract(operandTwo, MathContext.DECIMAL64));
						break;
					case '/':
						operationStack.add(operandOne.divide(operandTwo, MathContext.DECIMAL64));
						break;
					case '*':
						operationStack.add(operandOne.multiply(operandTwo, MathContext.DECIMAL64));
						break;
					default:
						// If unable to find appropriate operator, then throw Input Mismatch Exception
						throw new InputMismatchException("Encountered illegal operator: " + value);
				}
			}
		}
		
		return operationStack.pop();
	}
	
	/**
	 * Determines whether a <code>String</code> is a valid number value. Returns a <strong>true</strong> if the input is a valid <code>Double</code> and 
	 * <strong>false</strong> if the input is invalid.
	 * 
	 * @param input the <code>String</code> to validate
	 * @return a boolean representing the validity of the input
	 */
	public static boolean isNumber(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Determines whether a <code>Character</code> is a valid number value. Returns a <strong>true</strong> if the input is a valid <code>Double</code> 
	 * and <strong>false</strong> if the input is invalid.
	 * 
	 * @param input the <code>Character</code> to validate
	 * @return a boolean representing the validity of the input
	 */
	public static boolean isNumber(char input) {		
		return isNumber(Character.toString(input));
	}
	
}
