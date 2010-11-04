/**
 * 
 */
package com.mastermind.client;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mastermind.client.logic.ClientGameLogic;
import com.mastermind.client.logic.GameController;
import com.mastermind.client.ui.Mastermind;

/**
 * @author Alan Ly
 *
 */
public class ClientApplication {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Mastermind view = new Mastermind();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		
		

	}

}
