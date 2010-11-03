package com.mastermind.client.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class availableColorsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton[] availableColors = new JButton[8];

	int ChosenColor = -1;

	public availableColorsPanel() {
		super();
		initialize();
	}

	private void initialize() {
		setLayout(new GridLayout(8, 1));
		createAndPlaceButtons();
		setBackground(Color.decode("#484444"));
	}

	private void createAndPlaceButtons() {
		MyActionListener myActionListener = new MyActionListener();

		Border thickBorder = new LineBorder(Color.decode("#484444"), 5, true);

		
		for(int i =0; i < 8 ; i++)
		{
			
			availableColors[i] = new JButton(new ImageIcon("src/images/"+ i + ".gif"));
			availableColors[i].setBorder(thickBorder);
			availableColors[i].addActionListener(myActionListener);
			add(availableColors[i], makeConstraints(i, 1, 1, 1));
			
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

		/**
		 * Send the button text to the input handler
		 * 
		 * @param e
		 *            the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			for (int i = 0; i < 8; i++) {
				if (o == availableColors[i]) {
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image image = toolkit.getImage("src/images/click" + i + ".gif");
					Point hotSpot = new Point(0, 0);
					Cursor c = toolkit.createCustomCursor(image, hotSpot,
							"Mastermind");
					ChosenColor = i;
					setCursor(c);
				}
			}
		}
	}



	static Color choose(int l) {
		if (l == 0)
			return Color.red;
		if (l == 1)
			return Color.green;
		if (l == 2)
			return Color.blue;
		if (l == 3)
			return Color.yellow;
		if (l == 4)
			return Color.magenta;
		if (l == 5)
			return Color.cyan;
		if (l == 6)
			return Color.orange;
		else
			return Color.pink;

	}

}
