package com.mastermind.client.ui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;

import com.mastermind.client.logic.ClientGameLogic;
import com.mastermind.client.logic.GameController;



public class Mastermind extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel northPanel = new JPanel(new BorderLayout());
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel southPanel = new JPanel(new BorderLayout());
	JPanel Main = new JPanel(new BorderLayout());

	private availableColorsPanel colorPanel =null;
	private computerGuessPanel computerGuess = null;
	private cluePanel cluePanel = null ;
	private boardGamePanel boardGame = null ;
	private buttonPanel buttonPanel = null;
	private titlePanel titlePanel = null;
	ClientGameLogic game = null;
	GameController controller = null;
	
	private ColorModel cursorColor = null;

	public Mastermind() throws IOException  {
		
		super();
		int port;
		String ip;
		
		port = Integer.parseInt(JOptionPane.showInputDialog("Enter port number to connect to :"));
		ip = JOptionPane.showInputDialog("Enter a connection IP address: " );
		cursorColor = new ColorModel();
		
		colorPanel = new availableColorsPanel(cursorColor);
		computerGuess = new computerGuessPanel();
		cluePanel = new cluePanel();
		boardGame = new boardGamePanel(cursorColor);
		buttonPanel = new buttonPanel();
		titlePanel = new titlePanel();
		Socket socket = new Socket(ip, port);
		game = new ClientGameLogic(socket);
		controller = new GameController(game,this,boardGame,colorPanel,buttonPanel,cluePanel,computerGuess);
		
		initialize();
		
		cursorColor.addObserver(boardGame);
		setSize(350,605);
		setTitle("Mastermind");
		//pack();
		this.setVisible(true);
		
	}
	private void initialize() {
		
		northPanel.add(computerGuess, BorderLayout.CENTER);
		Main.add(northPanel, BorderLayout.NORTH);
		
		southPanel.add(buttonPanel,BorderLayout.CENTER);
		Main.add(southPanel,BorderLayout.SOUTH);
		// Prepare and add the boardGame panel
		centerPanel.add(boardGame, BorderLayout.CENTER);
		centerPanel.add(cluePanel, BorderLayout.EAST);
		centerPanel.add(colorPanel, BorderLayout.WEST);
		Main.add(centerPanel,BorderLayout.CENTER);
		
		//southPanel.add(colorPanel, BorderLayout.CENTER);
		//Main.add(southPanel,BorderLayout.SOUTH);
		this.add(Main,BorderLayout.CENTER);
		this.add(titlePanel,BorderLayout.EAST);
	
		// JFrame method for adding a menu bar to a frame
		setJMenuBar(createMenuBar());
		pack();
		setVisible(true);
	}

	/**
	 * This method constructs the menu bar that can then be added to the frame
	 * 
	 * @return the constructed menubar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		// Build the first menu.
		// This is the menu object in the menu bar
		menu = new JMenu("File");
		// This allows the user to access the menu without using the mouse
		menu.setMnemonic(KeyEvent.VK_F); // Alt + F
		// If the user has accessibility turned on this would be spoken
		menu.getAccessibleContext().setAccessibleDescription("The file menu");
		// Display a tool tip if the mouse hovers over this menu
		menu.setToolTipText("The file menu");
		// Add this to the menu bar
		menuBar.add(menu);

		// Build the menu items for the first menu
		// Create the first menu item and its mnemonic (Alt + character)
		// A mnemonic allows the user to access this item without using the
		// mouse
		menuItem = new JMenuItem("New", KeyEvent.VK_N);
		// An accelerator is an Alt + character keyboard shortcut to a menu item
		// without going through the menu
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		// If the user has accessibility turned on this would be spoken
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This is the New menu item");
		// Display a tool tip if the mouse hovers over this menu
		menuItem.setToolTipText("This is the New menu item");
		// Add the action listener
		//menuItem.addActionListener(this);
		// Add this item to the menu
		menu.add(menuItem);

		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This is the Exit menu item");
		menuItem.setToolTipText("This is the Exit menu item");
		//menuItem.addActionListener(this);
		menu.add(menuItem);

		// Build the second menu.
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menu.getAccessibleContext().setAccessibleDescription("The edit menu");
		menu.setToolTipText("The edit menu");
		menuBar.add(menu);

		menuItem = new JMenuItem("Cut");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This is the Cut menu item");
		menuItem.setToolTipText("This is the Cut menu item");
	//	menuItem.addActionListener(this);
		menu.add(menuItem);

		
		// Build the fourth menu.
		menu = new JMenu("Window");
		menu.setMnemonic(KeyEvent.VK_W);
		menu.getAccessibleContext().setAccessibleDescription("The window menu");
		menu.setToolTipText("The window menu");
		menuBar.add(menu);

		// Build the fifth menu.
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription("The help menu");
		menu.setToolTipText("The help menu");
		menuBar.add(menu);

		return menuBar;
	}

	/*
	 * Event handler for menu items other than Look and Feel Just display a
	 * dialog with the text of the menu item selected
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Menu event detected.\n" + "    Event source: "
				+ source.getText();
		JOptionPane.showMessageDialog(this, s);
		

	}

	/**
	 * Rather than have a single event handler for all menu items here is an
	 * inner class specific to the tool bar
	 * 
	 * @author neon
	 * 
	 */
	class ToolBarEventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) (e.getSource());
			String s = "ToolBar event detected.\n" + "    Action Command: "
					+ source.getActionCommand();
			JOptionPane.showMessageDialog(null, s);
		}
	}


	/**
	 * Rather than have a single event handler for all menu items here is an
	 * inner class specific to the Look and Feel menu
	 * 
	 * @author neon
	 * 
	 */
	class LookAndFeelMenuEventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem) (e.getSource());
			changeLookAndFeel(source.getText());
		}
	}

	/**
	 * Change the look and feel
	 * 
	 */
	private void changeLookAndFeel(String str) {
		if (str == "Metal") {
			try {
				UIManager
						.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"The Metal/Sun Look and Feel is not supported.",
						"Look & Feel Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (str == "Motif") {
			try {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"The Motif Look and Feel is not supported.",
						"Look & Feel Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (str == "Windows") {
			try {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"The Windows Look and Feel is not supported.",
						"Look & Feel Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (str == "Aqua") {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"The Mac Look and Feel is not supported.",
						"Look & Feel Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (str == "System") {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"The System Look and Feel is not supported.",
						"Look & Feel Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (str == "Cross Platform") {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"The Cross Platform Look and Feel is not supported.",
						"Look & Feel Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (str == "Windows Classic") {
			try {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"The Windows Classic Look and Feel is not supported.",
						"Look & Feel Error", JOptionPane.INFORMATION_MESSAGE);
			}

		}

		// Update all the GUI elements to the new look and feel
		SwingUtilities.updateComponentTreeUI(this);
	}

	

	}
	



