/**
 * Contains the coding that will handle the front-end interface and logic.
 */
package org.alanly.calculator.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The <code>DisplayPanel</code> represents the panel of the Calculator containing the display panel for output.
 * 
 * @author Alan Ly
 * @version 2.2
 */
public class DisplayPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -475711391694355484L;
	
	/**
	 * Default values for the panel
	 */
	private static final String DEFAULT_FONT_FACE = Font.MONOSPACED;
	private static final int DEFAULT_FONT_STYLE = Font.BOLD;
	private static final int DEFAULT_FONT_SIZE = 15;
	
	private Font currentFont;
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
	 * Creates the <code>DisplayPanel</code> with a specified font style and default initial string.
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
		this.initialise(initialString, font);
	}
	
	private void initialise(String initialString, Font font) {

		// Set layout for this panel
		this.setLayout(new BorderLayout());
		
		// Set initial properties for the JTextField
		this.textField = new JTextField(initialString);
		this.textField.setHorizontalAlignment(JTextField.RIGHT);
		this.textField.setBackground(new Color(205, 223, 204));
		this.textField.setBorder(BorderFactory.createEmptyBorder());
		this.textField.setEditable(false);
		
		// Set the font
		this.setCurrentFont(font);
		
		// Add the component into the layout manager
		add(this.textField, BorderLayout.CENTER);
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
	public void setCurrentFont(Font font) {
		this.currentFont = font;
		
		this.textField.setFont(this.currentFont);
	}
	
	/**
	 * Sets the font size used within the <code>JTextField</code> component based on the specified percentage.
	 * 
	 * @param percent the percentage to set the font size against
	 */
	public void adjustFontSize(double percent) {		
		// Create the new font size based on the set size and percentage
		int newFontSize = (int) (this.currentFont.getSize() * percent);
		
		// Set the new font
		this.setCurrentFont(new Font(this.currentFont.getName(), this.currentFont.getStyle(), newFontSize));
	}
	
	/**
	 * Gets the current font used for the <code>JTextField</code>.
	 * 
	 * @return the font currently used
	 */
	public Font getCurrentFont() {
		return this.currentFont;
	}
	
	/**
	 * Handles model updates and display results in the <code>JTextField</code>.
	 * 
	 * @see java.util.Observer#update(Observable, Object)
	 */
	@Override
	public void update(Observable observableObj, Object obj) {
		// Check if the update was from CalculatorModel
		if(observableObj instanceof CalculatorModel)
			// If secondary parameter is not null then it should contain the string
			if(obj != null)
				this.setText( (String) obj );
			else
				// If not, then we will manually fetch the string
				this.setText( ( (CalculatorModel) observableObj ).getOutput() );
	}

}
