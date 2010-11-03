/**
 * 
 */
package com.mastermind.client;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.mastermind.util.GameConstants;
import com.mastermind.util.net.ByteComm;
import com.mastermind.util.net.ByteProtocol;

/**
 * @author Alan Ly
 *
 */
public class TextClientTest {
	
	private static Socket socket;
	private static Scanner consoleScan;
	private static int guessCount = 0;
	private static boolean gameEnd = false;
	private static boolean wonGame = false;
	private static byte[] buffer;
	private static int[] answers;
	private static int[] guesses;

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		System.out.println("----- M A S T E R M I N D -----");
		
		try {
			initialise();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static void initialise() throws UnknownHostException, IOException {
		consoleScan = new Scanner(System.in);
		String input;
		
		System.out.println("Please enter the required server details (default values are in parenthesis),");
		System.out.print("Server Address (127.0.0.1): ");
		input = consoleScan.nextLine();
		
		String address = (input.equals("") ? "127.0.0.1" : input);
		
		System.out.print("Server Port (50000): ");
		input = consoleScan.nextLine();
		
		int port = (input.equals("") ? 50000 : Integer.parseInt(input));
		
		socket = new Socket(address, port);
		
		System.out.println("Connected to Mastermind Server...\n");
		
		try {
			initialiseGame();
		} catch (IOException ioe) {
			throw new IOException(ioe.getMessage());
		} finally {
			socket.close();
		}
	}
	
	private static void initialiseGame() throws IOException {
		startNewGame(new int[] { 1, 2, 3, 4 });
		
		System.out.println("New game started,\n");
		
		System.out.println("You have " + GameConstants.MAX_NUM_OF_GUESSES + " guesses. The number of guesses made so far are shown in square brackets.\n" +
				"To play, enter " + GameConstants.ANSWER_LENGTH + " values between 1 and 8 inclusive (e.g. '1738') or 'QUIT' to end the game and see the answer or 'NEW' to start a new game.\n" +
				"Clues are given to help guide you; 0 represents no matches, 1 represents a colour match and 2 represents a complete match. Good Luck!\n");
		
		do{
			System.out.print("[" + guessCount + " out of " + GameConstants.MAX_NUM_OF_GUESSES + "] Enter a value: ");
			
			String input = consoleScan.nextLine();
			
			if(input.equalsIgnoreCase("QUIT")) {
				answers = quitGame();
				gameEnd = true;
			} else if(input.equalsIgnoreCase("NEW")) {
				if(startNewGame(null)) {
					gameEnd = false;
					wonGame = false;
					guessCount = 0;
					System.out.println("\nNew Game Started\n");
				}
			} else {
				if(input.length() == GameConstants.ANSWER_LENGTH) {
					guesses = new int[GameConstants.ANSWER_LENGTH];
					
					for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++) {
						guesses[i] = Integer.parseInt(input.charAt(i) + "");
					}
					
					int[] clues = validateGuesses(guesses);
					
					String cluesResult = "";
					
					for(int clue : clues)
						cluesResult += clue;
					
					if(cluesResult.equalsIgnoreCase("2222")) {
						wonGame = true;
						gameEnd = true;
					} else {
						System.out.println("\nThe clues are: " + cluesResult);
					}
					
					guessCount++;
				} else {
					System.out.println("You have to enter " + GameConstants.ANSWER_LENGTH + " values between 1 and 8 inclusive or 'QUIT'!");
				}
			}
			
		} while(!gameEnd && guessCount < GameConstants.MAX_NUM_OF_GUESSES);
		
		if(!wonGame) {
			System.out.print("\n\nThe answers were, ");
			
			for(int answer : answers)
				System.out.print(answer + " ");
			
			System.out.println("\nBetter luck next time! Bye!");
		} else {
			System.out.println("\nYOU HAVE WON!!! CONGRATUALATIONS!!!");
			System.out.println("The answers were, ");
			
			for(int guess : guesses)
				System.out.print(guess + " ");
		}
		
		socket.close();
	}
	
	private static boolean startNewGame(int[] answer) throws IOException {
		buffer = new byte[GameConstants.BUFFER_LENGTH];
		
		buffer[0] = ByteProtocol.START_GAME_HEADER;
		
		if(answer == null)
			for(int i = 1; i < buffer.length; i++)
				buffer[i] = ByteProtocol.START_GAME_REQUEST;
		else
			for(int i = 1; i < buffer.length; i++)
				buffer[i] = (byte) (answer[i - 1] + ByteProtocol.START_GAME_REQUEST_ANSWER_PREFIX);
		
		ByteComm.send(socket, buffer);
		
		receiveMessage(socket, buffer, GameConstants.BUFFER_LENGTH);
		
		if(buffer[0] == ByteProtocol.START_GAME_HEADER)
			if(buffer[1] == ByteProtocol.START_GAME_SUCCESS)
				return true;
		
		return false;
	}
	
	private static int[] quitGame() throws IOException {
		int[] answers = new int[GameConstants.ANSWER_LENGTH];
		buffer = new byte[GameConstants.BUFFER_LENGTH];
		
		buffer[0] = ByteProtocol.END_GAME_HEADER;
		
		for(int i = 1; i < buffer.length; i++)
			buffer[i] = ByteProtocol.END_GAME_REQUEST;
		
		ByteComm.send(socket, buffer);
		
		receiveMessage(socket, buffer, GameConstants.BUFFER_LENGTH);		
		
		if(buffer[0] == ByteProtocol.END_GAME_HEADER)
			for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
				answers[i] = (buffer[i + 1] - ByteProtocol.END_GAME_ANSWER_PREFIX);
		
		return answers;
	}
	
	private static int[] validateGuesses(int[] guesses) throws IOException {
		int[] clues = new int[GameConstants.ANSWER_LENGTH];
		buffer = new byte[GameConstants.BUFFER_LENGTH];
		
		buffer[0] = ByteProtocol.VALIDATE_HEADER;
		
		for(int i = 1; i < buffer.length; i++)
			buffer[i] = (byte) (guesses[i - 1] + ByteProtocol.VALIDATE_GUESS_PREFIX);
		
		ByteComm.send(socket, buffer);
		
		receiveMessage(socket, buffer, GameConstants.BUFFER_LENGTH);
		
		if(buffer[0] == ByteProtocol.VALIDATE_HEADER)
			for(int i = 0; i < GameConstants.ANSWER_LENGTH; i++)
				clues[i] = (buffer[i + 1] - ByteProtocol.VALIDATE_CLUE_PREFIX);
		
		return clues;
	}
	
	private static void receiveMessage(Socket sock, byte[] buff, int len) throws IOException {
		for(int receiveSize = 0; receiveSize < len; receiveSize = ByteComm.receive(sock, buff)) {
			if(receiveSize == -1)
				throw new SocketException("Socket disconnected while receiving.");
		}
	}

}
