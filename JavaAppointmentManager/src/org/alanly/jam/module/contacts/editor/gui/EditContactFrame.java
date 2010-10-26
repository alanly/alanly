/**
 * 
 */
package org.alanly.jam.module.contacts.editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.KeyboardFocusManager;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * @author Alan Ly
 *
 */
public class EditContactFrame extends JFrame {
	
    private static final long serialVersionUID = 8579889651972518017L;

	private static final String FRAME_TITLE = "Edit Contact";
	
	private FieldsPanel fieldsPanel;

	public EditContactFrame() {
		super(FRAME_TITLE);
		
		this.setLayout(new BorderLayout());
		
		fieldsPanel = new FieldsPanel();
		fieldsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createEtchedBorder()));
		
		initializeFrame();
	}
	
	private void initializeFrame() {
		add(fieldsPanel, BorderLayout.CENTER);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
            public void run() {
	            EditContactFrame ecf = new EditContactFrame();
	            ecf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            ecf.setVisible(true);
            }
			
		});
	}
	
}
