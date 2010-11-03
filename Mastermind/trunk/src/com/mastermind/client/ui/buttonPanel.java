package com.mastermind.client.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class buttonPanel extends JPanel {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	JButton check = new JButton("Check");
	JButton newGame = new JButton("New Game");
	JButton quit = new JButton("Quit");

	
	

	public buttonPanel() {
		super();
		initialize();
	}

	private void initialize() {
		setBackground(Color.decode("#484444"));
		add(check);
		add(newGame);
		add(quit);
	}
	
	
}
