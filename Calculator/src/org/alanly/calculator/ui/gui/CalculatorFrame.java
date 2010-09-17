/**
 * Contains the coding that will handle the front-end interface and logic.
 */
package org.alanly.calculator.ui.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

/**
 * The <code>CalculatorFrame</code> represents Calculator interface.
 * 
 * @author Alan Ly
 * @version 1.5
 */
public class CalculatorFrame extends JFrame {

	private static final long serialVersionUID = 7311732750377812837L;
	private static final String DEFAULT_FRAME_TITLE = "jCalc";
	
	private CalculatorModel calculatorModel;
	private DisplayPanel displayPanel;
	private EquationDisplayPanel equationDisplayPanel;
	private ButtonPanel buttonPanel;
	
	/**
	 * Creates a <code>CaluclatorFrame</code>.
	 */
	public CalculatorFrame() {
		this(DEFAULT_FRAME_TITLE);
	}
	
	/**
	 * Creates a <code>CalculatorFrame</code> with a specified <code>JFrame</code> title.
	 */
	public CalculatorFrame(String frameTitle) {		
		// Initialise panels and Observable
		this.displayPanel = new DisplayPanel("0");
		this.equationDisplayPanel = new EquationDisplayPanel();
		this.calculatorModel = new CalculatorModel();
		this.buttonPanel = new ButtonPanel(calculatorModel);
		
		// Initialise the model
		this.initialiseModel();
		
		// Initialise the frame with a title
		this.initialiseFrame(frameTitle);
		
		// Set frame visible and set focus to button
		this.setVisible(true);
		this.buttonPanel.setFocusToButton();
	}
	
	/**
	 * Initialises the attached model and sets up the observers.
	 */
	private void initialiseModel() {
		// Add ButtonPanel Observers
		calculatorModel.addObserver(displayPanel);
		calculatorModel.addObserver(equationDisplayPanel);
	}
	
	/**
	 * Initialises the frame.
	 */
	private void initialiseFrame(String frameTitle) {
		// Create GridBag
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		// Set layout manager for Frame
		this.setLayout(gridBagLayout);
		
		// Default Gridbag Constraint values
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		// Create constraint for equation display panel
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.add(equationDisplayPanel, gridBagConstraints);
		
		// Create constraint for display panel
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		this.add(displayPanel, gridBagConstraints);
		
		// Create constraints for button panel
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		this.add(buttonPanel, gridBagConstraints);
		buttonPanel.requestFocusInWindow();
		
		// Set frame properties
		this.setTitle(frameTitle);
		this.pack();
		
		// Bind component listener
		this.addComponentListener(new FrameSizeListener(this.getPreferredSize()));
	}
	
	/**
	 * The <code>FrameSizeListener</code> responds to the resizing of the <code>JFrame</code> and makes sure that components and font sizes scale appropriately.
	 * 
	 * @author Alan Ly
	 * @version 1.0
	 * @since 1.0
	 */
	class FrameSizeListener extends ComponentAdapter {
		Dimension preferredFrameSize;
		
		public FrameSizeListener(Dimension preferredFrameSize) {
			super();
			this.preferredFrameSize = preferredFrameSize;
		}
		
		public void componentResized(ComponentEvent e) {
			if(e.getComponent() instanceof CalculatorFrame) {
				CalculatorFrame calcFrame = (CalculatorFrame) e.getComponent();
				
				Dimension newSize = calcFrame.getSize();
				
				double percent = (double) newSize.height / this.preferredFrameSize.height;
				buttonPanel.setFontSize(percent);
				equationDisplayPanel.setTextFieldFontSize(percent);
				displayPanel.setTextFieldFontSize(percent);
			}
		}
	}

}
