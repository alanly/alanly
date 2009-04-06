package binaryconverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BinaryConverterWindow extends JFrame {
	// Declare instance variables and window objects...
	private JPanel southPanel;
	private JPanel ioPanel;
	private JPanel dataTypeSelectPanel;
	private JPanel buttonPanel;
	private JMenuBar menuBar;
	private JRadioButton typeBinary;
	private JRadioButton typeAscii;
	private JTextArea outputArea;
	private JTextArea inputArea;
	
	
	public BinaryConverterWindow() {
		// Set window properties...
		setTitle("Binary Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		
		// Build window panel and add to window...
		buildMenuBar();
		buildIOPanel();
		add(ioPanel, BorderLayout.CENTER);
		buildSouthPanel();
		add(southPanel, BorderLayout.SOUTH);
		
		// Pack window and display...
		pack();
		setVisible(true);
	}
	
	private void buildMenuBar() {
		// Declare menu items...
		JMenuItem aboutItem = new JMenuItem("About");
		JMenuItem exitItem = new JMenuItem("Exit");
		aboutItem.setMnemonic(KeyEvent.VK_A);
		exitItem.setMnemonic(KeyEvent.VK_X);
		aboutItem.addActionListener(new AboutListener());
		exitItem.addActionListener(new ExitListener());
		
		// Create menu...
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(aboutItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		// Create menu bar....
		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}
	
	private void buildSouthPanel() {
		// Create new panel object and set properties...
		southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		southPanel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
		
		// Build sub-panels and add...
		buildDataTypeSelectPanel();
		southPanel.add(dataTypeSelectPanel, BorderLayout.WEST);
		buildButtonPanel();
		southPanel.add(buttonPanel, BorderLayout.EAST);
	}
	
	private void buildIOPanel() {
		// Create new panel object and set properties...
		ioPanel = new JPanel();
		ioPanel.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
		ioPanel.setLayout(new FlowLayout());
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());
		
		// Set text area items and properties...
		inputArea = new JTextArea(15, 55);
		inputArea.setText("This is the input area for data which you want converted.");
		inputArea.setLineWrap(true);
		inputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		outputArea = new JTextArea(15, 55);
		outputArea.setText("This is the output area for data which has been converted.");
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);
		outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		JScrollPane inputAreaScroll = new JScrollPane(inputArea);
		inputAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane outputAreaScroll = new JScrollPane(outputArea);
		outputAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel inputLabel = new JLabel("Text to convert");
		JLabel outputLabel = new JLabel("Result of conversion");
		
		// Add items to panel...
		inputPanel.add(inputLabel, BorderLayout.NORTH);
		inputPanel.add(inputAreaScroll, BorderLayout.CENTER);
		outputPanel.add(outputLabel, BorderLayout.NORTH);
		outputPanel.add(outputAreaScroll, BorderLayout.CENTER);
		ioPanel.add(inputPanel);
		ioPanel.add(outputPanel);
	}
	
	private void buildDataTypeSelectPanel() {
		// Create new panel object and set properties...
		dataTypeSelectPanel = new JPanel();
		dataTypeSelectPanel.setBorder(BorderFactory.createTitledBorder("Input Data Type"));
		dataTypeSelectPanel.setLayout(new FlowLayout());
		
		// Set button items and properties...
		typeBinary = new JRadioButton("Binary");
		typeAscii = new JRadioButton("ASCII", true);
		
		ButtonGroup dataType = new ButtonGroup();
		dataType.add(typeBinary);
		dataType.add(typeAscii);
		
		// Add items to panel...
		dataTypeSelectPanel.add(typeAscii);
		dataTypeSelectPanel.add(typeBinary);
	}
	
	private void buildButtonPanel() {
		// Create new panel object and set properties...
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		// Set button items and properties...
		JButton convertButton = new JButton("Convert");
		convertButton.addActionListener(new ConvertListener());
		
		// Add items to panel...
		buttonPanel.add(convertButton, SwingConstants.CENTER);
	}
	
	private class ConvertListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (typeBinary.isSelected())
				outputArea.setText(convertBinary(inputArea.getText()));
			else
				outputArea.setText(convertAscii(inputArea.getText()));				
		}
	}
	
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class AboutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "(Binary Converter)\nThis program was written by Alan Ly");
		}
	}
	
	private String convertBinary(String binaryInput) {
		// Declare and initialise variables...
		String[] binaryArray = new String[binaryInput.length() / 8];
		String[] resultArray;
		String convertedString = "";
		
		// Separate input string into eight character blocks and input into new array...
		for (int arrayIndex = 0, subStart = 0, subEnd = 8; arrayIndex < binaryArray.length && subEnd <= binaryInput.length(); arrayIndex++, subStart += 8, subEnd += 8)
			binaryArray[arrayIndex] = new String(binaryInput.substring(subStart, subEnd));
		
		// Create new object with array...
		EncodingConvert convertBin = new EncodingConvert(binaryArray, "asciiBin");
		resultArray = convertBin.convertToAsciiChar();
		
		// Concatonate resulting array into a single String variable...
		for (int arrayIndex = 0; arrayIndex < resultArray.length; arrayIndex++)
			convertedString += resultArray[arrayIndex];
		
		return convertedString;
	}
	
	private String convertAscii(String asciiInput) {
		// Declare and initialise variables...
		String[] asciiArray = new String[asciiInput.length()];
		String[] resultArray;
		String convertedString = "";
		
		// Separate input string into individual character blocks and input into new array...
		for (int arrayIndex = 0, subStart = 0, subEnd = 1; arrayIndex < asciiArray.length && subEnd <= asciiInput.length(); arrayIndex++, subStart++, subEnd++)
			asciiArray[arrayIndex] = new String(asciiInput.substring(subStart, subEnd));
		
		// Create new object with array...
		EncodingConvert convertAscii = new EncodingConvert(asciiArray, "asciiChar");
		resultArray = convertAscii.convertToAsciiBin();
		
		// Concatonate resulting array into a single String variable...
		for (int arrayIndex = 0; arrayIndex < resultArray.length; arrayIndex++)
			convertedString += resultArray[arrayIndex];
		
		return convertedString;
	}
}