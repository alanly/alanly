package com.mastermind.client.ui;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.mastermind.client.logic.GameController;

public class cluePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout = null;
	JLabel[][][] clue = new JLabel[20][2][2];
	private GameController controller;
	public cluePanel() {
		super();
		initialize();
	}

	private void initialize() {
		gridBagLayout = new GridBagLayout();
		// Set Layout
		this.setLayout(gridBagLayout);
		createAndPlaceLabels();
		setBackground(Color.decode("#939393"));

	}

	private void createAndPlaceLabels() {
		//int [] clues =controller.getClues();
		//for(int b = 0 ; b < clues.length;b++){
	//System.out.println(clues[b]);
//}
		Border thickBorder = new LineBorder(Color.decode("#484444"), 0, true);

		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 2; j++) 
				for(int k =0; k<2;k++)
			{
				clue[i][j][k] = new JLabel(new ImageIcon("src/images/clue.gif"));
				
				clue[i][j][k].setBorder(thickBorder);
				add(clue[i][j][k], makeConstraints(j, i, 1, 1));
				clue[i][j][k].setVisible(true);
			}
	}

	private GridBagConstraints makeConstraints(int gridx, int gridy,
			int gridwidth, int gridheight) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = gridx; // Column
		gbc.gridy = gridy; // Row
		gbc.gridheight = gridheight;
		gbc.gridwidth = gridwidth;
		gbc.anchor = GridBagConstraints.CENTER ;

		return gbc;
	}

}
