package com.mastermind.client.ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class buttonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton check = new JButton("Check");

	public buttonPanel() {
		super();
		initialize();
	}

	private void initialize() {
		setBackground(Color.decode("#484444"));
		add(check);
	}
}
