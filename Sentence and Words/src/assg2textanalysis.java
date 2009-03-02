import javax.swing.JOptionPane;

/**
 * This application will consist of a system that will accept a string sentence (defined as a set of one or more words ending with a period, separated by spaces) from the user, then perform various forms of text analysis on the string.
 * Testing sentence should be, "as  the   new  days   started and the  eager runner ran with   the  endless sea and  dreamy shore  in   sight    the early sun caught  his gaze."
 *
 * @author	Alan Ly
 * @version	final-build:2009.03.01-00
 */

public class assg2textanalysis {

	/**
	 * The main method will handle the input from user and print the correct data to the console. If it detects that the input does not end with a period (therefore not qualifying as a defined sentence), it will quit and print an error message.
	 */
	public static void main(String[] args) {
		String inputSentence = JOptionPane.showInputDialog("Enter a sentence, ending in a period:");
		
		if (inputSentence.charAt(inputSentence.length() - 1) != '.') {
			System.out.println("\nERROR: Invalid Input");
			System.out.println("- The sentence must end with a period character. The sentence you have entered,");
			System.out.println("\t\"" + inputSentence + "\"");
			System.out.println("- does not match the requirements. Please check your input and try again.");
			System.exit(1);
		}

		System.out.println("\t\t.: Text Analysis :.");
		System.out.println("[Original sentence entered]");
		System.out.println("\t\"" + inputSentence + "\"");
		System.out.println("\n[Sentence without excess blanks (if any)]");
		System.out.println("\t\"" + squeezeBlanks(inputSentence) + "\"");
		System.out.println("\n[Length of the longest word run, counted in connections between words]\n\t" + countMaxWordRun(squeezeBlanks(inputSentence)));
	}

	/**
	 * This method will analyse, calculate and return the length of the longest word run in the sentence provided.
	 *
	 * @param	inputString	This String parameter holds the sentence that is to be processed through, and should be
	 * 						provided by the main method.
	 * @return	Returns an Integer value that represents the length of the longest word run in the sentence.
	 */
	public static int countMaxWordRun(String inputString) {
		int lengthMaxWordRun = 0;
		
		for (int stringIndex = 0, wordRunCount = 0; stringIndex + 1 < inputString.length();) {
			int wordEnd = findWordEnd(inputString, stringIndex);
			int nextWordBegin = findWordStart(inputString, wordEnd);
			
			if (inputString.charAt(wordEnd - 1) == inputString.charAt(nextWordBegin)) {
				wordRunCount++;
			} else {
				if (wordRunCount > lengthMaxWordRun)
					lengthMaxWordRun = wordRunCount;
				else
					wordRunCount = 0;
			}
			
			stringIndex = findWordStart(inputString, wordEnd);
		}
		
		return lengthMaxWordRun;
	}

	/**
	 * Provided a sentence and the starting position of a specific word in the sentence, this method will return the position of the last character in the word + 1.
	 *
	 * @param	inputString This String parameter holds the sentence that is to be processed through; should be provided by the calling method.
	 * @param	wordBegin	This Integer parameter holds the starting position (where the word begins) of the word that is being processed; should be provided by the calling method.
	 * @return 	Returns an Integer value that represents the position of the last character of the processed word plus one.
	 */
	public static int findWordEnd(String inputString, int wordBegin) {
		if (inputString.indexOf(' ', wordBegin) == -1)
			return inputString.indexOf('.', wordBegin);
		else
			return inputString.indexOf(' ', wordBegin);
	}

	/**
	 * Provided a sentence and the position of the last character of a specific word in the sentence, this program will return the starting position of the next word if available, else it will return the position of the period.
	 *
	 * @param	inputString	This String parameter holds the sentence that is to be processed through, and should be provided by the calling method.
	 * @param	wordEnd	This Integer parameter holds the position of the last character of a word, and should be
	 * 							provided by the calling method.
	 * @return	Returns an Integer value that represents the position of the first character of the proceeding word or period.
	 */
	public static int findWordStart(String inputString, int wordEnd) {
		if (inputString.charAt(wordEnd) == '.')
			return wordEnd;
		else
			return inputString.indexOf(' ', wordEnd) + 1;
	}

	/**
	 * Provided a sentence, this method will return the sentence with excess spaces between word and word runs in the original sentence removed and replaced by a single space character.
	 *
	 * @param	inputString	This String parameter holds the sentence that is to be processed through, and should be provided by the
	 * 						calling method.
	 * @return	Returns a String that represents the original input sentence with the excess spaces removed.
	 */
	public static String squeezeBlanks(String inputString) {
		String outputString = "";
		
		for (int stringIndex = 0; stringIndex < inputString.length() - 1;) {
			outputString += inputString.substring(stringIndex, findWordEnd(inputString, stringIndex) + 1);
			for (stringIndex = findWordStart(inputString, findWordEnd(inputString, stringIndex)); inputString.charAt(stringIndex) == ' '; stringIndex++);
		}

		return outputString;
	}
}