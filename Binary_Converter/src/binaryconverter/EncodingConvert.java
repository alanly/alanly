package binaryconverter;

/**
 * The <coding>EncodingConvert</code> class handles all conversion between character encoding types depending on input from the calling method.
 * @author	Alan Ly
 * @version	build:2009.04.05-0
 */
public class EncodingConvert {
	// Instance variable declarations...
	private String[] inputDataArray;
	private String inputDataType;
	private final String[][] encodingChart = {
			// ASCII Binary, ASCII Character
			{"01000001","A"},{"01000010","B"},{"01000011","C"},{"01000100","D"},
			{"01000101","E"},{"01000110","F"},{"01000111","G"},{"01001000","H"},
			{"01001001","I"},{"01001010","J"},{"01001011","K"},{"01001100","L"},
			{"01001101","M"},{"01001110","N"},{"01001111","O"},{"01010000","P"},
			{"01010001","Q"},{"01010010","R"},{"01010011","S"},{"01010100","T"},
			{"01010101","U"},{"01010110","V"},{"01010111","W"},{"01011000","X"},
			{"01011001","Y"},{"01011010","Z"},{"01100001","a"},{"01100010","b"},
			{"01100011","c"},{"01100100","d"},{"01100101","e"},{"01100110","f"},
			{"01100111","g"},{"01101000","h"},{"01101001","i"},{"01101010","j"},
			{"01101011","k"},{"01101100","l"},{"01101101","m"},{"01101110","n"},
			{"01101111","o"},{"01110000","p"},{"01110001","q"},{"01110010","r"},
			{"01110011","s"},{"01110100","t"},{"01110101","u"},{"01110110","v"},
			{"01110111","w"},{"01111000","x"},{"01111001","y"},{"01111010","z"},
			{"00100001","!"},{"00100010","\""},{"00100011","#"},{"00100100","$"},
			{"00100101","%"},{"00100110","&"},{"00100111","'"},{"00101000","("},
			{"00101001",")"},{"00101010","*"},{"00101011","+"},{"00101100",","},
			{"00101101","-"},{"00101110","."},{"00101111","/"},{"00110000","0"},
			{"00110001","1"},{"00110010","2"},{"00110011","3"},{"00110100","4"},
			{"00110101","5"},{"00110110","6"},{"00110111","7"},{"00111000","8"},
			{"00111001","9"},{"00111010",":"},{"00111011",";"},{"00111100","<"},
			{"00111101","="},{"00111110",">"},{"00111111","?"},{"01000000","@"},
			{"01011011","["},{"01011100","\\"},{"01011101","]"},{"01011110","^"},
			{"01011111","_"},{"01100000","`"},{"01111011","{"},{"01111100","|"},
			{"01111101","}"},{"01111110","~"},{"00100000"," "}
	};
	
	/**
	 * The <code>EncodingConvert</code> class' default constructor.
	 */
	public EncodingConvert() {
		
	}

	/**
	 * The <code>EncodingConvert</code> class' overloaded constructor, which will initialise the two instance variables.
	 * @param	inputDataArray	This parameter is a String array passed by the calling method. Each encoded character should be in its own element in the single-dimension array.
	 * @param	inputDataType	This parameter is a String variable that will contain the type of data which is in the inputDataArray array.  It could either be "asciiChar" or "asciiBin".
	 */
	public EncodingConvert(String[] inputDataArray, String inputDataType) {
		// Bring parameter variables into instance...
		this.inputDataArray = inputDataArray;
		this.inputDataType = inputDataType;
	}
	
	/**
	 * This accessor method will fetch the input data instance array variable.
	 * @return	Returns a String array containing the input data array that was provided to the object upon initialisation.
	 */
	public String[] getInputDataArray() {
		return inputDataArray;
	}
	
	/**
	 * This accessor method will fetch the input data type variable.
	 * @return	Returns the String variable which holds the input data format that was provided to the object upon initialisation.
	 */
	public String getInputDataType() {
		return inputDataType;
	}
	
	/**
	 * This method will be responsible for converting the input data from its current state into the ASCII character set...
	 * @return	Returns a String array that contains the converted data.
	 */
	public String[] convertToAsciiChar() {
		// Declare and initialise variables...
		int inputArraySize = inputDataArray.length;
		int encArraySize = encodingChart.length;
		String[] outputArray = new String[inputArraySize];
		
		// Determine if input is already in ASCII characters or not... 
		if (inputDataType.equals("asciiChar"))
			return null;
		
		// Loop through input array and compare to encoding chart to find the ASCII character equivalent...
		for (int inputArrayIndex = 0; inputArrayIndex < inputArraySize; inputArrayIndex++)
			for (int encArrayIndex = 0; encArrayIndex < encArraySize; encArrayIndex++)
				if (inputDataArray[inputArrayIndex].equals(encodingChart[encArrayIndex][0]))
					outputArray[inputArrayIndex] = encodingChart[encArrayIndex][1];
		
		return outputArray;
	}
	
	/**
	 * This method will be responsible for converting the input data from its current state into the ASCII encoded binary form...
	 * @return	Returns a String array that contains the converted data.
	 */
	public String[] convertToAsciiBin() {
		// Declare and initialise variables...
		int inputArraySize = inputDataArray.length;
		int encArraySize = encodingChart.length;
		String[] outputArray = new String[inputArraySize];
		
		// Determine if input is already in ASCII encoded binary or not...
		if (inputDataType.equals("asciiBin"))
			return null;
		
		// Loop through input array and compare to encoding chart to find the ASCII encoded binary equivalent...
		for (int inputArrayIndex = 0; inputArrayIndex < inputArraySize; inputArrayIndex++) {
			for (int encArrayIndex = 0; encArrayIndex < encArraySize; encArrayIndex++)
				if (inputDataArray[inputArrayIndex].equals(encodingChart[encArrayIndex][1]))
					outputArray[inputArrayIndex] = encodingChart[encArrayIndex][0];
		}
		
		return outputArray;
	}
}