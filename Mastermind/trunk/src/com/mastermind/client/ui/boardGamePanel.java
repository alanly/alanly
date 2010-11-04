package com.mastermind.client.ui;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.mastermind.client.logic.GameController;

public class boardGamePanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout = null;
	JButton [][] boardGame = new JButton[10][4];
	int c = -1;
	private ColorModel cursorColor = null;
	
//	GameController counter = new GameController();
	public boardGamePanel(ColorModel cursorColor) {
		super();
		this.cursorColor = cursorColor;
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

		 MyActionListener myActionListener = new  MyActionListener();
		Border thickBorder = new LineBorder(Color.decode("#484444"), 0, true);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 4; j++) {
				boardGame[i][j] = new JButton(new ImageIcon(
						"src/images/board.gif"));
				boardGame[i][j].setBackground(Color.decode("#939393"));
				boardGame[i][j].setBorder(thickBorder);
				boardGame[i][j].addActionListener(myActionListener);
				add(boardGame[i][j], makeConstraints(j, i, 1, 1));
				boardGame[i][j].setVisible(true);
				
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
			
			Object o =  e.getSource();
			int color = cursorColor.getcolorNum();
			int []  colorArray = new int[4];
			
				for (int j = 0; j < 4; j++) {

					if (boardGame[cursorColor.getCurrentRow()][j] == o) {
					
						switch(color){
						case 0:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(Color.decode("#fb3001"));
							break;
						case 1:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(Color.decode("#01fb36"));
							break;
						case 2:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(Color.decode("#01330fb"));
							break;
						case 3:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(Color.decode("#fbf801"));
							break;
						case 4:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(Color.decode("#a001fb"));
							break;
						case 5:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(	Color.decode("#01f2fb"));
							break;
						case 6:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(	Color.decode("#fba001"));
							break;
						case 7:
							boardGame[cursorColor.getCurrentRow()][j].setBackground(	Color.decode("#fc9ccf"));
							break;
							
						
						}	colorArray[j]=color;
						
					}
				}
				cursorColor.setArray(colorArray);
			
				
				}
		}

	

	public void update(Observable observable, Object object) {

		// Determine the source of the observer event
		if (observable instanceof ColorModel) {
			ColorModel observedData = (ColorModel) observable;
			setCursor(observedData.getCursorColor());
			
			
		}
	}
}
