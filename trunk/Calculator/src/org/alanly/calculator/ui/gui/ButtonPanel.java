/**
 * Contains the coding that will handle the front-end interface and logic.
 */
package org.alanly.calculator.ui.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

/**
 * The <code>ButtonPanel</code> class represents the panel of the Calculator containing the buttons.
 * 
 * @author Alan Ly
 * @version 3.1
 */
public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = -2222123633760445628L;
	private static final String FONT_FACE = Font.DIALOG;
	private static final int FONT_STYLE = Font.BOLD;
	private static final int FONT_SIZE = 15;
	
	private GridBagLayout gridBagLayout;
	private ArrayList<GridBagConstraints> gridBagConstraints;
	private JButton[] jButtons;
	private CalculatorModel calculatorModel;
	private HashMap<String, String> functionMap;
	
	private String[][] buttons = {
			{"CE", "C"},
			{"7", "8", "9", "÷", "="},
			{"4", "5", "6", "x"},
			{"1", "2", "3", "-"},
			{"0", "±", ".", "+"}
	};
	
	/**
	 * Creates the <code>ButtonPanel</code> with a specified <code>CalculatorModel</code> relationship.
	 * 
	 * @param calculatorModel the model in charge of handling input and output between controller and viewer
	 */
	public ButtonPanel(CalculatorModel calculatorModel) {
		super();
		
		this.calculatorModel = calculatorModel;
		
		initialisePanel();
	}
	
	/**
	 * Initialises the <code>ButtonPanel</code> and components.
	 */
	private void initialisePanel() {			
		// Initialise GridBagLayout
		this.gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		// Initialise GridBagConstraints
		this.initialiseConstraints();
		
		// Initialise Buttons and Function Map
		this.initialiseButtons();
		this.initialiseFunctionMap();
		
		// Sets panel size to preferred size
		this.setSize(gridBagLayout.preferredLayoutSize(this));
	}
	
	/**
	 * Creates and sets the <code>GridBagConstraints</code> for the <code>GridBagLayout</code>.
	 */
	private void initialiseConstraints() {
		
		// Initialises constraints
		this.gridBagConstraints = new ArrayList<GridBagConstraints>();
		
		// Set general button constraints based on buttons array
		int buttonCount = 0;
		
		for(int row = 0; row < buttons.length; row++)
			for(int col = 0; col < buttons[row].length; col++) {
				gridBagConstraints.add(new GridBagConstraints());
				gridBagConstraints.get(buttonCount).fill = GridBagConstraints.BOTH;
				gridBagConstraints.get(buttonCount).insets = new Insets(1, 1, 1, 1);
				gridBagConstraints.get(buttonCount).gridx = col;
				gridBagConstraints.get(buttonCount).gridy = row;
				gridBagConstraints.get(buttonCount).weightx = 1.0;
				gridBagConstraints.get(buttonCount).weighty = 1.0;
				buttonCount++;
			}
		
		// Set constraints for CE button
		this.gridBagConstraints.get(0).gridx = 1;
		this.gridBagConstraints.get(0).gridy = 0;
		this.gridBagConstraints.get(0).gridwidth = 2;
		
		// Set constraints for C button
		this.gridBagConstraints.get(1).gridx = 3;
		this.gridBagConstraints.get(1).gridy = 0;
		this.gridBagConstraints.get(1).gridwidth = 2;
		
		// Set constraints for = button
		this.gridBagConstraints.get(6).gridheight = 4;
	}
	
	/**
	 * Creates and initialises the <code>JButtons</code> as well as sets their sizes.
	 */
	private void initialiseButtons() {
		// Initialise JButtons array
		this.jButtons = new JButton[gridBagConstraints.size()];
		
		// Create global listeners objects for all buttons
		ButtonKeyListener keyListener = new ButtonKeyListener();
		ButtonActionListener actionListener = new ButtonActionListener();
		
		// Create border for JButtons
		CompoundBorder border = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Create and add buttons
		for(int row = 0, buttonCount = 0; row < buttons.length; row++)
			for(int col = 0; col < buttons[row].length; col++, buttonCount++) {
				jButtons[buttonCount] = new JButton(buttons[row][col]);
				jButtons[buttonCount].setFont(new Font(FONT_FACE, FONT_STYLE, FONT_SIZE));
				jButtons[buttonCount].setBorder(border);
				jButtons[buttonCount].addKeyListener(keyListener);
				jButtons[buttonCount].addActionListener(actionListener);
				add(jButtons[buttonCount], gridBagConstraints.get(buttonCount));
			}
		
		// Create temporary variables to hold sizes
		Dimension newSize = new Dimension(0, 0);
		Dimension currentSize = new Dimension(0, 0);

		// Get the size of each button and preserve the widest
		for (int i = 0; i < gridBagConstraints.size(); i++) {
			currentSize = jButtons[i].getPreferredSize();
			if (currentSize.width > newSize.width)
				newSize.width = currentSize.width;
		}
		
		// Set height to width for square buttons
		newSize.height = newSize.width;

		// Set the new preferred size for each button
		for (int i = 0; i < gridBagConstraints.size(); i++) {
			jButtons[i].setPreferredSize(newSize);
			jButtons[i].setMinimumSize(newSize);
		}
	}
	
	/**
	 * Initialises the <code>HashMap</code> with the appropriate function display and backend values.
	 */
	private void initialiseFunctionMap() {
		// Initialise HashMap
		this.functionMap = new HashMap<String, String>();
		
		// Fill values
		functionMap.put("÷", "/");
		functionMap.put("x", "*");
		functionMap.put("±", "~");
		functionMap.put("+", "+");
		functionMap.put("-", "-");
	}
	
	/**
	 * Sets the focus in the panel to a button.
	 */
	public void setFocusToButton() {
		jButtons[0].requestFocusInWindow();
	}
	
	/**
	 * Sets the font size used for each <code>JButton</code> component based on the specified percentage.
	 * 
	 * @param percent the percentage to set the font size against
	 */
	public void setFontSize(double percent) {
		int currentFontSize = (int) (FONT_SIZE * percent);
		
		for(int i = 0; i < jButtons.length; i++)
			jButtons[i].setFont(new Font(FONT_FACE, FONT_STYLE, currentFontSize));
	}
	
	/**
	 * Inner-class <code>ActionListener</code> to respond to button actions.
	 * 
	 * @author Alan Ly
	 * @version 1.0
	 * @since 2.0
	 */
	class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();
			
			if(functionMap.get(buttonText) == null)
				calculatorModel.setInput(buttonText);
			else
				calculatorModel.setInput(functionMap.get(buttonText));
		}
		
	}
	
	/**
	 * Inner-class <code>KeyAdapter</code> listener to respond to keyboard input.
	 * 
	 * @author Alan Ly
	 * @version 1.1
	 * @since 2.1
	 */
	class ButtonKeyListener extends KeyAdapter {
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
		 */
		public void keyTyped(KeyEvent e) {
			int keyChar = e.getKeyChar();
			
			switch (keyChar) {
			// The numerical keys
			case '0': // The '0' key and etc.
				jButtons[15].doClick();
				break;
			case '1':
				jButtons[11].doClick();
				break;
			case '2':
				jButtons[12].doClick();
				break;
			case '3':
				jButtons[13].doClick();
				break;
			case '4':
				jButtons[7].doClick();
				break;
			case '5':
				jButtons[8].doClick();
				break;
			case '6':
				jButtons[9].doClick();
				break;
			case '7':
				jButtons[2].doClick();
				break;
			case '8':
				jButtons[3].doClick();
				break;
			case '9':
				jButtons[4].doClick();
				break;
			case '.':
				jButtons[17].doClick();
				break;
				
			// The operators
			case '+': // The addition function
				jButtons[18].doClick();
				break;
			case '-': // The subtraction function
				jButtons[14].doClick();
				break;
			case '*': // The multiplication function
			case 'x':
			case 'X':
				jButtons[10].doClick();
				break;
			case '/': // The division function
				jButtons[5].doClick();
				break;
			case '=': // The equals/execute/solve function
			case KeyEvent.VK_ENTER:
				jButtons[6].doClick();
				break;
			case '~': // The negate function
				jButtons[16].doClick();
				break;
				
			// The clear buttons
			case 'e': // Clear Last Entry (CE) key
			case 'E':
			case KeyEvent.VK_BACK_SPACE:
				jButtons[0].doClick();
				break;
			case 'c': // Clear Equation (C) key
			case 'C':
			case KeyEvent.VK_DELETE:
				jButtons[1].doClick();
				break;
			}
		}
		
	}
}
