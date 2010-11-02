package com.mastermind.client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class titlePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel[] title = new JLabel[10];
	String[] letters = {"M","A","S","T","E","R","M","I","N","D"};
	//JPanel letterPanel =new JPanel(new GridLayout(10, 1));

	public titlePanel() {
		super();
		initialize(); 
	}

	private void initialize() {
		setLayout(new GridLayout(10, 1));
		
		setBackground(Color.decode("#484444"));
		createAndPlaceLabels();
	}
	private void createAndPlaceLabels() {

		Border thickBorder = new LineBorder(Color.decode("#484444"), 0, true);

		for (int i = 0; i < 10; i++){
				title[i] = new JLabel(letters[i]);
			title[i].setBorder(thickBorder);
			title[i].setFont(new Font(letters[i],0,40));
			title[i].setForeground(Color.orange);
				add(title[i]);
				
				
			}
	}
}
