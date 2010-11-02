/**
 * 
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

import com.mastermind.util.Colour;
import com.mastermind.util.GameConstants;
import com.mastermind.util.net.ByteComm;
import com.mastermind.util.net.ByteProtocol;

/**
 * The <code>GameLogic</code> class represents the logical backend properties of a <strong>Mastermind</strong> game.
 * 
 * It contains the necessary behaviour required to interact and play a game of <strong>Mastermind</strong> with a client session.
 * 
 * @author Alan Ly
 * @version 1.8
 */
public class GameLogic {
	
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
		
		// Initialise the connection
		this.socket = clientSocket;
		this.buffer = buffer;
		this.clientConnected = true;
		this.bufferSize = buffer.length;
	}
	
	/**
	 * Sets the appropriate state for the start of a game or for a new game.
	 */
	private void initialiseGame(int[] answer) {
		this.answer = answer;
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
		// Create clues array to store clues
		int[] clues = new int[answer.length];
		
		// Loop through answers
		for(int i = 0; i < answer.length; i++) {
			int indirectMatch = -1;
			
			// Loop through guesses
			for(int j = 0; j < guesses.length; j++)
				// Check if answer at 'i' is equivalent to guess at 'j'
				if(answer[i] == guesses[j])
					// Check if 'i' and 'j' are equivalent or answer and guess at 'j' are equivalent
					if(i == j || answer[j] == guesses[j]) {
						// Direct match is found; add into clues at 'i'
						clues[i] = 2;
						
						// Set no indirect match
						indirectMatch = -1;
						
						// Disable the guess value so it doesn't get iterated through again
						guesses[j] = -1;
						
						break;
					} else
						// Set indirect match found at 'j'
						indirectMatch = j;
			
			// Check if indirect match was found
			if(indirectMatch != -1) {
				// Indirect match found; add into clues at 'i'
				clues[i] = 1;
				
				// Disable the guess value so it doesn't get iterated through again
				guesses[indirectMatch] = -1;
			}
		}
		
		// Sort clues so that they're not representative of the answer
		Arrays.sort(clues);

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
		
		// Iterate through 'colours' and populate array with randomised values
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
				// Check if client wants a predefined answer or not
				if(this.buffer[1] > 0x10) {
					// Initialise an answer array to contain the predefined answers
					int[] answer = new int[GameConstants.ANSWER_LENGTH];
					
					// Fetch the answer from the buffer and place it into the answer array
					for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
						answer[i] = (this.buffer[i + 1] - ByteProtocol.START_GAME_REQUEST_ANSWER_PREFIX);
					
					// Initialise the game with the predefined answer
					this.initialiseGame(answer);
				} else
					// Initialise the game with a generated answer
					this.initialiseGame(this.generateAnswer(GameConstants.ANSWER_LENGTH));
				
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
				for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
					buffer[i + 1] = (byte) (answer[i] + ByteProtocol.END_GAME_ANSWER_PREFIX);
				
				// Send buffer content to the client
				ByteComm.send(this.socket, this.buffer);
				
				break;

			// Validate a guess and send the clues
			case ByteProtocol.VALIDATE_HEADER:
				
				// Check for lost game and out-of-guesses
				if(!lostGame && this.guessCount < GameConstants.MAX_NUM_OF_GUESSES) {
					int[] guesses = new int[GameConstants.ANSWER_LENGTH];
					
					// Generate guesses array; decode from buffer
					for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
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
					for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
						buffer[i + 1] = (byte) (clues[i] + ByteProtocol.VALIDATE_CLUE_PREFIX);
					
					// Send buffer to the client
					ByteComm.send(this.socket, this.buffer);
				}
				
				break;
		}
	}
	
}
