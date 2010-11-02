package com.mastermind.client.ui;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class computerGuessPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout = null;
	JLabel[] computerGuess = new JLabel[4];

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
			computerGuess[j] = new JLabel(new ImageIcon("C:\\QM.gif"));

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

}
