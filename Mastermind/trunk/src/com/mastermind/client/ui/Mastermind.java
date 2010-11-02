package com.mastermind.client.ui;

import java.awt.*;

import javax.swing.*;


public class Mastermind extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel northPanel = new JPanel(new BorderLayout());
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel southPanel = new JPanel(new BorderLayout());
	
	JPanel Main = new JPanel(new BorderLayout());

	availableColorsPanel colorPanel =null;
	computerGuessPanel computerGuess = null;
	cluePanel cluePanel = null ;
	boardGamePanel boardGame = null ;
	buttonPanel buttonPanel = null;
	titlePanel titlePanel = null;
	
	

	public Mastermind() {
		
		super();
		colorPanel = new availableColorsPanel();
		computerGuess = new computerGuessPanel();
		cluePanel = new cluePanel();
		boardGame = new boardGamePanel();
		buttonPanel = new buttonPanel();
		titlePanel = new titlePanel();
		initialize();
		setTitle("Mastermind");
		pack();
		this.setVisible(true);
		
	}
	private void initialize() {
		
		northPanel.add(computerGuess, BorderLayout.CENTER);
		Main.add(northPanel, BorderLayout.NORTH);
		
		southPanel.add(buttonPanel,BorderLayout.CENTER);
		Main.add(southPanel,BorderLayout.SOUTH);
		// Prepare and add the boardGame panel
		centerPanel.add(boardGame, BorderLayout.CENTER);
		centerPanel.add(cluePanel, BorderLayout.EAST);
		centerPanel.add(colorPanel, BorderLayout.WEST);
		Main.add(centerPanel,BorderLayout.CENTER);
		
	//	southPanel.add(colorPanel, BorderLayout.CENTER);
		//Main.add(southPanel,BorderLayout.SOUTH);
		
		this.add(Main,BorderLayout.CENTER);
		this.add(titlePanel,BorderLayout.EAST);
	
		
	}

	
	public static  void main(String[] args) {

				// Create and set up the window.
				Mastermind frame = new Mastermind();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	
	

	}
	



