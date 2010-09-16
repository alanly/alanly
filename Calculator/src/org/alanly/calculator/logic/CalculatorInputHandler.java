/**
 * Contains the necessary logic classes to perform basic Calculator functions.
 */
package org.alanly.calculator.logic;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * The <code>CalculatorInputHandler</code> takes care of all user input to the calculator and produces the appropriate output.
 * 
 * Valid key input values are listed in the following table:
 * 
 * <table>
 * 	<tr><td><strong>Key Value</strong></td><td><strong>Function</strong></td></tr>
 * 	<tr><td><code>+</code></td><td>Addition</td></tr>
 * 	<tr><td><code>-</code></td><td>Subtraction</td></tr>
 * 	<tr><td><code>*</code></td><td>Multiplication</td></tr>
 * 	<tr><td><code>/</code></td><td>Division</td></tr>
 * 	<tr><td><code>.</code></td><td>Decimal point</td></tr>
 * 	<tr><td><code>~</code></td><td>Negate value</td></tr>
 * 	<tr><td><code>=</code></td><td>Solve equation</td></tr>
 * 	<tr><td><code>CE</code></td><td>Clear Entry up to last previous operand</td></tr>
 * 	<tr><td><code>C</code></td><td>Clear calculator</td></tr>
 * </table>
 * 
 * Invalid input values will throw an <code>InputMismatchException</code>.
 * 
 * @author Alan Ly
 * @version 1.3
 */
public class CalculatorInputHandler {
	
	private String tempValue;
	private ArrayDeque<String> equationDeque;
	private EquationParser equationParser;
	private boolean operatorsEnabled;
	private boolean numericsEnabled;
	private boolean decimalEnabled;
	private boolean resetTemp;
	
	/**
	 * Creates a <code>CalculatorInputHandler</code> with default values.
	 */
	public CalculatorInputHandler() {
		this.initialise();
	}
	
	/**
	 * Gets the output as a result of the user input action.
	 * 
	 * @return the output results
	 */
	public String getOutput() {
		return this.tempValue;
	}
	
	/**
	 * Gets the <code>Deque</code> that contains the stored equation.
	 * 
	 * @return the equation in an ArrayDeque, type String
	 */
	public ArrayDeque<String> getEquationDeque() {
		return this.equationDeque;
	}
	
	/**
	 * Gets the stored equation in the form of a String value. Operands and operators are delimited by a single space.
	 * 
	 * @return the equation in a String
	 */
	public String getEquationString() {
		String equationString = "";
		Iterator<String> equation = this.equationDeque.iterator();
		
		while(equation.hasNext())
			equationString += equation.next() + " ";
		
		return equationString;
	}
	
	/**
	 * Initialises the object with default values.
	 */
	private void initialise() {
		this.tempValue = "0";
		this.equationDeque = new ArrayDeque<String>();
		this.equationParser = new EquationParser();
		this.operatorsEnabled = false;
		this.numericsEnabled = true;
		this.decimalEnabled = true;
		this.resetTemp = true;
	}
	
	/**
	 * Performs all the necessary functions in order to process a key input specified by the <code>String</code> parameter.
	 * 
	 * @param key the input key value
	 */
	public void keyInput(String key) {
		// If equation has been solved, then clear the equation deque
		if(this.equationParser.isSolved())
			this.equationDeque.clear();
		
		// Check to make sure the key string isn't null
		if(key != null)
			switch(key.charAt(0)) {
				case '=':
					try {
						// Add the operand held in the operand store into the deque
						this.equationDeque.offer(this.tempValue);
							
						// Send the finalised equation to the parser
						this.equationParser.setInputQueue(this.equationDeque);
							
						// Solve the equation
						this.tempValue = this.equationParser.solve().toString();
						
						this.equationDeque.offer("=");
							
						// Set the key booleans
						this.operatorsEnabled = true;
						this.numericsEnabled = false;
						this.decimalEnabled = false;
					} catch(ArithmeticException ae) {
						// Get the exception message
						this.tempValue = ae.getMessage();
						
						// Set the key booleans
						this.operatorsEnabled = false;
						this.numericsEnabled = false;
						this.decimalEnabled = false;
					}
					
					break;
				case '.':
					// Check if the decimal key is enabled
					if(decimalEnabled) {
						// Add a decimal point to the end of the current value
						this.tempValue += ".";
						
						// Disable the decimal
						this.decimalEnabled = false;
					}
					
					break;
				case '~':
					// Check if operator keys are enabled
					if(this.numericsEnabled)
						// Check if the stored operand starts with a negative sign
						if(this.tempValue.startsWith("-"))
							// Remove the negative sign
							this.tempValue = this.tempValue.substring(1);
						else
							// Add the negative sign
							this.tempValue = "-" + this.tempValue;
					
					break;
				case 'C':
					// Check if the key called was Clear Entry or Clear
					if(key.equalsIgnoreCase("CE")) {
						// Clear temporary operand store
						this.tempValue = "0";
						
						// Check if the last value in the deque is a number or not
						if(!EquationUtilities.isNumber(this.equationDeque.peekLast())) {
							// If not a number, then remove last two elements
							this.equationDeque.pollLast();
							this.equationDeque.pollLast();
						}
						
						this.operatorsEnabled = false;
						this.numericsEnabled = true;
						this.decimalEnabled = true;
						this.resetTemp = true;
					} else
						// Reset values if Clear key is called
						this.initialise();
						
					break;
				default:
					// Check if the key called is a numeric key or an operator key and check if numeric keys are enabled
					if(EquationUtilities.isNumber(key) && this.numericsEnabled) {
						if(this.tempValue.equals("0") || resetTemp)
							this.tempValue = key;
						else
							this.tempValue += key;
						
						this.operatorsEnabled = true;
						this.numericsEnabled = true;
						this.decimalEnabled = true;
						this.resetTemp = false;
					} else {
						// Check if the last value in the deque is an operator or not
						if(operatorsEnabled) {
							// Add the operator to the deque
							this.equationDeque.offer(this.tempValue);
							this.equationDeque.offer(key);

							// Set key booleans
							this.operatorsEnabled = false;
							this.numericsEnabled = true;
							this.decimalEnabled = true;
							this.resetTemp = true;
						}
					}
			}
	}

}
