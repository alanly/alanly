/**
 * 
 */
package org.alanly.jam.module.contacts.editor.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Alan Ly
 *
 */
public class FieldsPanel extends JPanel {
	
    private static final long serialVersionUID = 7499081475398082479L;
    
    private static final String DEFAULT_LABEL_FONT_FACE = Font.SANS_SERIF;
    private static final int DEFAULT_LABEL_FONT_STYLE = Font.BOLD;
    private static final int DEFAULT_LABEL_FONT_SIZE = 12;
    
    private static final String DEFAULT_TEXTFIELD_FONT_FACE = Font.SANS_SERIF;
    private static final int DEFAULT_TEXTFIELD_FONT_STYLE = Font.PLAIN;
    private static final int DEFAULT_TEXTFIELD_FONT_SIZE = 12;
    
    private String[] fields = {
    		"First Name", "Middle Name", "Last Name", "Company Name", "Address (Line 1)", "Address (Line 2)", "City", "Province/State", "Country", "Postal/Zip Code", "Phone Number", "Mobile Number", "Fax Number", "Email Address"
    };
    
    private int[] fieldSizes = { 20, 20, 20, 20, 30, 30, 20, 20, 20, 10, 20, 20, 20, 20 };
    
    private JTextField[] textFields = null;

    public FieldsPanel() {
    	super(new GridBagLayout());
    	
    	createComponents();
    }
    
    private void createComponents() {
    	// Initialise textFields array
    	textFields = new JTextField[fields.length];
    	
    	// First Name
    	add(createLabel(fields[0]), createConstraints(0, 0, 1, 1, GridBagConstraints.EAST));
    	textFields[0] = createTextField(fieldSizes[0]);
    	add(textFields[0], createConstraints(1, 0, 1, 1, GridBagConstraints.WEST));
    	
    	// Middle Name
    	add(createLabel(fields[1]), createConstraints(0, 1, 1, 1, GridBagConstraints.EAST));
    	textFields[1] = createTextField(fieldSizes[1]);
    	add(textFields[1], createConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
    	
    	// Last Name
    	add(createLabel(fields[2]), createConstraints(0, 2, 1, 1, GridBagConstraints.EAST));
    	textFields[2] = createTextField(fieldSizes[2]);
    	add(textFields[2], createConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
    	
    	// Company Name
    	add(createLabel(fields[3]), createConstraints(0, 3, 1, 1, GridBagConstraints.EAST));
    	textFields[3] = createTextField(fieldSizes[3]);
    	add(textFields[3], createConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
    	
    	// Address - Line 1
    	add(createLabel(fields[4]), createConstraints(0, 4, 1, 1, GridBagConstraints.EAST));
    	textFields[4] = createTextField(fieldSizes[4]);
    	add(textFields[4], createConstraints(1, 4, 3, 1, GridBagConstraints.WEST));
    	
    	// Address - Line 2
    	add(createLabel(fields[5]), createConstraints(0, 5, 1, 1, GridBagConstraints.EAST));
    	textFields[5] = createTextField(fieldSizes[5]);
    	add(textFields[5], createConstraints(1, 5, 3, 1, GridBagConstraints.WEST));
    	
    	// City
    	add(createLabel(fields[6]), createConstraints(0, 6, 1, 1, GridBagConstraints.EAST));
    	textFields[6] = createTextField(fieldSizes[6]);
    	add(textFields[6], createConstraints(1, 6, 1, 1, GridBagConstraints.WEST));
    	
    	// Province/State
    	add(createLabel(fields[7]), createConstraints(2, 6, 1, 1, GridBagConstraints.EAST));
    	textFields[7] = createTextField(fieldSizes[7]);
    	add(textFields[7], createConstraints(3, 6, 1, 1, GridBagConstraints.WEST));
    	
    	// Country
    	add(createLabel(fields[8]), createConstraints(0, 7, 1, 1, GridBagConstraints.EAST));
    	textFields[8] = createTextField(fieldSizes[8]);
    	add(textFields[8], createConstraints(1, 7, 1, 1, GridBagConstraints.WEST));
    	
    	// Postal/Zip Code
    	add(createLabel(fields[9]), createConstraints(2, 7, 1, 1, GridBagConstraints.EAST));
    	textFields[9] = createTextField(fieldSizes[9]);
    	add(textFields[9], createConstraints(3, 7, 1, 1, GridBagConstraints.WEST));
    	
    	// Phone Number
    	add(createLabel(fields[10]), createConstraints(2, 0, 1, 1, GridBagConstraints.EAST));
    	textFields[10] = createTextField(fieldSizes[10]);
    	add(textFields[10], createConstraints(3, 0, 1, 1, GridBagConstraints.WEST));
    	
    	// Mobile Number
    	add(createLabel(fields[11]), createConstraints(2, 1, 1, 1, GridBagConstraints.EAST));
    	textFields[11] = createTextField(fieldSizes[11]);
    	add(textFields[11], createConstraints(3, 1, 1, 1, GridBagConstraints.WEST));
    	
    	// Fax Number
    	add(createLabel(fields[12]), createConstraints(2, 2, 1, 1, GridBagConstraints.EAST));
    	textFields[12] = createTextField(fieldSizes[12]);
    	add(textFields[12], createConstraints(3, 2, 1, 1, GridBagConstraints.WEST));
    	
    	// Email Address 
    	add(createLabel(fields[13]), createConstraints(2, 3, 1, 1, GridBagConstraints.EAST));
    	textFields[13] = createTextField(fieldSizes[13]);
    	add(textFields[13], createConstraints(3, 3, 1, 1, GridBagConstraints.WEST));
    	
    }
    
    private JLabel createLabel(String text) {
    	JLabel label = new JLabel(text);
    	label.setFont(new Font(DEFAULT_LABEL_FONT_FACE, DEFAULT_LABEL_FONT_STYLE, DEFAULT_LABEL_FONT_SIZE));
    	
    	return label;
    }
    
    private JTextField createTextField(int width) {
    	JTextField field = new JTextField(width);
    	field.setFont(new Font(DEFAULT_TEXTFIELD_FONT_FACE, DEFAULT_TEXTFIELD_FONT_STYLE, DEFAULT_TEXTFIELD_FONT_SIZE));
    	
    	return field;
    }
    
    /**
     * Creates a <code>GridBagConstraints</code> based on the specified parameters. The <em>anchor</em> parameter can be specified by a <code>GridBagConstraints</code> constant.
     * @param gridx the 'x' value marking the location of the component
     * @param gridy the 'y' value marking the location of the component
     * @param gridwidth the width of the component
     * @param gridheight the height of the component
     * @param anchor the location to anchor the component to
     * @return a <code>GridBagConstraints</code> based on the specified parameters
     */
    private GridBagConstraints createConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
    	GridBagConstraints constraints = new GridBagConstraints();
    	
    	constraints.gridx = gridx;
    	constraints.gridy = gridy;
    	constraints.gridwidth = gridwidth;
    	constraints.gridheight = gridheight;
    	constraints.anchor = anchor;
    	
    	constraints.insets = new Insets(3, 3, 3, 3);
    	constraints.ipadx = 1;
    	constraints.ipady = 1;
    	
    	return constraints;
    }
    
}
