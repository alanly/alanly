/**
 * 
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import com.mastermind.util.Colours;
import com.mastermind.util.net.ByteComm;

/**
 * @author Alan Ly
 * @version 1.0
 */
public class GameLogic {
	
	private Socket socket;
	private byte[] buffer;
	private int[] answer;
	private int answerSize;
	
	public GameLogic(Socket socket, byte[] buffer) {
		super();
		this.socket = socket;
		this.buffer = buffer;
		this.answer = generateAnswer();
		this.answerSize = buffer.length;
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
	
	private int[] generateClue(int[] guesses) {
		return null;
	}
	
	private int[] generateAnswer() {
		int numOfColours = Colours.values().length;
		int[] colours = new int[this.answerSize];
		Random rnd = new Random();
		
		for(int i = 0; i < this.answerSize; i++)
			colours[i] = rnd.nextInt(numOfColours) + 1;
		
		return colours;
	}
	
}
