/**
 * 
 */
package com.mastermind.client;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.mastermind.client.logic.GameBoard;
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
		int port;
		String ip;
		
		port = Integer.parseInt(JOptionPane.showInputDialog("Enter port number to connect to :"));
		ip = JOptionPane.showInputDialog("Enter a connection IP address: " );
		Mastermind view = new Mastermind();
		GameBoard game = new GameBoard(ip, port);
		GameController controller = new GameController(game,view);
				
		
		

	}

}
