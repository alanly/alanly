/**
 * Contains the coding that will handle the front-end interface and logic.
 */
package org.alanly.calculator.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The <code>DisplayPanel</code> represents the panel of the Calculator containing the display panel for output.
 * 
 * @author Alan Ly
 * @version 2.1
 */
public class DisplayPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -475711391694355484L;
	private static final String FONT_FACE = Font.MONOSPACED;
	private static final int FONT_STYLE = Font.PLAIN;
	private static final int FONT_SIZE = 16;
	
	private JTextField textField;
	private int currentFontSize = FONT_SIZE;
	
	/**
	 * Creates the <code>DisplayPanel</code> with a blank initial string.
	 */
	public DisplayPanel() {
		this("");
	}
	
	/**
	 * Creates the <code>DisplayPanel</code> with a specified initial string.
	 * 
	 * @param initialString
	 */
	public DisplayPanel(String initialString) {
		super();
		initialisePanel(initialString);
	}
	
	/**
	 * Initialises the panel.
	 */
	private void initialisePanel(String initialString) {
		// Sets the panel layout manager to the BorderLayout
		setLayout(new BorderLayout());
		
		initialiseTextField(initialString);
		add(textField, BorderLayout.CENTER);
	}
	
	/**
	 * Creates and initialises the <code>JTextField</code>.
	 */
	private void initialiseTextField(String initialString) {
		textField = new JTextField(initialString);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setFont(new Font(FONT_FACE, FONT_STYLE, FONT_SIZE));
		textField.setEditable(false);
		textField.setBackground(Color.WHITE);
	}
	
	/**
	 * Sets the string displayed within the <code>JTextField</code> component.
	 * 
	 * @param text the text to be displayed in the <code>JTextField</code>
	 */
	public void setText(String text) {
		textField.setText(text);
	}
	
	/**
	 * Sets the font size used within the <code>JTextField</code> component based on the specified percentage.
	 * 
	 * @param percent the percentage to set the font size against
	 */
	public void setFontSize(double percent) {
		currentFontSize = (int) (FONT_SIZE * percent);
		textField.setFont(new Font(FONT_FACE, FONT_STYLE, currentFontSize));
	}
	
	/**
	 * Handles model updates and display results in the <code>JTextField</code>.
	 * 
	 * @see java.util.Observer#update(Observable, Object)
	 */
	@Override
	public void update(Observable observableObj, Object obj) {
		if(observableObj instanceof CalculatorModel)
			if(obj != null)
				this.setText( (String) obj );
			else
				this.setText( ( (CalculatorModel) observableObj ).getOutput() );
	}

}
