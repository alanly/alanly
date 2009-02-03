import javax.swing.*;

public class LoopsApp {
	/**
	 * This method reads in an integer value from the user in terms of how many characters
	 * wide and high to produce an array of asterisks in a diamond formation.
	 *
	 * @param	row 	The current row that the loop is generating.
	 * @param 	col 	The current column that the loop is working in.
	 * @param 	size	The size of the asterisks-diamond formation, entered by the user.
	 * @param 	spaces	The current blank character that is being printed.
	 * @return	Returns a formation of asterisks in the height and width requested from the user.
	 * @title	Loops Assignment
	 * @version	2009020202
	 */
	public static void main (String[] args) {
		// Read user input
			String inputString = JOptionPane.showInputDialog("Please enter an ODD number in the range of 1-21:");
			int size = Integer.parseInt(inputString);

		// Validate user input for proper data
			if((size % 2) == 0 || size < 1 || size > 21) {
				System.out.println("\nERROR: Invalid Input");
				System.out.println("- The value you have entered (\"" + inputString + "\") is in an invalid range.");
				System.out.println("- Please make sure you enter an ODD number within the range of 1 to 21, inclusive.\n");
				System.exit(1);
			}

		// Comments
			System.out.println("/-----------------------\\");
			System.out.println("|       LoopsApp        |");
			System.out.println("\\-----------------------/\n");

		// Do top half of formation
			for (int row = 1; row <= size/2+1; row++) {
				for (int spaces = 1; spaces <= (size/2+1-row); spaces++) {
					System.out.print(" ");
				}

				System.out.print("*");

				for (int col = 1; col < row; col++) {
					System.out.print("**");
				}

				System.out.println("");
			}

		// Do bottom half of formation
			for (int row = size/2; row > 0; row--) {
				for (int spaces = size/2; spaces+1 > row; spaces--) {
					System.out.print(" ");
				}

				for (int col = 1; col < row; col++) {
					System.out.print("**");
				}

				System.out.print("*\n");
			}

		// Comments
			System.out.println("\n/-----------------------\\");
			System.out.println("| 420-212-DW 01: Java I |");
			System.out.println("| Assignment #1: Loops  |");
			System.out.println("| Revision: 2009020202  |");
			System.out.println("\\-----------------------/");
			System.exit(0);
	}
}