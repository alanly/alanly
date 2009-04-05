package assg3.music;

import assg3.musiccollection.MusicAlbumsCollection;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

/**
 * The <code>MusicAlbums</code> class will handle the priming input that will create the initial <code>MusicAlbumsCollection</code> object. It will then be responsible for the appropriate output data necessary.
 * @author Alan Ly
 * @version build:2009.04.04-0
 */
public class MusicAlbums {
	
	/**
	 * The <code>main</code> method is responsible for accepting the name of the album collection from the user and the output of the appropriate information to the user.
	 */
	public static void main (String [] args) {
		// Display splash screen...
		JOptionPane.showMessageDialog(null, 
				"[Album Collection Application]\n" +
				"Written by: Alan Ly\n" +
				"Last Amended: April 2, 2009"
				, "Album Collection Application", JOptionPane.PLAIN_MESSAGE);
		
		// Request user-friendly name of MusicAlbumsCollection from user... 
		String inputString = JOptionPane.showInputDialog("Please enter the name of your album collection:");
		
		// Create new MusicAlbumsCollection object with previously specified user-friendly name...
		MusicAlbumsCollection albumCollection = new MusicAlbumsCollection(inputString);
		
		// Crate DecimalFormat object...
		DecimalFormat decimal = new DecimalFormat("#,##0.00");
		
		// Display output...
		JOptionPane.showMessageDialog(null,
				"[" + albumCollection.getCollectionName() + " Collection]" +
				"\nYour collection \"" + albumCollection.getCollectionName() + "\" has " + albumCollection.getNumberOfArtists() + " artists in it." +
				"\n- Average number of albums per artist: "+ decimal.format(albumCollection.calculateAverageNumAlbums()) +
				"\n- Number of artists below that average : " + albumCollection.calculateNumArtistsWithLowerThanAvgNumAlbums() +
				"\n- Last artist entered with 4 gold albums: \"" + albumCollection.findArtistWithGoldNum(4) + "\"" +
				"\n- Number of albums by Pink Floyd: " + albumCollection.findNumAlbumsOfArtist("Pink Floyd")
				, "Album Collection Applicaton", JOptionPane.PLAIN_MESSAGE);
	}
}