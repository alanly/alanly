/**
 * This package holds the application class which handles output and basic input for collection name.
 */
package assg4.music;

import assg4.musiccollection.MusicAlbumsCollection;
import javax.swing.JOptionPane;
import java.util.Scanner;

/**
 * The <code>MusicAlbums</code> class will handle the priming input that will create the initial <code>MusicAlbumsCollection</code> object. It will then be responsible for the appropriate output data necessary.
 * @author Alan Ly
 * @version build:2009.05.03-02
 */
public class MusicAlbums {
	
	/**
	 * The <code>main</code> method is responsible for accepting the name of the album collection from the user and the output of the appropriate information to the user.
	 */
	public static void main (String [] args) {
		// Display splash screen...
		JOptionPane.showMessageDialog(null, 
				"[Album Collection Application]\n" +
				"  Written by: Alan Ly\n" +
				"  Last Amended: May 3, 2009"
				, "Album Collection", JOptionPane.PLAIN_MESSAGE);
		
		// Request user-friendly name of MusicAlbumsCollection from user... 
		String inputString = JOptionPane.showInputDialog(null, "Please enter the name of your album collection:", "Album Collection", JOptionPane.QUESTION_MESSAGE);
		
		// Create new MusicAlbumsCollection object with previously specified user-friendly name...
		System.out.print("To begin entering information into the collection press <Enter>...");
		MusicAlbumsCollection albumCollection = new MusicAlbumsCollection(inputString);
		
		// Get arrays with data...
		String[] artistNames = albumCollection.getArtistNamesArray();
		int[] numAlbums = albumCollection.getNumAlbumsArray();
		int[] numGoldAlbums = albumCollection.getNumGoldAlbumsArray();
		
		// Create Scanner instance...
		Scanner keyboard = new Scanner(System.in);
		
		// Output the original data...
		System.out.println("\nTo output data entered, press <Enter>");
		keyboard.nextLine();
		System.out.println("[" + albumCollection.getCollectionName() + " Collection - Original Data (Unsorted)]");
		System.out.println(" " + String.format("%-20s", "Artist Name") + "# of Albums" + "\t# of Gold Albums");
		System.out.println(" " + String.format("%-20s", "***********") + "***********" + "\t****************");
		for (int arrayIndex = 0; arrayIndex < albumCollection.getNumberOfArtists(); arrayIndex++)
			System.out.println(" " + String.format("%-20s", artistNames[arrayIndex]) + String.format("%-11s", numAlbums[arrayIndex]) + "\t" + numGoldAlbums[arrayIndex]);
		
		// Output the data sorted by artist name...
		albumCollection.sortNamesAndNumAlbumsOnArtistName();
		System.out.println("\nTo output data sorted by artist name, press <Enter>");
		keyboard.nextLine();
		System.out.println("[" + albumCollection.getCollectionName() + " Collection - Sorted by Artist Name]");
		System.out.println(" " + String.format("%-20s", "Artist Name") + "# of Albums" + "\t# of Gold Albums");
		System.out.println(" " + String.format("%-20s", "***********") + "***********" + "\t****************");
		for (int arrayIndex = 0; arrayIndex < albumCollection.getNumberOfArtists(); arrayIndex++)
			System.out.println(" " + String.format("%-20s", artistNames[arrayIndex]) + String.format("%-11s", numAlbums[arrayIndex]) + "\t" + numGoldAlbums[arrayIndex]);
		
		// Output the data sorted by the number of albums of artist...
		albumCollection.sortNamesAndNumAlbumsOnNumAlbums();
		System.out.println("\nTo output data sorted by artist name, press <Enter>");
		keyboard.nextLine();
		System.out.println("[" + albumCollection.getCollectionName() + " Collection - Sorted by Number of Albums]");
		System.out.println(" " + String.format("%-20s", "Artist Name") + "# of Albums" + "\t# of Gold Albums");
		System.out.println(" " + String.format("%-20s", "***********") + "***********" + "\t****************");
		for (int arrayIndex = 0; arrayIndex < albumCollection.getNumberOfArtists(); arrayIndex++)
			System.out.println(" " + String.format("%-20s", artistNames[arrayIndex]) + String.format("%-11s", numAlbums[arrayIndex]) + "\t" + numGoldAlbums[arrayIndex]);
		
		// Print footer...
		System.out.println("\n\t\t<Application Exit>");
	}
}