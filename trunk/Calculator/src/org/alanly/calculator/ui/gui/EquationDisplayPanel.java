/**
 * 
 */
package org.alanly.calculator.ui.gui;

import java.awt.Font;
import java.util.Observable;

/**
 * @author Alan Ly
 *
 */
public class EquationDisplayPanel extends DisplayPanel {

	private static final long serialVersionUID = -7751524304676735726L;
	
	/**
	 * Default values for the panel
	 */
	private static final String DEFAULT_FONT_FACE = Font.MONOSPACED;
	private static final int DEFAULT_FONT_STYLE = Font.PLAIN;
	private static final int DEFAULT_FONT_SIZE = 12;

	/**
	 * 
	 */
	public EquationDisplayPanel() {
		this("");
	}

	/**
	 * @param initialString
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
		if(observableObj instanceof CalculatorModel)
			this.setText( ( (CalculatorModel) observableObj ).getEquationString() );
	}
	
}
