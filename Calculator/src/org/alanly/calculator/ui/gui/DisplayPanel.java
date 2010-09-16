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
 * @version 2.1
 */
public class DisplayPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -475711391694355484L;
	
	/**
	 * Default values for the panel
	 */
	private static final String DEFAULT_FONT_FACE = Font.MONOSPACED;
	private static final int DEFAULT_FONT_STYLE = Font.BOLD;
	private static final int DEFAULT_FONT_SIZE = 16;
	
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
		this.setLayout(new BorderLayout());
		
		this.textField = new JTextField(initialString);
		this.textField.setFont(font);
		this.textField.setHorizontalAlignment(JTextField.RIGHT);
		this.textField.setBackground(Color.WHITE);
		this.textField.setBorder(BorderFactory.createEmptyBorder());
		this.textField.setEditable(false);
		
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
	public void setTextFieldFont(Font font) {
		this.textField.setFont(font);
	}
	
	/**
	 * Sets the font size used within the <code>JTextField</code> component based on the specified percentage.
	 * 
	 * @param percent the percentage to set the font size against
	 */
	public void setTextFieldFontSize(double percent) {
		Font currentFont = this.getTextFieldFont();
		int newFontSize = (int) (currentFont.getSize() * percent);
		
		this.setTextFieldFont(new Font(currentFont.getName(), currentFont.getStyle(), newFontSize));
	}
	
	/**
	 * Gets the current font used for the <code>JTextField</code>.
	 * 
	 * @return the font currently used
	 */
	public Font getTextFieldFont() {
		return this.textField.getFont();
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
