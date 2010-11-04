/**
 * 
 */
package com.mastermind.client.tui;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.mastermind.client.logic.ClientGameLogic;
import com.mastermind.util.GameConstants;

/**
 * @author Alan Ly
 *
 */
public class TextInterface {
	
	private ClientGameLogic gameBoard;
	private Scanner scanner;
	private Socket socket;
	private int[] answers;
	private int[] guesses;
	private boolean hasWon;
	private boolean showAnswer;
	
	public TextInterface() {
		super();
		
		this.gameBoard = null;
		this.scanner = new Scanner(System.in);
	}
	
	public void run() throws Exception {
		// Print the awesome looking header ASCII thing
		System.out.println("===========================================================");
		System.out.println("#   #   #    #### ##### ##### ####  #   # ##### #   # ####");
		System.out.println("## ##  # #  #       #   #     #   # ## ##   #   ##  # #   #");
		System.out.println("# # # #   #  ###    #   ####  ####  # # #   #   # # # #   #");
		System.out.println("#   # #####     #   #   #     #   # #   #   #   #  ## #   #");
		System.out.println("#   # #   # ####    #   ##### #   # #   # ##### #   # ####");
		System.out.println("==Mastermind===============================================");
		System.out.println();
		
		// Connect to the server
		System.out.println("Enter the server connection data (default values are in parenthesis),");	
		this.socket = this.createSocket("127.0.0.1", GameConstants.DEFAULT_PORT);
		System.out.println("\nConnecting to server...");
		
		// Initialise the game
		this.initialiseGame();
		
		// Start playing the game
		this.playGame();
	}
	
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
	
	private void initialiseGame() throws IOException {
		this.answers = null;
		this.guesses = null;
		this.hasWon = false;
		this.showAnswer = true;
		
		
		// Create the game board
		this.gameBoard = new ClientGameLogic(this.socket);
		this.gameBoard.startGame(null);
	}
	
	private void playGame() throws IOException {
		String input = "";
		
		System.out.println("Connected to the server.");
		this.printInstructions();
		
		while(true) {
			if(!this.gameBoard.hasLostGame())
				System.out.print("[" + (gameBoard.getGuessCount() + 1) + "/" + GameConstants.MAX_NUM_OF_GUESSES + "] Command: ");
			else
				System.out.print("\tCommand: ");
			
			input = scanner.nextLine();
			
			if(input.equalsIgnoreCase("NEW")) {
				// Create a new game
				this.gameBoard.startGame(null);
				this.initialiseGame();
				System.out.println();
			} else if(input.equalsIgnoreCase("QUIT")) {
				if(!this.gameBoard.hasLostGame()) {
					// End the current game
					this.answers = this.gameBoard.endGame();
				}
			} else if(input.equalsIgnoreCase("EXIT")) {
				// Exit the application
				System.out.println("\nGoodbye!");
				break;
			} else if(input.equalsIgnoreCase("HELP")) {
				// Print the instructions
				this.printInstructions();
			} else {
				if(!this.gameBoard.hasLostGame()) {
					// Process guesses
					if(input.length() == GameConstants.ANSWER_LENGTH) {
						this.guesses = new int[GameConstants.ANSWER_LENGTH];
						
						for(int i = 0; i < this.guesses.length; i++)
							this.guesses[i] = Integer.parseInt(input.charAt(i) + "");
						
						String clueString = "";
						
						// Get the clues
						int[] clues = this.gameBoard.validateGuess(this.guesses);
						
						for(int clue : clues)
							clueString += clue;
						
						if(clueString.equals("2222"))
							this.hasWon = true;
						else
							System.out.println("Clues: " + clueString + "\n");
					} else
						System.out.println("\nGuesses must be " + GameConstants.ANSWER_LENGTH + " characters long! Valid commands are NEW, QUIT, HELP and EXIT.\n");
				}
			}
			
			if(showAnswer && (this.gameBoard.hasLostGame() || this.gameBoard.getGuessCount() >= GameConstants.MAX_NUM_OF_GUESSES || this.hasWon)) {
				this.answers = this.gameBoard.endGame();
				
				if(this.hasWon)
					System.out.println("Congratualations!! You've guessed right!");
				else
					System.out.println("\n\"You're a born loooooseeerrr!\"\n");
				
				System.out.print("The answer was: ");
				
				for(int answer : this.answers)
					System.out.print(answer + " ");
				
				System.out.println("\nTo start a new game type NEW, to exit type EXIT, for help type HELP.\n");
				
				showAnswer = false;
			}
		}
		
		this.socket.close();
	}
	
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
