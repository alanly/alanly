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
public class GameController implements Observer{
	private int guessCount;
	private GameBoard gameBoard;
	private Mastermind view;
	private boardGamePanel boardGame;
	private availableColorsPanel availColors;
	private buttonPanel buttonpanel;
	private cluePanel cluepanel;
	private computerGuessPanel compGuess;
	
	
	public GameController(GameBoard gameBoard, Mastermind view,boardGamePanel boardGame,availableColorsPanel availColors,buttonPanel buttonpanel,cluePanel cluepanel,computerGuessPanel compGuess)
	{
		guessCount = 0;
		this.gameBoard = gameBoard;
		this.view = view;	
		this.availColors = availColors;
		this.buttonpanel = buttonpanel;
		this.cluepanel = cluepanel;
		this.compGuess = compGuess;
	}
	
	public void newGame() throws SocketException
	{		
		if()
		gameBoard.startGame(null);
	}
	
	public void endGame() throws SocketException
	{
		gameBoard.endGame();
	}
	
	public void check()
	{
		//gameBoard.validateGuess();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}
	

}
