/**
 * Contains the coding that will handle the front-end interface and logic.
 */
package org.alanly.calculator.ui.gui;

import java.awt.Font;
import java.util.Observable;

/**
 * The <code>EquationDisplayPanel</code> represents the panel of the <strong>Calculator</strong> which displays the equation being processed.
 * 
 * @author Alan Ly
 * @version 1.0
 */
public class EquationDisplayPanel extends DisplayPanel {

	private static final long serialVersionUID = -7751524304676735726L;
	
	/**
	 * Default values for the panel
	 */
	private static final String DEFAULT_FONT_FACE = Font.DIALOG;
	private static final int DEFAULT_FONT_STYLE = Font.PLAIN;
	private static final int DEFAULT_FONT_SIZE = 11;

	/**
	 * Creates an <code>EquationDisplayPanel</code> with a blank initial value.
	 */
	public EquationDisplayPanel() {
		this("");
	}

	/**
	 * Creates an <code>EquationDisplayPanel</code> with a specified initial value to display upon creation of the <code>JPanel</code>.
	 * 
	 * @param initialString the initial value to display upon creation
	 */
	public EquationDisplayPanel(String initialString) {
		super(initialString, new Font(DEFAULT_FONT_FACE, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE));
	}
	
	/**
	 * Handles model updates and display results in the <code>JTextField</code>.
	 * 
	 * @see java.util.Observer#update(Observable, Object)
	 */
	@Override
	public void update(Observable observableObj, Object obj) {
		// Check if the update was from the CalculatorModel
		if(observableObj instanceof CalculatorModel)
			// Manually get the equation string
			this.setText( ( (CalculatorModel) observableObj ).getEquationString() );
	}
	
}
