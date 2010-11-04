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
	ColorModel cursorColor = null;
	
	

	public availableColorsPanel(ColorModel cursorColor) {
		super();
		this.cursorColor = cursorColor;
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
			availableColors[i].setBackground(Color.decode("#484444"));
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
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Point hotSpot = new Point(0, 0);
			int num = -1;
			for (int i = 0; i < 8; i++) {
				if (o == availableColors[i]) {
					
					Image image = toolkit.getImage("src/images/click" + i + ".gif");
					
					Cursor c = toolkit.createCustomCursor(image, hotSpot,
							"Mastermind");
					availableColors[i].setBackground(Color.decode("#484444"));
					cursorColor.setCursorColor(c);
					cursorColor.setColorNum(i) ;
					num = cursorColor.getcolorNum();
					System.out.println(num);
					setCursor(c);

				}
			}
		}
	}	

}
