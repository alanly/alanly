/**
 * Contains the main method to launch the application in in the desired interface
 */
package org.alanly.calculator;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.alanly.calculator.ui.gui.CalculatorFrame;

/**
 * The <code>GUILauncher</code> contains the necessary behaviours to launch the <strong>Calculator</strong> application with a graphical user interface.
 * 
 * @author Alan Ly
 * @version 1.0
 */
public class GUILauncher {

	/**
	 * Launches the <strong>Calculator</strong> with a graphical user interface.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				CalculatorFrame calcFrame = new CalculatorFrame("Calculator");
				calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				calcFrame.setLocationRelativeTo(null);
			}
			
		});
	}

}
