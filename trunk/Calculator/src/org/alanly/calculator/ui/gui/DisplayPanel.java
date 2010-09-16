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
	
	/**
	 * Default values for the panel
	 */
	private static final String DEFAULT_FONT_FACE = Font.MONOSPACED;
	private static final int DEFAULT_FONT_STYLE = Font.PLAIN;
	private static final int DEFAULT_FONT_SIZE = 16;
	
	private Font font;
	private JTextField textField;
	
	/**
	 * Creates the <code>DisplayPanel</code> with a blank initial string and default <code>Font</code> style.
	 */
	public DisplayPanel() {
		this("", new Font(DEFAULT_FONT_FACE, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE));
	}
	
	/**
	 * Creates the <code>DisplayPanel</code> with a specified initial string and default <code>Font</code> style.
	 * 
	 * @param initialString the string to display when the panel is created
	 */
	public DisplayPanel(String initialString) {
		this(initialString, new Font(DEFAULT_FONT_FACE, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE));
	}
	
	/**
	 * Creates the <code>DisplayPanel</code> with a blank initial string and a specified <code>Font</code> style.
	 * 
	 * @param font the font to use when the panel is created
	 */
	public DisplayPanel(Font font) {
		this("", font);
	}
	
	/**
	 * Creates the <code>DisplayPanel</code> with a specified initial string and a specified <code>Font</code> style.
	 * 
	 * @param initialString the string to display when the panel is created
	 * @param font the font to use when the panel is created
	 */
	public DisplayPanel(String initialString, Font font) {
		super();
		this.initialisePanel(initialString, font);
	}
	
	/**
	 * Initialises the panel.
	 */
	private void initialisePanel(String initialString, Font font) {
		// Sets the panel layout manager to the BorderLayout
		setLayout(new BorderLayout());
		
		initialiseTextField(initialString, font);
		add(textField, BorderLayout.CENTER);
	}
	
	/**
	 * Creates and initialises the <code>JTextField</code>.
	 */
	private void initialiseTextField(String initialString, Font font) {
		textField = new JTextField(initialString);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setFont(font);
		textField.setEditable(false);
		textField.setBackground(Color.WHITE);
	}
	
	/**
	 * Sets the string displayed within the <code>JTextField</code> component.
	 * 
	 * @param text the text to be displayed in the <code>JTextField</code>
	 */
	public void setText(String text) {
		this.textField.setText(text);
	}
	
	/**
	 * Sets the font properties that are used to present text in the <code>JTextField</code>.
	 * 
	 * @param font the font to use
	 * @see java.awt.Font
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	
	/**
	 * Sets the font size used within the <code>JTextField</code> component based on the specified percentage.
	 * 
	 * @param percent the percentage to set the font size against
	 */
	public void setFontSize(double percent) {
		int currentFontSize = (int) (DEFAULT_FONT_SIZE * percent);
		textField.setFont(new Font(DEFAULT_FONT_FACE, DEFAULT_FONT_STYLE, currentFontSize));
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
