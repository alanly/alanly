package assg3.musiccollection;

import javax.swing.JOptionPane;

/**
 * The <code>MusicAlbumsCollection</code> class is responsible for holding the essential data relating to the instance of its object, as well as providing essential methods to process such data.
 * @author Alan Ly
 * @version build:2009.04.13-1
 */
public class MusicAlbumsCollection {
	// Declare instance variables...
		private String collectionName;	// Name of MusicAlbumsCollection object.
		private String[] artists = new String[50];	// Array of artist names entered by user.
		private int totalArtists;	// Total number of artists entered by the user.
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
		
		// Request for and input user data and catch exceptions...
		for (int arrayIndex = 0; arrayIndex < artists.length; arrayIndex++) {
			try {
				inputArtist = JOptionPane.showInputDialog(null, "Enter the artist's name:\n(To stop, enter 'sentinel')", "Album Collection", JOptionPane.QUESTION_MESSAGE);
				if (inputArtist.equals("sentinel"))
					break;
				else {
					inputAlbumsByArtist = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of albums by '" + inputArtist + "':\n(To stop, enter '9999')", "\"" + inputArtist + "\"", JOptionPane.QUESTION_MESSAGE));
					if (inputAlbumsByArtist == 9999)
						break;
					else {
						inputGoldAlbumsByArtist = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of certified gold albums by '" + inputArtist + "':\n(To stop, enter '9999')", "\"" + inputArtist + "\"", JOptionPane.QUESTION_MESSAGE));
						if (inputGoldAlbumsByArtist == 9999)
							break;
					}
				}
			
				// Increment artist count and store user data into array... 
				totalArtists++;
				artists[arrayIndex] = new String(inputArtist);
				albumsByArtist[arrayIndex] = inputAlbumsByArtist;
				goldAlbumsByArtist[arrayIndex] = inputGoldAlbumsByArtist;
			}
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "You have entered an invalid integer value. Please re-enter data for artist \"" + inputArtist + "\".\nDetails:\n    " + nfe.getMessage(), "Error: Non-Integer Input", JOptionPane.ERROR_MESSAGE);
				arrayIndex--;
			}
			catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(null, "Please enter a valid artist name, or enter 'sentinel' to quit and show results.\nDetails:\n    " + npe.getMessage(), "Error: Null String Input", JOptionPane.ERROR_MESSAGE);
				arrayIndex--;
			}
		}
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
		
		return 9999;
	}
}