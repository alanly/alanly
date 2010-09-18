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
 * <ul><table border="1">
 * 		<tr><td><strong>Key Value</strong></td><td><strong>Function</strong></td></tr>
 *	 	<tr><td><code>+</code></td><td>Addition</td></tr>
 *		<tr><td><code>-</code></td><td>Subtraction</td></tr>
 *	 	<tr><td><code>*</code></td><td>Multiplication</td></tr>
 *	 	<tr><td><code>/</code></td><td>Division</td></tr>
 *	 	<tr><td><code>.</code></td><td>Decimal point</td></tr>
 *	 	<tr><td><code>~</code></td><td>Negate value</td></tr>
 * 		<tr><td><code>=</code></td><td>Solve equation</td></tr>
 * 		<tr><td><code>CE</code></td><td>Clear Entry up to last previous operand</td></tr>
 * 		<tr><td><code>C</code></td><td>Clear calculator</td></tr>
 * </table></ul>
 * 
 * Invalid input values will throw an <code>InputMismatchException</code>.
 * 
 * @author Alan Ly
 * @version 1.3
 */
public class CalculatorInputHandler {
	
	/**
	 * The maximum length of the input value
	 */
	private static final int MAX_VALUE_LENGTH = 16;
	
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
		// Create temporary string and iterator
		String equationString = "";
		Iterator<String> equation = this.equationDeque.iterator();
		
		// Loop with iterator and append values onto string
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
		// Check to make sure the key string isn't null
		if(key != null && !key.equals("")) {
			
			// Check if its necessary to reset the temporary value
			if(this.resetTemp) {
				this.tempValue = "";
				this.resetTemp = false;
			}
			
			// Check if the temporary value has exceeded the allowable length
			if(this.tempValue.length() >= MAX_VALUE_LENGTH) {
				this.numericsEnabled = false;
				this.decimalEnabled = false;
			}
			
			// Determine which key called and perform the appropriate processing
			switch(key.charAt(0)) {
				case '=':
					// Check if the equation has already been solved and if the temporary value is empty
					if(!this.equationParser.isSolved() && !this.tempValue.equals(""))
						try {
							// Add the operand held in the operand store into the deque
							this.equationDeque.offer(this.tempValue);
									
							// Send the finalised equation to the parser
							this.equationParser.setInputQueue(this.equationDeque);
									
							// Solve the equation
							this.tempValue = this.equationParser.solve().toPlainString();
							
							System.out.println("Infix = " + this.equationParser.getEquationString());

							Iterator<String> iter = this.equationParser.generatePostfix(this.equationParser.getEquationQueue()).iterator();
							System.out.print("Postfix = ");
							while(iter.hasNext()) {
								System.out.print(iter.next() + " ");
							}
								
							this.equationDeque.offer("=");
									
							// Set the key booleans
							this.operatorsEnabled = true;
							this.numericsEnabled = true;
							this.decimalEnabled = false;
							this.resetTemp = false;
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
						if(!equationParser.isNumber(this.equationDeque.peekLast())) {
							// If not a number, then remove last two elements
							this.equationDeque.pollLast();
							this.equationDeque.pollLast();
						}
						
						// Reset boolean values
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
					if(equationParser.isNumber(key)) {
						// Checks if the numerical keys are enabled or not
						if(this.numericsEnabled) {							
							// Checks if the temporary value is the default value or if the equations been solved already
							if(this.tempValue.equals("0") || this.equationParser.isSolved()) {
								// Replaces the temporary values existing data with the key value and sets the appropriate state
								this.tempValue = key;
								this.decimalEnabled = true;
								this.equationDeque.clear();
								this.equationParser.resetSolved();
							} else
								// Appends the key value onto the temporary value
								this.tempValue += key;
							
							// Reset boolean values
							this.operatorsEnabled = true;
							this.numericsEnabled = true;
							this.resetTemp = false;
						}
					} else {
						// Check if the last value in the deque is an operator or not
						if(operatorsEnabled) {
							// If equation has been solved, then clear the equation deque
							if(this.equationParser.isSolved()) {
								// Resets the state of objects
								this.equationDeque.clear();
								this.equationParser.resetSolved();
							}
							
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

}
