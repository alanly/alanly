package com.mastermind.client.ui;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class boardGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout = null;
	JButton[][] boardGame = new JButton[4][10];
	availableColorsPanel color = new availableColorsPanel();
	// int boardcolor = MyActionListener.ChosenColor;
	int boardcolor = color.ChosenColor;

	public boardGamePanel() {
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

		MyActionListener myActionListener = new MyActionListener();
		Border thickBorder = new LineBorder(Color.decode("#484444"), 0, true);

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 10; j++) {
				boardGame[i][j] = new JButton(new ImageIcon("C:\\board.gif"));
				boardGame[i][j].setBorder(thickBorder);
				add(boardGame[i][j], makeConstraints(i, j, 1, 1));
				boardGame[i][j].setVisible(true);
				boardGame[i][j].addActionListener(myActionListener);
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

	class MyActionListener implements ActionListener {

		public static final int ChosenColor = 0;

		/**
		 * Send the button text to the input handler
		 * 
		 * @param e
		 *            the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 10; j++) {

					if (o == boardGame[i][j]) {
						boardGame[i][j] = new JButton(new ImageIcon("C:\\"
								+ boardcolor + ".gif"));

					}
				}
			}
		}

	}
}
