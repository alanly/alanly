package com.mastermind.client.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.mastermind.client.logic.GameController;

public class buttonPanel extends JPanel   {

	/**
	 * 
	 */
	private GameController controller;
	ColorModel model = new ColorModel();
	private static final long serialVersionUID = 1L;
	
	JButton check = new JButton("Check");
	JButton newGame = new JButton("New Game");
	JButton quit = new JButton("Quit");
	int row = 9;

	public buttonPanel() {
		super();
		initialize();
	}

	private void initialize() {
		setBackground(Color.decode("#484444"));
		MyActionListenerCheck alc = new MyActionListenerCheck();
	//	MyActionListenerNew aln = new MyActionListenerNew();
		//MyActionListenerQuit alq = new MyActionListenerQuit();
		check.addActionListener(alc);
	//	newGame.addActionListener(aln);
		//quit.addActionListener(alq);
		add(check);
		add(newGame);
		add(quit);
	}

	private class MyActionListenerCheck implements ActionListener{
		
	@Override
	public void actionPerformed(ActionEvent e) {	
	
		Object o =  e.getSource();
		if ( o == check){
		row--;
		model.setCurrentRow(row);
		int []colorArray =model.getArray();
	
	try {
		controller.check(colorArray);
	} catch (SocketException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	}
		
	}
	private class MyActionListenerNew implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				controller.newGame();
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		}
	private class MyActionListenerQuit implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				controller.endGame();
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		}
	
	
	
	}
	
}
