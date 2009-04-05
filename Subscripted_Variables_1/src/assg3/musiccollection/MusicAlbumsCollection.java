package assg3.musiccollection;

import javax.swing.JOptionPane;

/**
 * The <code>MusicAlbumsCollection</code> class is responsible for holding the essential data relating to the instance of its object, as well as providing essential methods to process such data.
 * @author Alan Ly
 * @version build:2009.04.04-1
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
	 * The <code>MusicAlbumsCollection</code> class overloaded constructor method that will accept a <code>String</code> input from the calling method, which will determine the name for the album collection.
	 * @param collectionName	This <code>String</code> parameter holds the name of the album collection as determined by the calling method.
	 */
	public MusicAlbumsCollection(String collectionName) {
		// Set name of collection...
		this.collectionName = collectionName;
		
		// Declare variables...
		String inputArtist;
		int inputAlbumsByArtist;
		int inputGoldAlbumsByArtist;
		
		// Request for and input user data...
		for (int arrayIndex = 0; arrayIndex < artists.length; arrayIndex++) {
			inputArtist = JOptionPane.showInputDialog("Enter the artist's name:\n(To stop, enter 'sentinel')");
			if (inputArtist.equals("sentinel"))
				break;
			else {
				inputAlbumsByArtist = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of albums by '" + inputArtist + "':\n(To stop, enter '9999')"));
				if (inputAlbumsByArtist == 9999)
					break;
				else {
					inputGoldAlbumsByArtist = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of certified gold albums by '" + inputArtist + "':\n(To stop, enter '9999')"));
					if (inputGoldAlbumsByArtist == 9999)
						break;
				}
			}
			
			totalArtists++;
			artists[arrayIndex] = new String(inputArtist);
			albumsByArtist[arrayIndex] = inputAlbumsByArtist;
			goldAlbumsByArtist[arrayIndex] = inputGoldAlbumsByArtist;
		}
		
/*
		// Declare variables...
		String inputString;
		
		for (int arrayIndex = 0; arrayIndex < artists.length; arrayIndex++) {
			// Read in initial user data...
			inputString = JOptionPane.showInputDialog("Enter the artist's name:\n(To stop, enter 'sentinel')");
			
			// Determine if user wants to end or not...
			if (!inputString.equals("sentinel")) {
				// Add to sum of total valid artists entered...
				totalArtists++;
				
				// Input user data entries into array...
				artists[arrayIndex] = new String(inputString);
				albumsByArtist[arrayIndex] = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of albums by '" + artists[arrayIndex] + "':"));
				goldAlbumsByArtist[arrayIndex] = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of certified gold albums by '" + artists[arrayIndex] + "':"));
			} else {
				// End loop upon user request...
				break;
			}
		}
*/
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
		
		// Loop through array and 
		for (int arrayIndex = 0; arrayIndex < totalArtists; arrayIndex++)
			sumOfAlbums += albumsByArtist[arrayIndex];
		
		return sumOfAlbums / totalArtists;
	}

	/**
	 * The <code>calculateNumArtistsWithLowerThanAvgNumAlbums</code> method will be responsible for calculating the number of artists that have lower than average number of albums in comparison with others in the array.
	 * @return	Returns an <code>Integer</code> value representing the sum of artists that have lower than average number of albums.
	 */
	public int calculateNumArtistsWithLowerThanAvgNumAlbums() {
		int numArtistsWithLowerThanAvgNumAlbums = 0;
			
		for (int arrayIndex = 0; arrayIndex < totalArtists; arrayIndex++)
			if (albumsByArtist[arrayIndex] < calculateAverageNumAlbums())
				numArtistsWithLowerThanAvgNumAlbums++;
			
		return numArtistsWithLowerThanAvgNumAlbums;
	}
	
	/**
	 * The <code>findArtistWithGoldNum</code> method will be responsible for finding the name of the last artist in the array <strong>alphabetically</strong> that has a number of certified gold albums equivalent to <code>goldScore</code>, which is a parameter provided by the calling method.
	 * @param	goldScore	This <code>Integer</code> parameter holds the number of gold albums to which this method must match an artist name to.
	 * @return	Returns a <code>String</code> that will contain the name of the artist which has the matching amount of gold albums.
	 */
	public String findArtistWithGoldNum(int goldScore) {		
		// Declare and initialise variables...
		String artistWithGoldNum = "noartist";
		
		// Loop through array and determine the last artist alphabetically...
			for (int arrayIndex = 0; arrayIndex < totalArtists && artistWithGoldNum.equals("noartist"); arrayIndex++)
				if (goldAlbumsByArtist[arrayIndex] == goldScore)
					artistWithGoldNum = artists[arrayIndex];
			
			for (int arrayIndex = 0; arrayIndex < totalArtists; arrayIndex++)
				if (goldAlbumsByArtist[arrayIndex] == goldScore && artistWithGoldNum.compareTo(artists[arrayIndex]) < 0)
					artistWithGoldNum = artists[arrayIndex];
		
		return artistWithGoldNum;
	}
	
	/**
	 * The <code>findNumAlbumsOfArtist</code> method will be responsible for finding the number of albums by <code>artistName</code>, which is the parameter provided by the calling method. If none are found, then this method will return '9999'.
	 * @param artistName	This <code>String</code> parameter holds the name of the artist, which this method must find the number of albums by.
	 * @return	Returns an <code>Integer</code> value that will contain the number of albums by <code>artistName</code>.
	 */
	public int findNumAlbumsOfArtist(String artistName) {
		for (int arrayIndex = 0; arrayIndex < totalArtists; arrayIndex++)
			if (artists[arrayIndex].equals(artistName))
				return albumsByArtist[arrayIndex];
		
		return 9999;
	}
}