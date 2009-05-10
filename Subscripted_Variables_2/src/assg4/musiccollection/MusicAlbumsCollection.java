/**
 * This package holds the logic class that handles the primary input as well as providing the appropriate methods to process the arrays.
 */
package assg4.musiccollection;

import java.util.*;
import javax.swing.JOptionPane;

/**
 * The <code>MusicAlbumsCollection</code> class is responsible for holding the essential data relating to the instance of its object, as well as providing essential methods to process such data.
 * @author Alan Ly
 * @version build:2009.05.09-02
 */
public class MusicAlbumsCollection {
	// Declare instance variables...
		private String collectionName;	// Name of MusicAlbumsCollection object.
		private int totalArtists;	// Total number of artists entered by the user.
		private String[] artists = new String[50];	// Array of artist names entered by user.
		private int[] albumsByArtist = new int[50];	// Number of albums by the corresponding artist.
		private int[] goldAlbumsByArtist = new int[50];	// Number of albums by the corresponding artists, certified gold.
	
	/**
	 * The <code>MusicAlbumsCollection</code> class default constructor. 
	 */
	public MusicAlbumsCollection() {
	}
			
	/**
	 * The <code>MusicAlbumsCollection</code> class overloaded constructor method that will accept a <code>String</code> input from the calling method, which will determine the user-friendly name for the album collection object. It will then prompt the user to enter the data for artist name, number of albums by said artist, and number of certified gold albums by said artist. It will continue to do so until the end of the array or when the user enters the specified sentinel values in one of the prompt boxes, which will break the user free from the loop and return to the calling method. 
	 * @param collectionName	This <code>String</code> parameter holds the name of the album collection as determined by the calling method.
	 */
	public MusicAlbumsCollection(String collectionName) {
		// Set name of collection...
		this.collectionName = collectionName;
		
		// Declare variables...
		String inputArtist = "";
		int inputAlbumsByArtist = 0;
		int inputGoldAlbumsByArtist = 0;
		Scanner keyboard = new Scanner(System.in);
		int arrayIndex = 0;
		
		// Request for and input user data and catch exceptions...
		do {	
			try {				
				// Display header...
				System.out.println("\n[Artist #" + (totalArtists + 1) + "]");
				
				// Accept user input and check for sentinel values...
				System.out.print("\tEnter the artist name: (Entering 'sentinel' stops input loop)\n\t   > ");
				inputArtist = keyboard.nextLine();
				if (inputArtist.equalsIgnoreCase("sentinel"))
					break;
				
				System.out.print("\tEnter the number of albums by " + inputArtist + ": (Entering '9999' stops input loop)\n\t   > ");
				inputAlbumsByArtist = keyboard.nextInt();
				if (inputAlbumsByArtist == 9999)
					break;
				else if (inputAlbumsByArtist < 0 || inputAlbumsByArtist > 1000) {
					System.err.print("***! Please keep the number of albums within the range of 0 and 1000 inclusive.. !***");
					continue;
				}
				
				System.out.print("\tEnter the number of gold albums by " + inputArtist + ": (Entering '9999' stops input loop)\n\t   > ");
				inputGoldAlbumsByArtist = keyboard.nextInt();
				if (inputGoldAlbumsByArtist == 9999)
					break;
				else if (inputGoldAlbumsByArtist < 0 || inputGoldAlbumsByArtist > 100) {
					System.err.print("***! Please keep the number of gold albums within the range of 0 and 100 inclusive. !***");
					continue;
				}
				
				// Remove residual new line character in the buffer...
				keyboard.nextLine();
				
				// Enter data into appropriate array elements...
				artists[arrayIndex] = new String(inputArtist);
				albumsByArtist[arrayIndex] = inputAlbumsByArtist;
				goldAlbumsByArtist[arrayIndex] = inputGoldAlbumsByArtist;
					
				// Advance counter variables...
				totalArtists++;
				arrayIndex++;
			}
			catch (InputMismatchException ime) {
				System.err.println("\tYou have entered an invalid integer value. Please re-enter the data set for '" + inputArtist + "'.\n");
				keyboard.nextLine();
				continue;
			}
			catch (ArrayIndexOutOfBoundsException aioobe) {
				JOptionPane.showMessageDialog(null, "Error: Null or size of zero arrays; no data to be processed.", "Error: Invalid arrays", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		} while (arrayIndex < artists.length);
	}
	
	/**
	 * The <code>getCollectionName</code> accessor method that will retrieve the name of the album collection from the instance variable.
	 * @return	Returns a <code>String</code> containing the name of the album collection as stored in the instance variable.
	 */
	public String getCollectionName() {
		return collectionName;
	}
	
	/**
	 * The <code>getNumberOfArtists</code> accessor method that will retrieve the total number of artists that have been entered into the <code>artists</code> array by the user.
	 * @return	Returns an <code>Integer</code> with the sum of artists that have been entered by the user.
	 */
	public int getNumberOfArtists() {		
		return totalArtists;
	}
	
	/**
	 * The <code>getArtistNamesArray</code> accessor method that will return the array containing the artist names, that have been entered by the user.
	 * @return	Returns a <code>String</code> array containing the names of all the artists that have been entered by the user.
	 */
	public String[] getArtistNamesArray() {
		return artists;
	}
	
	/**
	 * The <code>getNumAlbumsArray</code> accessor method that will return an array containing the number of albums for each artists that has been entered by the user.
	 * @return	Returns an <code>Integer</code> array containing the number of albums of all the artists that have been entered by the user.
	 */
	public int[] getNumAlbumsArray() {
		return albumsByArtist;
	}
	
	/**
	 * The <code>getNumGoldAlbumsArray</code> accessor method that will return an array containing the number of certified gold album for each artists that has been entered by the user.
	 * @return	Returns an <code>Integer</code> array containing the number of certified gold albums of all the artists that have been entered by the user.
	 */
	public int[] getNumGoldAlbumsArray() {
		return goldAlbumsByArtist;
	}
	
	/**
	 * The <code>setCollectionName</code> mutator method that will modify the name of the album collection.
	 * @param newCollectionName	This <code>String</code> parameter will contain the new name that will be entered into the <code>collectionName</code> instance variable. It should be provided by the calling method.
	 */
	public void setCollectionName(String newCollectionName) {
		this.collectionName = new String(newCollectionName);
	}
	
	/**
	 * The <code>calculateAverageNumAlbums</code> method will be responsible for calculating the average number of albums per artist.
	 * @return	Returns a <code>Double</code> value representing the average number of albums per artist as entered by the user.
	 */
	public double calculateAverageNumAlbums() {
		// Declare variable...
		double sumOfAlbums = 0.0;
		
		// Loop through array and calculate total number of albums...
		for (int arrayIndex = 0; arrayIndex < totalArtists; arrayIndex++)
			sumOfAlbums += albumsByArtist[arrayIndex];
		
		return sumOfAlbums / totalArtists;
	}

	/**
	 * The <code>calculateNumArtistsWithLowerThanAvgNumAlbums</code> method will be responsible for calculating the number of artists that have lower than average number of albums in comparison with others in the array.
	 * @return	Returns an <code>Integer</code> value representing the sum of artists that have lower than average number of albums.
	 */
	public int calculateNumArtistsWithLowerThanAvgNumAlbums() {
		// Declare and initialise variables...
		int numArtistsWithLowerThanAvgNumAlbums = 0;
		double averageNumAlbums = calculateAverageNumAlbums();
		
		// Loop through array and sum up number of artists with lower than average albums...
		for (int arrayIndex = 0; arrayIndex < totalArtists; arrayIndex++)
			if (albumsByArtist[arrayIndex] < averageNumAlbums)
				numArtistsWithLowerThanAvgNumAlbums++;
			
		return numArtistsWithLowerThanAvgNumAlbums;
	}
	
	/**
	 * The <code>findArtistWithGoldNum</code> method will be responsible for finding the name of the last artist in the array <strong>alphabetically</strong> that has a number of certified gold albums equivalent to <code>goldScore</code>, which is a parameter provided by the calling method. If none are found then "<code>noartist</code>" is returned.
	 * @param	goldScore	This <code>Integer</code> parameter holds the number of gold albums to which this method must match an artist name to.
	 * @return	Returns a <code>String</code> that will contain the name of the artist which has the matching amount of gold albums.
	 */
	public String findArtistWithGoldNum(int goldScore) {		
		// Declare and initialise variables...
		String artistWithGoldNum = "noartist";
		int arrayIndex;
		
		// Determine the first artist with equivalent goldScore with loop if necessary...
		for (arrayIndex = 0; arrayIndex < totalArtists && artistWithGoldNum.equals("noartist"); arrayIndex++)
			if (goldAlbumsByArtist[arrayIndex] == goldScore)
				artistWithGoldNum = artists[arrayIndex];
		
		// Loop through array while comparing Strings to see find alphabetically, the last artist with equivalent goldScore...
		while (arrayIndex < totalArtists) {
			if (goldAlbumsByArtist[arrayIndex] == goldScore && artistWithGoldNum.compareToIgnoreCase(artists[arrayIndex]) < 0)
				artistWithGoldNum = artists[arrayIndex];
			arrayIndex++;
		}
		
		return artistWithGoldNum;
	}
	
	/**
	 * The <code>findNumAlbumsOfArtist</code> method will be responsible for finding the number of albums by <code>artistName</code>, which is the parameter provided by the calling method. If none are found, then this method will return '9999'.
	 * @param artistName	This <code>String</code> parameter holds the name of the artist, which this method must find the number of albums by.
	 * @return	Returns an <code>Integer</code> value that will contain the number of albums by <code>artistName</code>.
	 */
	public int findNumAlbumsOfArtist(String artistName) {
		// Run through loop and search for equivalent artistName...
		for (int arrayIndex = 0; arrayIndex < totalArtists; arrayIndex++)
			if (artists[arrayIndex].equalsIgnoreCase(artistName))
				return albumsByArtist[arrayIndex];
		
		return 99999;
	}
	
	/**
	 * The <code>sortNamesAndNumAlbumsOnArtistName</code> method will be responsible for sorting, in parallel, the three arrays containing artist names and albums based on the ascending alphabetical order of the artist names.
	 */
	public void sortNamesAndNumAlbumsOnArtistName() {
		// Check for null arrays...
		if (artists == null || artists.length == 0 || albumsByArtist == null || albumsByArtist.length == 0 || goldAlbumsByArtist == null || goldAlbumsByArtist.length == 0) {
			JOptionPane.showMessageDialog(null, "Error: Null or size of zero arrays; no data to be processed.", "Error: Invalid arrays", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		// Declare and initialise variables...
		int arrayIndex, tempNumAlbums, tempNumGoldAlbums;
		String tempArtistName;
		boolean madeSwap;
		int lastUnsortedElement = totalArtists - 1;
		
		// Loop through array, sort and organise...
		do {
			madeSwap = false;
			for (arrayIndex = 0; arrayIndex < lastUnsortedElement; arrayIndex++)
				if (artists[arrayIndex].compareToIgnoreCase(artists[arrayIndex + 1]) > 0) {
					// Swap values for 'artists' array...
					tempArtistName = new String(artists[arrayIndex]);
					artists[arrayIndex] = new String(artists[arrayIndex + 1]);
					artists[arrayIndex + 1] = new String(tempArtistName);
					
					// Swap values for 'albumsByArtist' array...
					tempNumAlbums = albumsByArtist[arrayIndex];
					albumsByArtist[arrayIndex] = albumsByArtist[arrayIndex + 1];
					albumsByArtist[arrayIndex + 1] = tempNumAlbums;
					
					// Swap values for 'goldAlbumsByArtist' array...
					tempNumGoldAlbums = goldAlbumsByArtist[arrayIndex];
					goldAlbumsByArtist[arrayIndex] = goldAlbumsByArtist[arrayIndex + 1];
					goldAlbumsByArtist[arrayIndex + 1] = tempNumGoldAlbums;
					
					// Set boolean to true...
					madeSwap = true;
				}
			lastUnsortedElement--;
		} while (madeSwap);
	}
	
	/**
	 * The <code>sortNamesAndNumAlbumsOnNumAlbums</code> method will be responsible for sorting, in parallel, the three arrays containing artist names and albums based on the ascending order of the number of albums.
	 */
	public void sortNamesAndNumAlbumsOnNumAlbums() {
		// Check for null arrays...
		if (artists == null || artists.length == 0 || albumsByArtist == null || albumsByArtist.length == 0 || goldAlbumsByArtist == null || goldAlbumsByArtist.length == 0) {
			JOptionPane.showMessageDialog(null, "Error: Null or size of zero arrays; no data to be processed.", "Error: Invalid arrays", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		// Declare and initialise variables...
		int arrayIndex, tempNumAlbums, tempNumGoldAlbums;
		String tempArtistName;
		boolean madeSwap;
		int lastUnsortedElement = totalArtists - 1;
		
		// Loop through array, sort and organise...
		do {
			madeSwap = false;
			for (arrayIndex = 0; arrayIndex < lastUnsortedElement; arrayIndex++)
				if (albumsByArtist[arrayIndex] > albumsByArtist[arrayIndex + 1]) {
					// Swap values for 'artists' array...
					tempArtistName = new String(artists[arrayIndex]);
					artists[arrayIndex] = new String(artists[arrayIndex + 1]);
					artists[arrayIndex + 1] = new String(tempArtistName);
					
					// Swap values for 'albumsByArtist' array...
					tempNumAlbums = albumsByArtist[arrayIndex];
					albumsByArtist[arrayIndex] = albumsByArtist[arrayIndex + 1];
					albumsByArtist[arrayIndex + 1] = tempNumAlbums;
					
					// Swap values for 'goldAlbumsByArtist' array...
					tempNumGoldAlbums = goldAlbumsByArtist[arrayIndex];
					goldAlbumsByArtist[arrayIndex] = goldAlbumsByArtist[arrayIndex + 1];
					goldAlbumsByArtist[arrayIndex + 1] = tempNumGoldAlbums;
					
					// Set boolean to true...
					madeSwap = true;
				}
			lastUnsortedElement--;
		} while (madeSwap);
	}
}