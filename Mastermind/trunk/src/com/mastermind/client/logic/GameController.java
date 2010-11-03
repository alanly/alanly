/**
 * 
 */
package com.mastermind.client.logic;

import java.net.SocketException;

import com.mastermind.client.ui.Mastermind;

/**
 * @author FLIP
 *
 */
public class GameController {
	private int guessCount;
	private GameBoard gameBoard;
	private Mastermind view;
	
	public GameController(GameBoard gameBoard, Mastermind view)
	{
		guessCount = 0;
		this.gameBoard = gameBoard;
		this.view = view;			
	}
	
	public void newGame() throws SocketException
	{
		gameBoard.startGame(null);
	}
	
	public void endGame() throws SocketException
	{
		gameBoard.endGame();
	}
	
	public void check()
	{
		gameBoard.validateGuess();
	}
	

}
