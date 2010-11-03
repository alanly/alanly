package com.mastermind.client.ui;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.mastermind.client.logic.GameController;
import com.mastermind.util.GameConstants;


public class computerGuessPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout = null;
	JLabel[] computerGuess = new JLabel[4];
	int[]displayAnswers = new int[GameConstants.ANSWER_LENGTH];
	

	public computerGuessPanel() {
		super();
		initialize();
	}

	private void initialize() {
		gridBagLayout = new GridBagLayout();
		// Set Layout
		this.setLayout(gridBagLayout);
		createAndPlaceLabels();
		setBackground(Color.decode("#484444"));

	}

	private void createAndPlaceLabels() {

		Border thickBorder = new LineBorder(Color.decode("#484444"), 0, true);

		for (int j = 0; j < 4; j++) {
			computerGuess[j] = new JLabel(new ImageIcon("src/images/QM.gif"));

			computerGuess[j].setBorder(thickBorder);
			add(computerGuess[j], makeConstraints(j, 1, 1, 1));

		}
	}

	private GridBagConstraints makeConstraints(int gridx, int gridy,
			int gridwidth, int gridheight) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = gridx; // Column
		gbc.gridy = gridy; // Row
		gbc.gridheight = gridheight;
		gbc.gridwidth = gridwidth;

		return gbc;
	}
	public void displayAnswers()
	{
		//change the labels here to display the answer
	}
	
	public void setAnswers(int[]answers)
	{
		for(int i = 0 ; i < computerGuess.length;i++)
		{
			displayAnswers[i] =  answers[i];
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
			
			
	}

}
