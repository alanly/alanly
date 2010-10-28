/**
 * 
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import com.mastermind.util.Colour;
import com.mastermind.util.net.ByteComm;

/**
 * The <code>GameLogic</code> class represents the logical backend properties of a <strong>Mastermind</strong> game.
 * 
 * It contains the necessary behaviour required to interact and play a game of <strong>Mastermind</strong> with a client session.
 * 
 * @author Alan Ly
 * @version 1.3
 */
public class GameLogic {
	
	private static final int ANSWER_SIZE = 4;
	private static final int MAX_GUESSES = 8;
	
	private Socket socket;
	private byte[] buffer;
	private int[] answer;
	private int guessCount;
	
	/**
	 * Initialises a <code>GameLogic</code> instance with a specified <code>clientSocket</code> and <code>buffer</code> array.
	 * 
	 * @param clientSocket a reference to the {@link java.net.Socket Socket} for the client connection
	 * @param buffer a <strong>byte</strong> array that will be used to temporarily store data received from the <code>clientSocket</code>
	 */
	public GameLogic(Socket clientSocket, byte[] buffer) {
		super();
		this.socket = clientSocket;
		this.buffer = buffer;
		this.answer = generateAnswer(ANSWER_SIZE);
		
	}
	
	/**
	 * Sets the appropriate state for the start of a game or for a new game.
	 */
	private void initialiseGame() {
		this.answer = generateAnswer(ANSWER_SIZE);
		this.guessCount = 0;
	}
	
	public void startGame() throws IOException {
		int receiveSize = 0;
		
		while((receiveSize = ByteComm.receive(this.socket, this.buffer)) != -1) {
	        // TODO implement Mastermind Game Logic instance code here
	    }
	}
	
	/**
	 * Generates the appropriate clues from the given <code>guesses</code> in comparison with the <code>answer</code>.
	 * 
	 * The return value consists of an Integer array holding the appropriate clues.
	 * Clues are represented by one of three values,
	 * <ul>
	 * 	<li><strong><code>0</code></strong> - represents a blank clue (as in, no clue).</li>
	 * 	<li><strong><code>1</code></strong> - represents a partial match wherein a colour matches, however the position is incorrect.</li>
	 * 	<li><strong><code>2</code></strong> - represents an absolute match wherein both the colour and position matches.</li>
	 * </ul>
	 * 
	 * @param guess an <strong>int</strong> array containing the guesses which are the selected {@link com.mastermind.util.Colour Colour} values
	 * @param answer an <strong>int</strong> array containing the answers which are a set of selected {@link com.mastermind.util.Colour Colour} values
	 * @return an <strong>int</strong> array containing the appropriate clues
	 * @see com.mastermind.util.Colour
	 */
	private int[] generateClue(int[] guess, int[] answer) {
		int[] clues = new int[answer.length];
		
		for(int i = 0; i < answer.length; i++) {
			int indirectMatch = -1;
			
			for(int j = 0; j < guess.length; j++)
				if(answer[i] == guess[j])
					if(i == j || answer[j] == guess[j]) {
						clues[i] = 2;
						indirectMatch = -1;
						guess[j] = -1;
						break;
					} else
						indirectMatch = j;
			
			if(indirectMatch != -1) {
				clues[i] = 1;
				guess[indirectMatch] = -1;
			}
		}

		return clues;
	}
	
	/**
	 * Generates a random answer set with a length of <code>size</code>. Colour values are taken from the {@link com.mastermind.util.Colour Colour} enum.
	 * 
	 * @param size the size of the resulting answer set
	 * @return an integer array containing a generated answer set with length of <code>size</code>
	 * @see com.mastermind.util.Colour
	 */
	private int[] generateAnswer(int size) {
		// Initialise variables
		int numOfColours = Colour.values().length;
		int[] colours = new int[size];
		
		// Create an instance of Random
		Random rnd = new Random();
		
		// Iterate through 'colours' and populate array with randomized values
		for(int i = 0; i < size; i++)
			colours[i] = Colour.values()[rnd.nextInt(numOfColours)].toInteger();
		
		return colours;
	}
	
}
