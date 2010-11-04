/**
 * 
 */
package com.mastermind.client.logic;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import com.mastermind.util.GameConstants;
import com.mastermind.util.net.ByteComm;
import com.mastermind.util.net.ByteProtocol;

/**
 * The <strong>GameBoard</strong> class represents the basic client-side logic for a <strong>Mastermind</strong> game.
 * 
 * @author Alan Ly
 * @version 1.2
 */
public class ClientGameLogic {

	// Global variables related to connection
	private Socket socket;
	private byte[] buffer;
	
	// Global variables related to game
	private boolean lostGame;
	private int guessCount;
	
	/**
	 * Initialises a <strong>GameBoard</strong> class with a specified <em>Mastermind Server</em> socket.
	 * 
	 * @param socket the socket to the server that is provided by the calling method
	 * @throws IOException thrown if an error occurs related to the creation of the socket
	 */
	public ClientGameLogic(Socket socket) throws IOException {
		super();
		
		// Initialise the connection
		this.socket = socket;
		this.buffer = new byte[GameConstants.BUFFER_LENGTH];
		
		// Initialise the game
		this.initialiseGame();
	}
	
	/**
	 * Initialises the class to a clean state for a new game.
	 */
	private void initialiseGame() {
		this.lostGame = false;
		this.guessCount = 0;
	}
	
	/**
	 * Gets a boolean that represents whether or not the player has lost the game.
	 * 
	 * If the player loses, the value is set to true, else it is set to false under normal circumstances.
	 * 
	 * @return a boolean to determine the lost of the game
	 */
	public boolean hasLostGame() {
		return this.lostGame;
	}
	
	/**
	 * Gets the number of guesses that the player has already tried.
	 * 
	 * @return the number of guesses the player has tried
	 */
	public int getGuessCount() {
		return this.guessCount;
	}
	
	/**
	 * Starts a new game with a predefined answer (represented by an Integer array of colours). If a randomly generated is desired, then just pass through a <strong>null</strong> object.
	 * 
	 * @param answer the predefined answer if desired in an Integer array; <strong>null</strong> if a randomly generated answer is desired
	 * @return a boolean representing a successful game start; true if game started successfully and false if game failed to start
	 * @throws SocketException thrown is an error occurred during communication with the server
	 */
	public boolean startGame(int[] answer) throws SocketException {		
		// Set start game header byte
		this.buffer[0] = ByteProtocol.START_GAME_HEADER;
		
		// Determine if a predetermined answer is wanted
		if(answer == null)
			// Create the buffer with the start game request value
			for(int i = 1; i < this.buffer.length; i++)
				buffer[i] = ByteProtocol.START_GAME_REQUEST;
		else
			// Create the buffer with the start game request with a predefined answer
			for(int i = 1; i < this.buffer.length; i++)
				this.buffer[i] = (byte) (answer[i - 1] + ByteProtocol.START_GAME_REQUEST_ANSWER_PREFIX);
		
		// Send the buffer to the server
		ByteComm.send(this.socket, this.buffer);
		
		// Receive the reply from the server
		this.receiveMessage(this.socket, this.buffer, GameConstants.BUFFER_LENGTH);
		
		// Check if the reply is a valid start game message
		if(this.buffer[0] == ByteProtocol.START_GAME_HEADER)
			// If the game started successfully, return true
			if(this.buffer[1] == ByteProtocol.START_GAME_SUCCESS) {
				this.initialiseGame();
				return true;
			}
		
		return false;
	}
	
	/**
	 * Ends the current game and returns the answer to the game in an Integer array.
	 * 
	 * @return an Integer array containing the answers
	 * @throws SocketException thrown if an error occurs during communication with the server
	 */
	public int[] endGame() throws SocketException {
		// Create an answers array to store the result
		int[] answers = new int[GameConstants.ANSWER_LENGTH];
		
		// Set the end game header byte for the buffer
		this.buffer[0] = ByteProtocol.END_GAME_HEADER;
		
		// Set the end game request for the buffer
		for(int i = 1; i < this.buffer.length; i++)
			this.buffer[i] = ByteProtocol.END_GAME_REQUEST;
		
		// Send the request to the server
		ByteComm.send(this.socket, this.buffer);
		
		// Receive the result from the server
		this.receiveMessage(this.socket, this.buffer, GameConstants.BUFFER_LENGTH);		
		
		// Check if the reply is a valid end game message
		if(this.buffer[0] == ByteProtocol.END_GAME_HEADER) {
			// Get the answer encoded within the buffer
			for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
				// Get the answer value by removing the prefix.
				answers[i] = (this.buffer[i + 1] - ByteProtocol.END_GAME_ANSWER_PREFIX);
			
			// Set lost game state to true;
			this.lostGame = true;
		}
		
		return answers;
	}
	
	/**
	 * Validates a set of <code>guesses</code> and returns the appropriate clues as an Integer array. If the user has lost the game, this method will return <strong>null</strong>.
	 * 
	 * @param guesses the player's guesses in an Integer array
	 * @return the associated clues in an Integer array
	 * @throws SocketException thrown if an error is encountered during communication with the server
	 */
	public int[] validateGuess(int[] guesses) throws SocketException {		
		if(this.lostGame)
			return null;
		
		// Create an array to store the clues
		int[] clues = new int[GameConstants.ANSWER_LENGTH];
		
		// Set the validate header byte for the buffer
		this.buffer[0] = ByteProtocol.VALIDATE_HEADER;
		
		// Set the validate guess request encoded with the guess values
		for(int i = 1; i < this.buffer.length; i++)
			// Determine the encoded value by adding the prefix value onto each guess
			this.buffer[i] = (byte) (guesses[i - 1] + ByteProtocol.VALIDATE_GUESS_PREFIX);
		
		// Send the buffer data to the server
		ByteComm.send(this.socket, this.buffer);
		
		// Receive the resulting clues from the server
		this.receiveMessage(this.socket, this.buffer, GameConstants.BUFFER_LENGTH);
		
		// Determine if the message is a valid validate message with the header
		if(this.buffer[0] == ByteProtocol.VALIDATE_HEADER) {
			// Get the clues from the buffer
			for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
				// Decode the clues by removing the clue prefix
				clues[i] = (this.buffer[i + 1] - ByteProtocol.VALIDATE_CLUE_PREFIX);
			
			// Increment the guess count
			this.guessCount++;
			
			if(this.guessCount >= GameConstants.MAX_NUM_OF_GUESSES)
				this.lostGame = true;
		}
		
		return clues;
	}
	
	/**
	 * Receives a byte array of length <code>messageLength</code> from the server at <code>socket</code> and places the message into <code>buffer</code>.
	 * 
	 * @param socket the socket connection to receive from
	 * @param buffer the buffer to output messages to
	 * @param messageLength the appropriate length of the message to retrieve
	 * @throws SocketException thrown if and error is encountered during communication
	 */
	private void receiveMessage(Socket socket, byte[] buffer, int messageLength) throws SocketException {
		for(int receiveSize = 0; receiveSize < messageLength; receiveSize = ByteComm.receiveByte(socket, buffer)) {
			if(receiveSize == -1)
				throw new SocketException("Socket disconnected while receiving.");
		}
	}
	
}
