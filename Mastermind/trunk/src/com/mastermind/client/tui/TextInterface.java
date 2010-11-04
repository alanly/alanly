/**
 * Contains all the classes that are use to present a text-based interactive user interface.
 */
package com.mastermind.client.tui;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.mastermind.client.logic.ClientGameLogic;
import com.mastermind.util.GameConstants;

/**
 * The <strong>TextInterface</strong> represents an interactive console text-based user interface that will accept commands from the user and perform the appropriate processing in the background in order to interact with a <em>Mastermind Server</em> to play a game of </em>Mastermind</em>.
 * 
 * @author Pedram Balalzadeh, Phillipe Thibault, Alan Ly
 * @version 1.0
 */
public class TextInterface {
	
	// Global variables for connection and logic
	private ClientGameLogic gameLogic;
	private Scanner scanner;
	private Socket socket;
	
	// Global variables for game
	private int[] answers;
	private int[] guesses;
	private boolean hasWon;
	private boolean showAnswer;
	
	/**
	 * Initialises a <strong>TextInterface</strong> class.
	 */
	public TextInterface() {
		super();
		
		this.gameLogic = null;
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * Starts the <strong>TextInterface</strong> class and begins interacting with user.
	 * 
	 * @throws Exception thrown if any exception occurs during the handling of the game
	 */
	public void run() throws Exception {
		// Print the awesome looking header-ASCII-thing
		System.out.println("===========================================================");
		System.out.println("#   #  ###   #### ##### ##### ####  #   # ##### #   # ####");
		System.out.println("## ## #   # #       #   #     #   # ## ##   #   ##  # #   #");
		System.out.println("# # # #####  ###    #   ####  ####  # # #   #   # # # #   #");
		System.out.println("#   # #   #     #   #   #     #   # #   #   #   #  ## #   #");
		System.out.println("#   # #   # ####    #   ##### #   # #   # ##### #   # ####");
		System.out.println("==Mastermind===============================================");
		System.out.println();
		
		// Connect to the server
		System.out.println("Enter the server connection data (default values are in parenthesis),");	
		this.socket = this.createSocket("127.0.0.1", GameConstants.DEFAULT_PORT);
		System.out.println("\nConnecting to server...");
		
		// Initialise the game
		this.initialiseGame();
		
		// Create the game board
		this.gameLogic = new ClientGameLogic(this.socket);
		
		// Start the game with a random answer; specify the answer as an Integer array if pre-defined answer is required
		this.gameLogic.startGame(null);
		
		// Start playing the game
		this.playGame();
	}
	
	/**
	 * Creates a <strong>Socket</strong> by interacting with the user. If the user does not enter any values, the default values specified by <code>defaultServer</code> and <code>defaultPort</code> will be used.
	 * 
	 * @param defaultServer the default server address to connect to
	 * @param defaultPort the default server port to connect to
	 * @return a <strong>Socket</strong> object representing the new connection
	 * @throws IOException thrown if an error occurs while generating the <strong>Socket</strong>
	 * @see java.net.Socket
	 */
	private Socket createSocket(String defaultServer, int defaultPort) throws IOException {
		Socket sock = null;
		String input = "";
		String server = "";
		int port = 0;
		
		try {			
			// Get server address
			System.out.print("\tServer Address (" + defaultServer + "): ");
			input = scanner.nextLine();
			server = (input.length() == 0 ? defaultServer : input);
			
			// Get port number
			System.out.print("\tServer Port (" + defaultPort + "): ");
			input = scanner.nextLine();
			port = (input.length() == 0 ? defaultPort : Integer.parseInt(input));
			
			// Validate the port number
			if(port < 0 || port > 65535)
				throw new NumberFormatException();
			
			// Create the socket
			sock = new Socket(server, port);
		} catch(NumberFormatException nfe) {
			throw new NumberFormatException("The port entered is invalid!");
		} catch(IOException ioe) {
			throw new IOException("The socket could not be created!");
		}
		
		// Return the socket that has been created
		return sock;
	}
	
	/**
	 * Initialises the game with the default values required for a new game session.
	 */
	private void initialiseGame() {
		this.answers = null;
		this.guesses = null;
		this.hasWon = false;
		this.showAnswer = true;
	}
	
	/**
	 * Plays a game of <em>Mastermind</em> with the user. In addition to the guesses, it will accept various commands from the user such as 'NEW' (to start a new game), 'QUIT' (to end the current game and see the answer), 'HELP' (to display the help text) and 'EXIT' (to exit the client).
	 * 
	 * @throws IOException thrown if an error occurs while communicating with the server
	 */
	private void playGame() throws IOException {
		// Create String to hold user input values
		String input = "";
		
		// Display connected message and print the instructions for the game
		System.out.println("Connected to the server.");
		this.printInstructions();
		
		// Keep looping until user quits
		while(true) {
			// Check whether or not the user has lost the game to output the appropriate prompt
			if(!this.gameLogic.hasLostGame())
				System.out.print("[" + (gameLogic.getGuessCount() + 1) + "/" + GameConstants.MAX_NUM_OF_GUESSES + "] Command: ");
			else
				System.out.print("\tCommand: ");
			
			// Get the user input
			input = scanner.nextLine();
			
			// Determine whether the user's entered a command or a guess
			if(input.equalsIgnoreCase("NEW")) {
				
				// Create a new game
				this.gameLogic.startGame(null);
				this.initialiseGame();
				System.out.println();
				
			} else if(input.equalsIgnoreCase("QUIT")) {
				
				// End the current game if it is still in play
				if(!this.gameLogic.hasLostGame()) {
					// End the current game
					this.answers = this.gameLogic.endGame();
				}
				
			} else if(input.equalsIgnoreCase("EXIT")) {
				
				// Exit the application
				System.out.println("\nGoodbye!");
				break;
				
			} else if(input.equalsIgnoreCase("HELP")) {
				
				// Print the instructions
				this.printInstructions();
				
			} else {
				
				// Check if the user has already lost or not
				if(!this.gameLogic.hasLostGame()) {
					
					// Process guesses if valid, else print message
					if(input.length() == GameConstants.ANSWER_LENGTH) {
						// Create the guesses array
						this.guesses = new int[GameConstants.ANSWER_LENGTH];
						
						// Get the guesses from the user input
						for(int i = 0; i < this.guesses.length; i++)
							this.guesses[i] = Integer.parseInt(input.charAt(i) + "");
						
						// Create String to hold output clues
						String clueString = "";
						
						// Get the clues
						int[] clues = this.gameLogic.validateGuess(this.guesses);
						
						// Add clues into the String
						for(int clue : clues)
							clueString += clue;
						
						// Check if user has won or not
						if(clueString.equals("2222"))
							this.hasWon = true;
						else
							System.out.println("Clues: " + clueString + "\n");
						
					} else
						System.out.println("\nGuesses must be " + GameConstants.ANSWER_LENGTH + " characters long! Valid commands are NEW, QUIT, HELP and EXIT.\n");
				}
			}
			
			// Check if answer should be shown, and if so, is the game over or not
			if(showAnswer && (this.hasWon || this.gameLogic.hasLostGame() || this.gameLogic.getGuessCount() >= GameConstants.MAX_NUM_OF_GUESSES)) {
				// Get the answers
				this.answers = this.gameLogic.endGame();
				
				// Print the appropriate message if the user has won or if the user has lost
				if(this.hasWon)
					System.out.println("Congratualations!! You've guessed right!");
				else
					System.out.println("\n\"You're a born loooooseeerrr!\"\n");
				
				System.out.print("The answer was: ");
				
				// Print each answer into the console
				for(int answer : this.answers)
					System.out.print(answer + " ");
				
				System.out.println("\nTo start a new game type NEW, to exit type EXIT, for help type HELP.\n");
				
				// Prevent answer from being shown again, until a new game
				showAnswer = false;
			}
		}
		
		// Close the socket if the user's exiting
		this.socket.close();
	}
	
	/**
	 * Prints the instructions to the console with newline before and after the message.
	 */
	private void printInstructions() {
		System.out.println("\nINSTRUCTIONS");
		System.out.println("------------");
		System.out.println("You have " + GameConstants.MAX_NUM_OF_GUESSES + " guesses. The number of guesses made so far are shown in square brackets.\n" +
				"To play, enter " + GameConstants.ANSWER_LENGTH + " values between 1 and 8 inclusive (e.g. '1738') or 'QUIT' to end the game and see the answer or 'NEW' to start a new game.\n" +
				"Clues are given to help guide you; 0 represents no matches, 1 represents a colour match and 2 represents a complete match. Good Luck!\n\n" +
				"Valid commands are: NEW, QUIT, HELP, EXIT");
		System.out.println("------------\n");
	}
}
