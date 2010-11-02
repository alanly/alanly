/**
 * 
 */
package com.mastermind.client.logic;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Alan Ly
 *
 */
public class GameBoard {

	// Global variables related to connection
	private Socket socket;
	
	// Global variables related to game
	private int guessCount;
	private boolean lostGame;
	
	public GameBoard(String serverAddress, int serverPort) throws IOException {
		super();
		this.socket = new Socket(serverAddress, serverPort);
	}
	
	private void initialiseGame() {
		this.guessCount = 0;
		this.lostGame = false;
	}
	
	public void startGame() {
		
	}
	
	public void endGame() {
		
	}
	
	public void validateGuess() {
		
	}
	
}
