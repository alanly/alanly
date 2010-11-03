/**
 * 
 */
package com.mastermind.client.logic;

import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import com.mastermind.client.ui.Mastermind;
import com.mastermind.client.ui.availableColorsPanel;
import com.mastermind.client.ui.boardGamePanel;
import com.mastermind.client.ui.buttonPanel;
import com.mastermind.client.ui.cluePanel;
import com.mastermind.client.ui.computerGuessPanel;

/**
 * @author FLIP
 *
 */
public class GameController extends Observable{
	private int guessCount;
	private GameBoard gameBoard;
	private Mastermind view;
	private boardGamePanel boardGame;
	private availableColorsPanel availColors;
	private buttonPanel buttonpanel;
	private cluePanel cluepanel;
	private computerGuessPanel compGuess;
	private int [] answers;
	private int [] clues;
	
	
	public GameController(GameBoard gameBoard, Mastermind view,boardGamePanel boardGame,availableColorsPanel availColors,buttonPanel buttonpanel,cluePanel cluepanel,computerGuessPanel compGuess)
	{
		guessCount = 9;
		int [] answers = null;
		int [] clues = null;
		this.gameBoard = gameBoard;
		this.view = view;	
		this.availColors = availColors;
		this.buttonpanel = buttonpanel;
		this.cluepanel = cluepanel;
		this.compGuess = compGuess;
	}
	
	
	
	public void newGame() throws SocketException
	{		
		gameBoard.startGame(null);
		setChanged();
		notifyObservers();
	}
	
	public void endGame() throws SocketException
	{
		answers = gameBoard.endGame();
		compGuess.setAnswers(answers);
		compGuess.displayAnswers();
	}
	
	public void check()
	{
		//guesses = boardGame.getGuesses();
		clues = gameBoard.validateGuess(guesses);
		setChanged();
		notifyObservers();
		guessCount--;
	}
	
	public int getGuessCount()
	{
		return guessCount;
	}

	public int[] getAnswers() {
		
		return answers;
	}

	

}
