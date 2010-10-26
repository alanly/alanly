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
	//private int answerSize;
	
	public GameLogic(Socket socket, byte[] buffer) {
		super();
		this.socket = socket;
		this.buffer = buffer;
		//this.answer = generateAnswer();
		this.answer = new int[] { 1, 2, 3, 4 };
		//this.answerSize = buffer.length;
		
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
	
	public int[] generateClue(int[] guesses) {
		int[] clues = new int[4];
		
		for(int i = 0; i < answer.length; i++)
			for(int j = 0; j < guesses.length; j++)
				if(answer[i] == guesses[j])
					if(i == j) {
						clues[i] = 2;
						guesses[j] = -1;
						break;
					} else {
						if(answer[j] == guesses[j])
							clues[i] = 2;
						else
							clues[i] = 1;

						guesses[j] = -1;
						//break;
					}
		
		return clues;
	}
	
	private int[] generateAnswer() {
		int numOfColours = Colours.values().length;
		//int[] colours = new int[this.answerSize];
		Random rnd = new Random();
		
		//for(int i = 0; i < this.answerSize; i++)
		//	colours[i] = rnd.nextInt(numOfColours) + 1;
		
		//return colours;
		return null;
	}
	
}
