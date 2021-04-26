import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * fileToLinesArray, what the function does:
 * this function takes stop.txt file, and breaks it down line by line and stores it in the linesArray.
 * 
 * parameters: explain the input parameters
 * 1) fileName: which is "stop.txt", name of the file which will be broken down into lines.
 * 2) arraySize: int number which represents the size of the array (8759 in our case)
 *
 * returns: linesArray with all the lines, starting with the second one (1888,51874,WB HASTINGS ST FS HOLDOM AVE-,....)
 
 * linesArrToTST, what the function does:
 * this function takes the linesArray, produced by the function above, and breaks it down further. However, unlike the previous function, this one 
 * only takes part of the text, stop id and stop name in particular. These values are then stored in the 2d array.
 * parameters: explain the input parameters
 * 1) linesArray: array with all the stop.txt lines.
 * 2) arraySize: int number which represents the size of the array (8759 in our case)
 *
 * returns: linesArray with all the lines, beggining with the second one (1888,51874,WB HASTINGS ST FS HOLDOM AVE-,....)
 
 * saveResults, what the function does:
 * This function creates an 2D array to store [int stop id] and [String with all the info about the bus stop]
 * parameters: explain the input parameters
 * 1) linesArray: array with all the stop.txt lines.
 * 2) arraySize: int number which represents the size of the array (8759 in our case)
 */

public class readBusStations {

	static String[] fileToLinesArray (String fileName, int arraySize) throws IOException {

		String[] arrayOutput = new String[arraySize];

		int i = 0;
		String strLine = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		while (strLine != null) {

			strLine = br.readLine();
			arrayOutput[i] = strLine;
			i = i + 1;
		}
		br.close();
		return arrayOutput;     
	}

	static Object[][] linesArrToTST (String[] linesArray, int arraySize) {

		Object[][] arrayOutput = new Object[arraySize][2];

		String line = "";
		String[] tokens = null;
		char char1 = '\u0000';
		char char2 = '\u0000';
		String flagstop = "";

		arrayOutput[0][1] = "a";
		arrayOutput[0][0] = 1;

		for (int i = 1; i < arraySize; i++) {

			line = linesArray[i];	
			
			if (line != null) {
				tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			}
			
			arrayOutput[i][0] = tokens[0]; //filling first column in the array with stop ids
			arrayOutput[i][1] = tokens[2]; //filling the second column in the array with stops names, associated with the stop id form the frist column.
	
			//Now we need to get rid of WB, NB, SB or EB flagstops.
			char1 = ((String) arrayOutput[i][1]).charAt(0);
			char2 = ((String) arrayOutput[i][1]).charAt(1);
			//checking for the word flagstop
			flagstop = ((String) arrayOutput[i][1]).substring(0, Math.min(((String) arrayOutput[i][1]).length(), 8));
			if (flagstop.equals("FLAGSTOP")) {
				arrayOutput[i][1] = ((String) arrayOutput[i][1]).substring(5);
			}
			//checking for the WB, NB, SB or EB
			if (((char1 == 'W') && (char2 == 'B')) || ((char1 == 'N') && (char2 == 'B')) || ((char1 == 'S') && (char2 == 'B')) || ((char1 == 'E') && (char2 == 'B'))) {
				arrayOutput[i][1] = ((String) arrayOutput[i][1]).substring(2); //removing the first 2 chars from a bus name.
				arrayOutput[i][1] = ((String) arrayOutput[i][1]).replaceFirst("^\\s*", ""); //removing the whitespace in the beginning of a bus name.
	        } 
		}
		return arrayOutput;     
	}
	
	static Object[][] saveResults (String[] linesArray, int arraySize) {

		Object[][] arrWithFullInfo = new Object[arraySize][2];
		String line = "";
		String[] tokens = null;
		
		arrWithFullInfo[0][1] = "a";
		arrWithFullInfo[0][0] = 1;

		for (int i = 1; i < arraySize; i++) {

			line = linesArray[i];	
			
			if (line != null) {
				tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			}	
			//coping full info and it's bus stop id into another array, so the full information can be showed to user.
			arrWithFullInfo[i][1] = line;
			arrWithFullInfo[i][0] = tokens[0];
		}
		return arrWithFullInfo;
	}	
}

