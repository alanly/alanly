/**
 * 
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import com.mastermind.util.Colour;
import com.mastermind.util.net.ByteComm;
import com.mastermind.util.net.ByteProtocol;

/**
 * The <code>GameLogic</code> class represents the logical backend properties of a <strong>Mastermind</strong> game.
 * 
 * It contains the necessary behaviour required to interact and play a game of <strong>Mastermind</strong> with a client session.
 * 
 * @author Alan Ly
 * @version 1.6
 */
public class GameLogic {
	
	/**
	 * The size of the answer, as in the number of values that make up the answer.
	 */
	public static final int ANSWER_SIZE = 4;
	
	/**
	 * The maximum number of guesses that are allowed before the user loses.
	 */
	public static final int MAX_GUESSES = 10;
	
	// Global variables related to connection
	private Socket socket;
	private byte[] buffer;
	private boolean clientConnected;
	private int bufferSize;
	
	// Global variables related to game
	private int[] answer;
	private int guessCount;
	private boolean lostGame;
	
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
		this.clientConnected = true;
		this.bufferSize = buffer.length;
		this.initialiseGame();
	}
	
	/**
	 * Sets the appropriate state for the start of a game or for a new game.
	 */
	private void initialiseGame() {
		this.answer = generateAnswer(ANSWER_SIZE);
		this.guessCount = 0;
		this.lostGame = false;
	}
	
	/**
	 * Starts this <strong>GameLogic</strong> instance and plays a game of <strong>Mastermind</strong> with the client.
	 * 
	 * @throws IOException thrown if an error occurs when handling the client socket
	 */
	public void start() throws IOException {
		
		// Handle the client
		while(true) {
			
			// Retrieve the message array from the client; if size of -1 is returned, then client has disconnected
			for(int receiveSize = 0; receiveSize < bufferSize; receiveSize = ByteComm.receive(this.socket, this.buffer))
				if(receiveSize == -1) {
					this.clientConnected = false;
					break;
				}
			
			// If the client is no longer connected, then break the loop
			if(!this.clientConnected)
				break;
			else
				// Handle the client request
				this.handleRequest();
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
	 * @param guesses an <strong>int</strong> array containing the guesses which are the selected {@link com.mastermind.util.Colour Colour} values
	 * @param answer an <strong>int</strong> array containing the answers which are a set of selected {@link com.mastermind.util.Colour Colour} values
	 * @return an <strong>int</strong> array containing the appropriate clues
	 * @see com.mastermind.util.Colour
	 */
	private int[] generateClue(int[] guesses, int[] answer) {
		int[] clues = new int[answer.length];
		
		for(int i = 0; i < answer.length; i++) {
			int indirectMatch = -1;
			
			for(int j = 0; j < guesses.length; j++)
				if(answer[i] == guesses[j])
					if(i == j || answer[j] == guesses[j]) {
						clues[i] = 2;
						indirectMatch = -1;
						guesses[j] = -1;
						break;
					} else
						indirectMatch = j;
			
			if(indirectMatch != -1) {
				clues[i] = 1;
				guesses[indirectMatch] = -1;
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
			colours[i] = Colour.values()[rnd.nextInt(numOfColours)].toInt();
		
		return colours;
	}
	
	/**
	 * Handles the client request in the <strong>buffer</strong> array.
	 * 
	 * @throws IOException thrown when an IOException occurs when sending a reply to the client
	 */
	private void handleRequest() throws IOException {
		// Figure what type of message it is using the message header (element 0),
		switch(this.buffer[0]) {

			// Start new game
			case ByteProtocol.START_GAME_HEADER:
				// Reset state
				this.initialiseGame();
				
				///
				// Send the answer back to the client
				///
				
				// Create a new buffer array
				this.buffer = new byte[bufferSize];
				
				// Set the message header
				this.buffer[0] = ByteProtocol.START_GAME_HEADER;
				
				// Add success message into the buffer
				for(int i = 1; i < bufferSize; i++)
					buffer[i] = (byte) (ByteProtocol.START_GAME_SUCCESS);
				
				// Send buffer content to the client
				ByteComm.send(this.socket, this.buffer);
				
				break;
			
			// End current game and send the answer
			case ByteProtocol.END_GAME_HEADER:
				// Set 'lost' state
				this.lostGame = true;
				
				///
				// Send the answer back to the client
				///
				
				// Create a new buffer array
				this.buffer = new byte[bufferSize];
				
				// Set the message header
				this.buffer[0] = ByteProtocol.END_GAME_HEADER;
				
				// Add answer into buffer; encode with prefix
				for(int i = 0; i < ANSWER_SIZE; i++)
					buffer[i + 1] = (byte) (answer[i] + ByteProtocol.END_GAME_ANSWER_PREFIX);
				
				// Send buffer content to the client
				ByteComm.send(this.socket, this.buffer);
				
				break;

			// Validate a guess and send the clues
			case ByteProtocol.VALIDATE_HEADER:
				
				// Check for lost game and out-of-guesses
				if(!lostGame && this.guessCount < MAX_GUESSES) {
					int[] guesses = new int[ANSWER_SIZE];
					
					// Generate guesses array; decode from buffer
					for(int i = 0; i < ANSWER_SIZE; i++)
						guesses[i] = (buffer[i + 1] - ByteProtocol.VALIDATE_GUESS_PREFIX);
					
					// Generate the clues
					int[] clues = this.generateClue(guesses, this.answer);
					
					// Increment guess counter
					this.guessCount++;
					
					///
					// Send the clues back to the client
					///
					
					// Create a new buffer array 
					this.buffer = new byte[bufferSize];
					
					// Set the message header
					this.buffer[0] = ByteProtocol.VALIDATE_HEADER;
					
					// Add clues into buffer; encode with prefix
					for(int i = 0; i < ANSWER_SIZE; i++)
						buffer[i + 1] = (byte) (clues[i] + ByteProtocol.VALIDATE_CLUE_PREFIX);
					
					// Send buffer to the client
					ByteComm.send(this.socket, this.buffer);
				}
				
				break;
		}
	}
	
}
