/**
 * 
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.Socket;

import com.mastermind.util.net.ByteComm;

/**
 * @author Alan Ly
 *
 */
public class GameLogic {
	
	private Socket socket;
	private byte[] buffer;
	private int[] answer;
	
	public GameLogic(Socket socket, byte[] buffer) {
		super();
		this.socket = socket;
		this.buffer = buffer;
	}
	
	public void startGame() throws IOException {
		int receiveSize = 0;
		
		while((receiveSize = ByteComm.receive(this.socket, this.buffer)) != -1) {
	        // TODO implement Mastermind Game Logic instance code here
	    }
	}
	
	private void newGame() {
		
	}
	
	private void endGame() {
		
	}
	
	private int[] validateGuess(int[] guesses) {
		return null;
	}
	
	private void generateAnswer() {
		
	}
	
}
